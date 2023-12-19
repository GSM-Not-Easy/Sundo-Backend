package noteasy.sundo.queryfactory.teacher.factory;

import noteasy.sundo.queryfactory.teacher.Subject;
import noteasy.sundo.queryfactory.teacher.Teacher;

import java.util.Optional;

public interface TeacherRepositoryCustom {
    Optional<Teacher> findBySubject(Subject subject);
}
