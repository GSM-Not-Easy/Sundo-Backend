package noteasy.sundo.queryfactory.persistmodel.teacher;

import lombok.*;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "class_room_id", nullable = true)
    private ClassRoom classRoom;

    @Column(name = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;
}
