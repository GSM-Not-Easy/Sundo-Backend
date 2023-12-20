package noteasy.sundo.application.portfolio.dto;

import lombok.*;
import noteasy.sundo.queryfactory.portfolio.Portfolio;
import noteasy.sundo.queryfactory.student.Student;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePortfolioRequest {

        @NotBlank
        private String introduce;

        @Nullable
        private String major;

        @Nullable
        private String githubLink;

        @Nullable
        private String portfolioLink;

        @Nullable
        private String blogLink;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdatePortfolioRequest {

        @NotBlank
        private String introduce;

        @Nullable
        private String major;

        @Nullable
        private String githubLink;

        @Nullable
        private String portfolioLink;

        @Nullable
        private String blogLink;
    }


    @Getter
    @RequiredArgsConstructor
    public static class Responses {
        private final List<Response> portfolioList;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Response {

        private final Long id;

        private final Long studentId;

        private final String studentName;

        private final String major;

        private final Integer grade;

        private final Integer classNum;

        private final Integer number;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Detail {

        private final Long id;

        private final Long studentId;

        private final String studentName;

        private final String major;

        private final Integer grade;

        private final Integer classNum;

        private final Integer number;

        private final String introduce;

        private final String githubLink;

        private final String portfolioLink;

        private final String blogLink;
    }

    public static Response of(Portfolio portfolio) {
        Student student = portfolio.getStudent();

        return Response.builder()
                .id(portfolio.getId())
                .studentId(student.getId())
                .studentName(student.getUser().getName())
                .major(portfolio.getMajor())
                .grade(student.getClassRoom().getGrade())
                .classNum(student.getClassRoom().getClassNum())
                .number(student.getNumber())
                .build();
    }

    public static Detail detailOf(Portfolio portfolio) {
        Student student = portfolio.getStudent();

        return Detail.builder()
                .id(portfolio.getId())
                .studentId(student.getId())
                .studentName(student.getUser().getName())
                .major(portfolio.getMajor())
                .grade(student.getClassRoom().getGrade())
                .classNum(student.getClassRoom().getClassNum())
                .number(student.getNumber())
                .introduce(portfolio.getIntroduce())
                .githubLink(portfolio.getGithubLink())
                .portfolioLink(portfolio.getPortfolioLink())
                .blogLink(portfolio.getBlogLink())
                .build();
    }

    public static Responses listOf(List<Portfolio> portfolioList) {
        List<Response> responses = portfolioList.stream().map(PortfolioDto::of).collect(Collectors.toList());
        return new Responses(responses);
    }

}
