package noteasy.sundo.queryfactory.persistmodel.user.manager;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.factory.UserQueryFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceManager {

    private final UserQueryFactory queryFactory;

    public Optional<User> findById(Long id) {
        return queryFactory.findById(id);
    }

    public void delete(User user) {
        queryFactory.delete(user);
    }

    public void deleteById(Long userId) {
        queryFactory.deleteById(userId);
    }

}
