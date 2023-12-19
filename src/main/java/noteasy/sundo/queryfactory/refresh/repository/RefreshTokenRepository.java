package noteasy.sundo.queryfactory.refresh.repository;

import noteasy.sundo.queryfactory.refresh.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
