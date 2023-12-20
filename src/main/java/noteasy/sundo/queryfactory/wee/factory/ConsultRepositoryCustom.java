package noteasy.sundo.queryfactory.wee.factory;

import java.time.LocalDateTime;

public interface ConsultRepositoryCustom {
    Boolean existsByStudentIdAndConsultDate(Long studentId, LocalDateTime consultDate);
}
