package noteasy.sundo.queryfactory.wee.repository;

import noteasy.sundo.queryfactory.BaseQueryFactory;
import noteasy.sundo.queryfactory.wee.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Long>, BaseQueryFactory<Consult, Long> {
}
