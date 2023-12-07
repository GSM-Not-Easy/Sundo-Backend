package noteasy.sundo.queryfactory.persistmodel.student.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static noteasy.sundo.queryfactory.persistmodel.student.QStudent.*;


@Component
@RequiredArgsConstructor
public class StudentQueryFactory implements BaseQueryFactory<Student, Long> {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Student> findById(Long id) {
        var result = queryFactory.selectFrom(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(id)))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public void delete(Student entity) {
        queryFactory.update(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(entity.getId())))
                .set(student.isDeleted, true)
                .execute();
    }

    @Override
    public void deleteById(Long id) {
        queryFactory.update(student)
                .where(student.isDeleted.isFalse().and(student.id.eq(id)))
                .set(student.isDeleted, true)
                .execute();
    }
}
