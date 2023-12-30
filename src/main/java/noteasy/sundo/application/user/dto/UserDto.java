package noteasy.sundo.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noteasy.sundo.queryfactory.teacher.Subject;
import reactor.util.annotation.Nullable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentSignUpRequest {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotNull
        private Integer grade;

        @NotNull
        private Integer classNum;

        @NotNull
        private Integer number;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherSignUpRequest {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @Enumerated(EnumType.STRING)
        @NotNull
        private Subject subject;

        @Nullable
        private Integer grade;

        @Nullable
        private Integer classNum;
    }
}
