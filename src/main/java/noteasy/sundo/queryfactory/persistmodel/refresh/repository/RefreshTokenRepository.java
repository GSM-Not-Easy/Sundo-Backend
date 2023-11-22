package noteasy.sundo.queryfactory.persistmodel.refresh.repository;

import noteasy.sundo.queryfactory.persistmodel.refresh.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
