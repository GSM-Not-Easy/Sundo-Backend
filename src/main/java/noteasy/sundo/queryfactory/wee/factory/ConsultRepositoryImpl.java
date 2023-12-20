package noteasy.sundo.queryfactory.wee.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.wee.Consult;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static noteasy.sundo.queryfactory.wee.QConsult.consult;

@Repository
@RequiredArgsConstructor
public class ConsultRepositoryImpl implements BaseQueryFactory<Consult, Long>, ConsultRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Consult> queryById(Long id) {
        Consult result = queryFactory.selectFrom(consult)
                .where(consult.isDeleted.isFalse().and(consult.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void delete(Consult entity) {
        queryFactory.update(consult)
                .where(consult.isDeleted.isFalse().and(consult.id.eq(entity.getId())))
                .set(consult.isDeleted, false)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(consult)
                .where(consult.isDeleted.isFalse().and(consult.id.eq(id)))
                .set(consult.isDeleted, false)
                .execute();
    }

    @Override
    public Boolean existsByStudentIdAndConsultDate(Long id, LocalDateTime consultDate) {
        var result = queryFactory.selectOne()
                .from(consult)
                .where(consult.isDeleted.isFalse()
                        .and(consult.student.id.eq(id))
                        .and(consult.consultDate.eq(consultDate)))
                .fetchOne();

        return result != null;
    }
}
