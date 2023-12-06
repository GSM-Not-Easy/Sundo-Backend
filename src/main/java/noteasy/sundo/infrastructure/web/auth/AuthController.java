package noteasy.sundo.infrastructure.web.auth;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.user.executor.UserExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserExecutor userExecutor;


}
