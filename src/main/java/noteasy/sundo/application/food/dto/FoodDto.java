package noteasy.sundo.application.food.dto;


import lombok.*;
import noteasy.sundo.queryfactory.food.Food;
import noteasy.sundo.queryfactory.student.Student;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class FoodDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotEmpty
        private String foodName;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Response {

        private final String foodName;

        private final LocalDateTime createdAt;

        private final String studentName;

        private final Integer grade;

        private final Integer classNum;

        private final Integer number;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Responses {
        private final List<Response> foods;
    }

    public static Response of(Food food, Student student) {
        return Response.builder()
                .foodName(food.getFoodName())
                .createdAt(food.getCreatedAt())
                .studentName(student.getUser().getName())
                .grade(student.getClassRoom().getGrade())
                .classNum(student.getClassRoom().getClassNum())
                .number(student.getNumber())
                .build();
    }

    /**
     * key food
     * value student
     * @param foodMap
     * @return FoodDto.Responses
     */
    public static Responses listOf(Map<Food, Student> foodMap) {
        List<FoodDto.Response> responses = foodMap.entrySet().stream()
                .map(entry -> FoodDto.of((entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        return new Responses(responses);
    }
}
