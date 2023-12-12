package noteasy.sundo.queryfactory.persistmodel.student.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.factory.StudentQueryFactoryNeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPersistenceManager extends JpaRepository<Student, Long>, BaseQueryFactory<Student, Long>, StudentQueryFactoryNeed {

}
