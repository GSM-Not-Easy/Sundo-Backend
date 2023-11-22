package noteasy.sundo.queryfactory.persistmodel.user.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.user.QUser.user;

@Repository
@RequiredArgsConstructor
public abstract class UserQueryFactory implements BaseQueryFactory<User, Long>, JpaRepository<User, Long> {

    private final JPAQueryFactory queryFactory;

    /**
     * soft delete 조건을 추가합니다.
     * @param id
     * @return Optional<User>
     */
    @Override
    public Optional<User> findById(Long id) {
        User result = queryFactory.selectFrom(user)
                .where(user.isDeleted.isFalse().and(user.id.eq(id)))
                .fetchOne();

        return Optional.of(result);
    }

    @Override
    public void delete(User entity) {
        queryFactory.update(user)
                .where(user.id.eq(entity.getId()))
                .set(user.isDeleted, true)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(user)
                .where(user.id.eq(id))
                .set(user.isDeleted, true)
                .execute();
    }
}
