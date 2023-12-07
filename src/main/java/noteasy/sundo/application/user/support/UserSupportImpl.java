package noteasy.sundo.application.user.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.manager.UserPersistenceManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSupportImpl implements UserSupport {

    private final UserPersistenceManager pm;
    private final PasswordEncoder encoder;

    @Override
    public void save(User user) {
        pm.save(user);
    }

    @Override
    public User findUserById(Long id) {
        var user = pm.findById(id)
                .orElseThrow(() -> new GlobalException("Not Found User", HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public void deleteById(Long id) {
        pm.deleteById(id);
    }

    @Override
    public void delete(User user) {
        pm.delete(user);
    }

    @Override
    public void signUpValidate(UserDto.StudentSignUpRequest request) {
        var requestEmail = request.getEmail();
        var isExist = pm.existsByEmail(requestEmail);

        if(isExist) {
            throw new GlobalException("Already Exist User", HttpStatus.ALREADY_REPORTED);
        }
    }

    @Override
    public void signUpTeacher() {

    }

    @Override
    public void signUpStudent() {

    }
}
