package noteasy.sundo.queryfactory.persistmodel.refresh.manager;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.persistmodel.refresh.RefreshToken;
import noteasy.sundo.queryfactory.persistmodel.refresh.repository.RefreshTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RefreshTokenManager {

    private final RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findById(token);
    }

    public RefreshToken save(RefreshToken token) {
        return refreshTokenRepository.save(token);
    }
}
