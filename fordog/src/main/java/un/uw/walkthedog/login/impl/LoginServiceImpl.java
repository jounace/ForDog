package un.uw.walkthedog.login.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import un.uw.walkthedog.login.LoginService;
import un.uw.walkthedog.login.dao.IDao;
import un.uw.walkthedog.login.pojo.LoginRequest;
import un.uw.walkthedog.login.pojo.LoginResult;

import java.util.HashMap;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    public IDao dao;

    @Override
    public LoginResult login(LoginRequest loginRequest) {
        LoginResult loginResult = new LoginResult();
        HashMap<String, String> result = dao.login(loginRequest.getUsername(), loginRequest.getPassword(),loginRequest.getRole());
        if (result.get(loginRequest.getUsername()).equals("is not exist")) {
            loginResult.setStatus("false");
            loginResult.setInfo(loginRequest.getUsername() + " is not exist");
        } else if (result.get(loginRequest.getUsername()).equals(loginRequest.getPassword())) {
            loginResult.setStatus("true");
            loginResult.setInfo("success");
            loginResult.setUserid(dao.getuserid(loginRequest.getUsername(),loginRequest.getRole()));
        } else {
            loginResult.setInfo("incorrect password");
            loginResult.setStatus("false");
        }
        return loginResult;
    }
}
