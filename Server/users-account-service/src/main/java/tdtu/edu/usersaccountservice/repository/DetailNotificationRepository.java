package tdtu.edu.usersaccountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.DetailLibraryStudySet;
import tdtu.edu.usersaccountservice.models.entity.DetailNotificationUser;
import tdtu.edu.usersaccountservice.models.entity.Notification;

import java.util.List;

@Repository
public interface DetailNotificationRepository extends JpaRepository<DetailNotificationUser, Integer> {
    DetailNotificationUser save(DetailNotificationUser data);

    @Query(value = "select noti from DetailNotificationUser noti where noti.user = ?1")
    List<DetailLibraryStudySet> findAllByUserId(Integer userId);

    @Query(value = "select libra from DetailNotificationUser libra where libra.notification = ?1")
    List<DetailLibraryStudySet> findAllByNotificationId(Integer notificationId);
}
