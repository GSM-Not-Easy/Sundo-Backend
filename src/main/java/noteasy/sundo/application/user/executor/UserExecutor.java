package noteasy.sundo.application.user.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.application.user.support.UserSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Component
@RequiredArgsConstructor
@Validated
public class UserExecutor {

    private final UserSupport userSupport;

    @Transactional(rollbackFor = Exception.class)
    public void executeSignUp(UserDto.StudentSignUpRequest request) {
    }
}
