package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.DetailLibraryStudySet;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailLibraryStudyRepository extends JpaRepository<DetailLibraryStudySet, Integer> {
    DetailLibraryStudySet save(DetailLibraryStudySet data);

    @Query(value = "select libra from DetailLibraryStudySet libra where libra.user.userId = ?1")
    List<DetailLibraryStudySet> findAllByUserId(Integer userId);

    @Query(value = "select libra from DetailLibraryStudySet libra where libra.user.userId = ?1")
    List<DetailLibraryStudySet> findAllByStudyId(Integer studyId);

    @Modifying
    @Transactional
    @Query("delete from DetailLibraryStudySet d where d.studyId = ?1 and d.user.userId = ?2")
    void deleteByStudyIdAndUserId(Integer studyId, Integer userId);

}
