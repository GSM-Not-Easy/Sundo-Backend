package noteasy.sundo.application.food.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.food.dto.FoodDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.food.Food;
import noteasy.sundo.queryfactory.food.repository.FoodRepository;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.teacher.manager.TeacherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodSupportImpl implements FoodSupport {

    private final FoodRepository foodRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SecurityContextUtil contextUtil;

    @Override
    public void createFood(FoodDto.Request request) {
        Student student = contextUtil.currentStudent();

        Food food = Food.builder()
                .foodName(request.getFoodName())
                .createdAt(LocalDateTime.now())
                .studentId(student.getUser().getId())
                .build();

        foodRepository.save(food);
    }

    @Override
    public FoodDto.Responses queryAllFoods() {
        List<Food> foods = foodRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));

        Map<Food, Student> foodMap = foods.parallelStream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        food -> studentRepository.queryById(food.getStudentId())
                                .orElseThrow(() -> new GlobalException("Not Found Student", HttpStatus.NOT_FOUND))
                ));

        return FoodDto.listOf(foodMap);
    }
}
