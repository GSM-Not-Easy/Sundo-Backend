package noteasy.sundo.infrastructure.creator;

import noteasy.sundo.queryfactory.persistmodel.refresh.RefreshToken;
import noteasy.sundo.queryfactory.persistmodel.user.ApproveStatus;
import noteasy.sundo.queryfactory.persistmodel.user.Authority;
import noteasy.sundo.queryfactory.persistmodel.user.User;

import java.time.LocalDateTime;

public interface GlobalEntityCreator {
    User createUser(String email, String password, String name, Authority authority, ApproveStatus approveStatus);
    RefreshToken createRefreshToken(String token, Long userId, Authority authority, Integer expiredAt);
}
