package noteasy.sundo.queryfactory.teacher.factory;

import noteasy.sundo.queryfactory.teacher.Subject;
import noteasy.sundo.queryfactory.teacher.Teacher;
import noteasy.sundo.queryfactory.user.User;

import java.util.Optional;

public interface TeacherRepositoryCustom {
    Optional<Teacher> findBySubject(Subject subject);
    Optional<Teacher> findByUser(User user);
}
