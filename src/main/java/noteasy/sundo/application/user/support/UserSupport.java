package noteasy.sundo.application.user.support;

import noteasy.sundo.application.auth.dto.TokenDto;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.queryfactory.persistmodel.user.User;

public interface UserSupport {

    void save(User user);
    User findUserById(Long id);
    void deleteById(Long id);
    void delete(User user);
    void signUpValidate(String email);
    void signUpTeacher(UserDto.TeacherSignUpRequest request);
    void signUpStudent(UserDto.StudentSignUpRequest request);

    TokenDto.Response login(UserDto.LoginRequest request);
}
