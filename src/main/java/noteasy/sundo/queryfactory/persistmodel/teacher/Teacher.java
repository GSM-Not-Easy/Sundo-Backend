package noteasy.sundo.queryfactory.persistmodel.teacher;

import lombok.*;
import noteasy.sundo.queryfactory.persistmodel.classroom.ClassRoom;
import noteasy.sundo.queryfactory.persistmodel.user.User;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "class_room_id", nullable = true)
    private ClassRoom classRoom;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
