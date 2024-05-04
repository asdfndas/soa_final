package tdtu.edu.usersaccountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.DetailLibraryStudySet;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailLibraryStudyRepository extends JpaRepository<DetailLibraryStudySet, Integer> {
    DetailLibraryStudySet save(DetailLibraryStudySet data);

    //    Optional<DetailLibraryStudySet> getAllByUserId(Integer userId);

    //    void deleteByStudyIdAndUserId(Integer studyId, Integer userId);
}
