package noteasy.sundo.queryfactory.persistmodel.user.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.factory.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, BaseQueryFactory<User, Long>, UserRepositoryCustom {
}
