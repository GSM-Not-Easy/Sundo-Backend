package noteasy.sundo.queryfactory.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @Enumerated(EnumType.STRING)
    @Column(name = "approve_status")
    private ApproveStatus approveStatus;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}

