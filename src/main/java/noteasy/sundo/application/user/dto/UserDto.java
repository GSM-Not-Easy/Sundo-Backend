package noteasy.sundo.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class SignUpRequest {
        private String email;
        private String password;
        private String name;
    }
}
