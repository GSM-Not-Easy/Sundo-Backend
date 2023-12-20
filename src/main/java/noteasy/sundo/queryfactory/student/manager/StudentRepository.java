package noteasy.sundo.queryfactory.student.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.factory.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, BaseQueryFactory<Student, Long>, StudentRepositoryCustom {

}
