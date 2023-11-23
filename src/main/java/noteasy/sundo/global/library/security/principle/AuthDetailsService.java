package noteasy.sundo.global.library.security.principle;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import noteasy.sundo.queryfactory.persistmodel.user.manager.UserPersistenceManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserPersistenceManager userPersistenceManager;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userPersistenceManager.findById(Long.parseLong(username))
                .orElseThrow(() -> new GlobalException("Not Found User in AuthDetailsService", HttpStatus.NOT_FOUND));
        return new AuthDetails(user);
    }

}
