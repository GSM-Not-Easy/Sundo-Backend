package noteasy.sundo.queryfactory.persistmodel.user.factory;

import noteasy.sundo.queryfactory.persistmodel.user.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
