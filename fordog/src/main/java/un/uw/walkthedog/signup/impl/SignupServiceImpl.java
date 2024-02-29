package un.uw.walkthedog.signup.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import un.uw.walkthedog.signup.SignupService;
import un.uw.walkthedog.signup.dao.IDao;
import un.uw.walkthedog.signup.pojo.SignUpRequest;
import un.uw.walkthedog.signup.pojo.SignUpResult;

@Component
public class SignupServiceImpl implements SignupService {

    @Autowired
    public IDao dao;

    @Override
    public SignUpResult signup(SignUpRequest signUpRequest) {
        SignUpResult signUpResult = new SignUpResult();
        if (dao.isExist(signUpRequest.getUsername(),signUpRequest.getRole())){
            signUpResult.setInfo("already has the same person");
            signUpResult.setStatus("false");
        }else {
            int i = dao.adduser(signUpRequest.getUsername(),signUpRequest.getPhone(),signUpRequest.getAddress(),signUpRequest.getNotes(),signUpRequest.getPassword(),signUpRequest.getRole());
            if (i>0){
                signUpResult.setStatus("success");
            }else {
                signUpResult.setStatus("false");
                signUpResult.setInfo("add user error");
            }
        }
        return signUpResult;
    }
}
