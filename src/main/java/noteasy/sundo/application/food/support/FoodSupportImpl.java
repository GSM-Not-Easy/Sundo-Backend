package noteasy.sundo.application.food.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.food.dto.FoodDto;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.food.Food;
import noteasy.sundo.queryfactory.food.repository.FoodRepository;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.teacher.manager.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FoodSupportImpl implements FoodSupport {

    private final FoodRepository foodRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SecurityContextUtil contextUtil;

    @Override
    public void saveFood(FoodDto.Request request) {
        Student student = contextUtil.currentStudent();

        Food food = Food.builder()
                .foodName(request.getFoodName())
                .createdAt(LocalDateTime.now())
                .studentId(student.getUser().getId())
                .build();

        foodRepository.save(food);
    }
}
