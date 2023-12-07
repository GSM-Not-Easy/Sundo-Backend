package noteasy.sundo.application.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDto {


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


        @NotNull
        private Boolean isHomeroom;

        @Nullable
        private Integer grade;

        @Nullable
        private Integer classNum;
    }
}
