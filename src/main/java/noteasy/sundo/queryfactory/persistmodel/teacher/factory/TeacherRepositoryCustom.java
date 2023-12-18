package noteasy.sundo.queryfactory.persistmodel.teacher.factory;

import noteasy.sundo.queryfactory.persistmodel.teacher.Subject;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;

import java.util.Optional;

public interface TeacherRepositoryCustom {
    Optional<Teacher> findByStudentAndSubject(Subject subject);
}
