package noteasy.sundo.queryfactory.persistmodel.portfolio.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.portfolio.QPortfolio.*;
import static noteasy.sundo.queryfactory.persistmodel.student.QStudent.student;
import static noteasy.sundo.queryfactory.persistmodel.user.QUser.user;

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

    @Override
    public Boolean existsByStudent(Student student) {
        var fetchOne = queryFactory.selectOne()
                .where(portfolio.isDeleted.isFalse().and(portfolio.student.id.eq(student.getId())))
                .fetchFirst();

        return fetchOne != null;
    }

    /**
     * portfolio 전체 조회 - Student, User 값을 패치조인으로 가져옵니다.
     * @return List<Portfolio>
     */
    @Override
    public List<Portfolio> findAllByIsNotDeleted() {
        return queryFactory.selectFrom(portfolio)
                .where(portfolio.isDeleted.isFalse())
                .leftJoin(portfolio.student, student)
                .fetchJoin()
                .leftJoin(student.user, user)
                .fetchJoin()
                .fetch();
    }
}
