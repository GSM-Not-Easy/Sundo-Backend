package noteasy.sundo.queryfactory.refresh;

import lombok.*;
import noteasy.sundo.queryfactory.user.Authority;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@ToString
@RedisHash(value = "tbl_refresh_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    private String token;

    private Long userId;

    private Authority authority;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Integer expiredAt;

}
