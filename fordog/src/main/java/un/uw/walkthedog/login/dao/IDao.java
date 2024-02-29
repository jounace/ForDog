package un.uw.walkthedog.login.dao;


import java.util.HashMap;

public interface IDao {
    public String getuserid(String username, String type);

    public HashMap<String,String> login(String username, String password,String type);

}
