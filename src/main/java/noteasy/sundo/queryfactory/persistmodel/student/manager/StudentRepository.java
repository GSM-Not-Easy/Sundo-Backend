package noteasy.sundo.queryfactory.persistmodel.student.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.factory.StudentRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>, BaseQueryFactory<Student, Long>, StudentRepositoryImpl {

}
