package un.uw.walkthedog.login;

import un.uw.walkthedog.login.pojo.LoginRequest;
import un.uw.walkthedog.login.pojo.LoginResult;

public interface LoginService {

    public LoginResult login(LoginRequest loginRequest);
}
