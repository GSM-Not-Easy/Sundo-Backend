package noteasy.sundo.queryfactory.persistmodel.user.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.user.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryFactory implements BaseQueryFactory<User, Long>, UserQueryFactoryNeed{

    private final JPAQueryFactory queryFactory;

    /**
     * soft delete 조건을 추가합니다.
     * @param id
     * @return Optional<User>
     */
    @Override
    public Optional<User> queryById(Long id) {
        var result = queryFactory.selectFrom(user)
                .where(user.isDeleted.isFalse().and(user.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var result = queryFactory.selectFrom(user)
                .where(user.isDeleted.isFalse().and(user.email.eq(email)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Boolean existsByEmail(String email) {
        var fetchOne = queryFactory.selectOne()
                .from(user)
                .where(user.isDeleted.isFalse().and(user.email.equalsIgnoreCase(email)))
                .fetchOne();

        return fetchOne != null;
    }

    @Override
    public void softDelete(User entity) {
        queryFactory.update(user)
                .where(user.id.eq(entity.getId()))
                .set(user.isDeleted, true)
                .execute();
    }

    @Override
    public void softDeleteById(Long id) {
        queryFactory.update(user)
                .where(user.id.eq(id))
                .set(user.isDeleted, true)
                .execute();
    }

}
