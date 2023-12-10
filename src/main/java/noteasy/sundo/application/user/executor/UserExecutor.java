package noteasy.sundo.application.user.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.auth.dto.TokenDto;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.application.user.support.UserSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class UserExecutor {

    private final UserSupport userSupport;

    @Transactional(rollbackFor = Exception.class)
    public void executeStudentSignUp(UserDto.StudentSignUpRequest request) {
        userSupport.signUpValidate(request.getEmail());
        userSupport.signUpStudent(request);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeTeacherSignUp(UserDto.TeacherSignUpRequest request) {
        userSupport.signUpValidate(request.getEmail());
        userSupport.signUpTeacher(request);
    }

    public TokenDto.Response executeLogin(UserDto.LoginRequest request) {
        TokenDto.Response result = userSupport.login(request);
        return result;
    }
}
