package noteasy.sundo.infrastructure.creator;

import noteasy.sundo.queryfactory.persistmodel.refresh.RefreshToken;
import noteasy.sundo.queryfactory.persistmodel.user.ApproveStatus;
import noteasy.sundo.queryfactory.persistmodel.user.Authority;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GlobalEntityCreatorImpl implements GlobalEntityCreator {

    @Override
    public User createUser(String email, String password, String name, Authority authority, ApproveStatus approveStatus) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .authority(authority)
                .approveStatus(approveStatus)
                .isDeleted(false)
                .build();
    }

    @Override
    public RefreshToken createRefreshToken(String token, Long userId, Authority authority, Integer expiredAt) {
        return RefreshToken.builder()
                .token(token)
                .userId(userId)
                .authority(authority)
                .expiredAt(expiredAt)
                .build();
    }
}
