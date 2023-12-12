package noteasy.sundo.queryfactory.persistmodel.portfolio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noteasy.sundo.queryfactory.persistmodel.student.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "tbl_portfolio")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    @NotNull
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "introduce", columnDefinition = "TEXT")
    @NotNull
    private String introduce;

    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "portfolio_link")
    private String portfolioLink;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
