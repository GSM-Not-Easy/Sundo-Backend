package noteasy.sundo.global.library.security;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.manager.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil {

    private final UserRepository userPm;

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof Long) {
            return userPm.queryById((Long) principal)
                    .orElseThrow(() -> new GlobalException("Current User Not Found", HttpStatus.NOT_FOUND));
        } else {
            throw new GlobalException("Principal is Not Valid", HttpStatus.UNAUTHORIZED);
        }
    }
}
