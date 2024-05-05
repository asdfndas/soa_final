package tdtu.edu.usersaccountservice.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "detail_library_studyset", schema = "public")
public class DetailLibraryStudySet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "study_id")
    public Integer studyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
