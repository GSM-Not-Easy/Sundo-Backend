package noteasy.sundo.queryfactory.portfolio.factory;

import noteasy.sundo.queryfactory.portfolio.Portfolio;
import noteasy.sundo.queryfactory.student.Student;

import java.util.List;

public interface PortfolioRepositoryCustom {
    Boolean existsByStudent(Student student);
    List<Portfolio> search(Integer grade, Integer classNum, String keyword);
}