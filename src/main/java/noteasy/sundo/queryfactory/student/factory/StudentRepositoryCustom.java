package noteasy.sundo.queryfactory.student.factory;

import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.user.User;

import java.util.Optional;

public interface StudentRepositoryCustom {
    Optional<Student> findByUser(User user);
}
