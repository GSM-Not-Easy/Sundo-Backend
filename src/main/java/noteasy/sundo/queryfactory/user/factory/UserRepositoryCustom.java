package noteasy.sundo.queryfactory.user.factory;

import noteasy.sundo.queryfactory.user.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
