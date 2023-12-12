package noteasy.sundo.queryfactory.persistmodel.portfolio.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.portfolio.QPortfolio.*;

@Component
@RequiredArgsConstructor
public class PortfolioQueryFactory implements BaseQueryFactory<Portfolio, Long> {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Portfolio> findById(Long id) {
        var result = queryFactory.selectFrom(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void delete(Portfolio entity) {
        queryFactory.update(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(entity.getId())))
                .set(portfolio.isDeleted, true)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(portfolio)
                .where(portfolio.isDeleted.isFalse().and(portfolio.id.eq(id)))
                .set(portfolio.isDeleted, true)
                .execute();
    }
}
