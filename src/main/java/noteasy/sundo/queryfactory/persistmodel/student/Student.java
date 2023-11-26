package noteasy.sundo.queryfactory.persistmodel.student;

import lombok.*;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Integer grade;

    @OneToOne
    @JoinColumn(name = "class_room_id", nullable = false)
    private ClassRoom classRoom;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;
}
