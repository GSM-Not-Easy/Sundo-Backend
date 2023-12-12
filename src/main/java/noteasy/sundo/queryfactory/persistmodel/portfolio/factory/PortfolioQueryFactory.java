package noteasy.sundo.queryfactory.persistmodel.portfolio.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.portfolio.QPortfolio.*;

@Component
@RequiredArgsConstructor
public class PortfolioQueryFactory implements BaseQueryFactory<Portfolio, Long>, PortfolioQueryFactoryNeed {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Portfolio> queryById(Long id) {
        var result = queryFactory.selectFrom(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void softDelete(Portfolio entity) {
        queryFactory.update(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(entity.getId())))
                .set(portfolio.isDeleted, true)
                .execute();
    }

    @Override
    public void softDeleteById(Long id) {
        queryFactory.update(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(id)))
                .set(portfolio.isDeleted, true)
                .execute();
    }

    @Override
    public Boolean existByStudent(Student student) {
        var fetchOne = queryFactory.selectOne()
                .where(portfolio.isDeleted.isFalse().and(portfolio.student.id.eq(student.getId())))
                .fetchFirst();

        return fetchOne != null;
    }
}
