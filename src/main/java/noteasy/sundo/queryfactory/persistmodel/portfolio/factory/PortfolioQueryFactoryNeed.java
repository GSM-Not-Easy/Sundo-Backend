package noteasy.sundo.queryfactory.persistmodel.portfolio.factory;

import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.student.Student;

import java.util.List;

public interface PortfolioQueryFactoryNeed {
    Boolean existsByStudent(Student student);
    List<Portfolio> findAllByIsNotDeleted();
}
