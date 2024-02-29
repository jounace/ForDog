package un.uw.walkthedog.signup.dao;

public interface IDao {
    public boolean isExist(String username,String role);

    public int adduser(String username,String phone,String address, String notes,String password,String role);
}
