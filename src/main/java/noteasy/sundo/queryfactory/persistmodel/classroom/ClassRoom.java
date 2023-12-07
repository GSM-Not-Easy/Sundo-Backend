package noteasy.sundo.queryfactory.persistmodel.classroom;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_class_room", uniqueConstraints = {
        @UniqueConstraint(name = "schoolClassRoom", columnNames = {"grade", "class_num"})
})
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_room_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer grade;

    @Column(name = "class_num", nullable = false)
    private Integer classNum;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
