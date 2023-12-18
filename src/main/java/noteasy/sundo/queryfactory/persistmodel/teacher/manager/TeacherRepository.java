package noteasy.sundo.queryfactory.persistmodel.teacher.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.teacher.Teacher;
import noteasy.sundo.queryfactory.persistmodel.teacher.factory.TeacherRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, BaseQueryFactory<Teacher, Long>, TeacherRepositoryCustom {
}
