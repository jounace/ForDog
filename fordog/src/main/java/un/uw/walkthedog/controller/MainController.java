package un.uw.walkthedog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import un.uw.walkthedog.login.LoginService;
import un.uw.walkthedog.login.pojo.LoginRequest;
import un.uw.walkthedog.login.pojo.LoginResult;
import un.uw.walkthedog.signup.SignupService;
import un.uw.walkthedog.signup.pojo.SignUpRequest;
import un.uw.walkthedog.signup.pojo.SignUpResult;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private Environment environment;

    @Autowired
    private LoginService loginService;

    @Autowired
    private SignupService signupService;

    @RequestMapping("/login")
    @POST
    public LoginResult login(@ModelAttribute LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @RequestMapping("/signup")
    @POST
    public SignUpResult signUpResult(@ModelAttribute SignUpRequest signUpRequest){
        return signupService.signup(signUpRequest);
    }
}
