package noteasy.sundo.queryfactory.teacher.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.teacher.Teacher;
import noteasy.sundo.queryfactory.teacher.factory.TeacherRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, BaseQueryFactory<Teacher, Long>, TeacherRepositoryCustom {
}
