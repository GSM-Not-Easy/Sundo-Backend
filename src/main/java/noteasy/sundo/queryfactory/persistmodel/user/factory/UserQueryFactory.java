package noteasy.sundo.queryfactory.persistmodel.user.factory;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.user.User;

import java.util.Optional;

public class UserQueryFactory implements BaseQueryFactory<User, Long> {




    @Override
    public Optional<User> findById(Long id) {


        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
