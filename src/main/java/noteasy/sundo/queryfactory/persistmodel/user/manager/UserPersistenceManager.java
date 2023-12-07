package noteasy.sundo.queryfactory.persistmodel.user.manager;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.factory.UserQueryFactoryNeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersistenceManager extends JpaRepository<User, Long>, BaseQueryFactory<User, Long>, UserQueryFactoryNeed {
}
