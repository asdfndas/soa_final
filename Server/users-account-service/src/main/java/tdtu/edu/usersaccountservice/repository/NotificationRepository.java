package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.Notification;

import java.util.List;
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

    List<Notification> readAllByStudyID(Integer studyId);

//    @Query(value = "select noti from Notification noti where noti.user.userId = ?1")
//    List<Notification> readAllByUserId(Integer userId);

    @Query(value = "select noti.* from notification noti " +
            "join detail_notification_user d on d.notification_id = noti.notification_id " +
            "join public.user u on d.user_id = u.user_id " +
            "where u.user_id = :userId", nativeQuery = true)
    Optional<List<Notification>> getNotificationByUserId(@Param("userId") Integer userId);


    @Query("select noti from Notification noti where noti.studyID = ?1 and noti.user.userId = ?2")
    Optional<List<Notification>> getAllByStudyIdAndUserId(Integer studyId, Integer userId);

    void deleteById(Integer id);

}
