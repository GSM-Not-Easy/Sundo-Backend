package noteasy.sundo.queryfactory;

import java.util.Optional;

public interface BaseQueryFactory<T, ID> {
    Optional<T> findById(ID id);
    T save(T t);
    void delete(T t);
    void deleteById(ID id);
}
