package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.dto.NotificationRequest;
import tdtu.edu.usersaccountservice.models.entity.Notification;
import tdtu.edu.usersaccountservice.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void createNotification(NotificationRequest data) {

        notificationRepository.saveNotification(data.getContent(), data.getDescription(), data.getStudyID(), data.getUserId());
    }

    public Notification getNotificationById(Integer id) {
        Optional<Notification> result = notificationRepository.findById(id);
        return result.orElse(null);
    }

    public List<Notification> getAllByStudyId(Integer studyId) {
        return notificationRepository.readAllByStudyID(studyId);
    }

    public List<Notification> getAllByStudyIdAndUserId(Integer studyId, Integer userId) {
        Optional<List<Notification>> result = notificationRepository.getAllByStudyIdAndUserId(studyId, userId);
        return result.orElse(null);
    }

    public List<Notification> getAllByUserId(Integer userId) {
        Optional<List<Notification>> retults = notificationRepository.getNotificationByUserId(userId);
        return retults.orElse(null);
    }
    public void deleteNotificationById(Integer id) {
        notificationRepository.deleteById(id);
    }
}
