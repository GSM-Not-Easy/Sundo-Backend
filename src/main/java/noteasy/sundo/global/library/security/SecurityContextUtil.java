package noteasy.sundo.global.library.security;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.teacher.Teacher;
import noteasy.sundo.queryfactory.teacher.manager.TeacherRepository;
import noteasy.sundo.queryfactory.user.User;
import noteasy.sundo.queryfactory.user.manager.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof Long) {
            return userRepository.queryById((Long) principal)
                    .orElseThrow(() -> new GlobalException("Current User Not Found", HttpStatus.NOT_FOUND));
        } else {
            throw new GlobalException("Principal is Not Valid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Student currentStudent() {
        return studentRepository.findByUser(currentUser())
                .orElseThrow(() -> new GlobalException("Student Not Found", HttpStatus.NOT_FOUND));
    }

    public Teacher currentTeacher() {
        return teacherRepository.findByUser(currentUser())
                .orElseThrow(() -> new GlobalException("Teacher Not Found", HttpStatus.NOT_FOUND));
    }
}
