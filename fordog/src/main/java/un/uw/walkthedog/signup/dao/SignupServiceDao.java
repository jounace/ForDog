package un.uw.walkthedog.signup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SignupServiceDao implements IDao {

    @Autowired
    @Qualifier("mysqljdbctemple")
    private JdbcTemplate mysqlJdbcTemple;

    @Override
    public boolean isExist(String username, String role) {
        String table = "";
        if (role.equals("owner")) {
            table = "DOGOWNER";
        } else if (role.equals("worker")) {
            table = "WORKER";
        }
        String sql = "select userid from ? where username = ?";
        List<Map<String, Object>> rows = mysqlJdbcTemple.queryForList(sql, table, username);
        if (rows.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int adduser(String username, String phone, String address, String notes, String password, String role) {
        String table = "";
        if (role.equals("owner")) {
            table = "DOGOWNER";
        } else if (role.equals("worker")) {
            table = "WORKER";
        }

        String sql = "insert into ? (id,name,phone,address,notes,password) values (?,?,?,?,?,?)";
        String userid = username+password+phone+address+notes+role;


        return mysqlJdbcTemple.update(sql,table,userid.hashCode(),username,phone,address,address,notes,password);
    }
}
