package un.uw.walkthedog.login.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class LoginServiceDao implements IDao{

    @Autowired
    @Qualifier("mysqljdbctemple")
    private JdbcTemplate mysqlJdbcTemple;

    @Override
    public String getuserid(String username, String type) {
        String table = "";
        if (type.equals("owner")){
            table = "DOGOWNER";
        }else {
            table = "WORKER";
        }
        String sql = "select userid from ? where username = ?";
        return mysqlJdbcTemple.queryForMap(sql,table,username).get("userid").toString();
    }

    public HashMap<String,String> login(String username, String password, String type){
        String table = "";
        if (type.equals("owner")){
            table = "DOGOWNER";
        }else {
            table = "WORKER";
        }
        HashMap<String,String> loginResult = new HashMap<>();
        String sql = "select username,password from ? where username = ?";
        List<Map<String, Object>> rows = mysqlJdbcTemple.queryForList(sql,table,username);
        if (rows == null || rows.size()<1){
            loginResult.put(username,"is not exist");
        }else {
            for (Map<String,Object> row:rows){
                loginResult.put(row.get("username").toString(),row.get("password").toString());
            }
        }
        return loginResult;
    }

}
