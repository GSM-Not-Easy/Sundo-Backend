package noteasy.sundo.web.auth;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.auth.dto.TokenDto;
import noteasy.sundo.application.user.dto.UserDto;
import noteasy.sundo.application.user.executor.UserExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final UserExecutor userExecutor;

    @PostMapping("/student")
    public ResponseEntity<Void> signUpStudent(@RequestBody UserDto.StudentSignUpRequest request) {
        userExecutor.executeStudentSignUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/teacher")
    public ResponseEntity<Void> signUpTeacher(@RequestBody UserDto.TeacherSignUpRequest request) {
        userExecutor.executeTeacherSignUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto.Response> login(@RequestBody UserDto.LoginRequest request) {
        TokenDto.Response response = userExecutor.executeLogin(request);
        return ResponseEntity.ok(response);
    }

}
