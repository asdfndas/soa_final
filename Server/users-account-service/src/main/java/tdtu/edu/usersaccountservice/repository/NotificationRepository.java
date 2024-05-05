package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.Notification;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO public.notification (content, description, study_id, user_id) " +
                    "VALUES (:content, :description, :studyId, :userId)",
            nativeQuery = true
    )

    void saveNotification(
            @Param("content") String content,
            @Param("description") String description,
            @Param("studyId") Integer studyId,
            @Param("userId") Integer userId
    );

    Optional<Notification> findById(Integer id);

    void deleteById(Integer id);


}
