package noteasy.sundo.queryfactory.persistmodel.student.factory;

import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.user.User;

import java.util.Optional;

public interface StudentRepositoryImpl {
    Optional<Student> findByUser(User user);
}
