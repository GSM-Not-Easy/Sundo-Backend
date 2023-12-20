package noteasy.sundo.queryfactory.wee.factory;

import java.time.LocalDateTime;

public interface ConsultRepositoryCustom {
    Boolean existsByStudentAndConsultDate(Long id, LocalDateTime consultDate);
}
