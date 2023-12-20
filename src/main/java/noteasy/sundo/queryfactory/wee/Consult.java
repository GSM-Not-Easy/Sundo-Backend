package noteasy.sundo.queryfactory.wee;

import lombok.*;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.teacher.Teacher;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "tbl_consult")
@NoArgsConstructor
@AllArgsConstructor
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consult_id", nullable = false)
    private Long id;

    @Column(name = "consult_date", nullable = false)
    private LocalDateTime consultDate;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Integer period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wee_id")
    private Teacher teacher;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
