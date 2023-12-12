package noteasy.sundo.queryfactory;

import java.util.Optional;


/**
 * DELETE 방식은 Soft Delete 방식을 사용합니다.
 * isDeleted라는 Flag를 통해 delete 과정을 사용합니다.
 * QueryFactory를 구현할 때 Select, Delete 구문을 추가하게 강제합니다.
 */
public interface BaseQueryFactory<T, ID> {
    Optional<T> queryById(ID id);
    void delete(T t);
    void deleteById(ID id);
}
