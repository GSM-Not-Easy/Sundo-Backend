package noteasy.sundo.queryfactory.user.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.user.User;
import noteasy.sundo.queryfactory.user.factory.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, BaseQueryFactory<User, Long>, UserRepositoryCustom {
}
