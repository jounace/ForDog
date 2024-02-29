package un.uw.walkthedog.signup;

import un.uw.walkthedog.signup.pojo.SignUpRequest;
import un.uw.walkthedog.signup.pojo.SignUpResult;

public interface SignupService {
    public SignUpResult signup(SignUpRequest signUpRequest);
}
