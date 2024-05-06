package tdtu.edu.usersaccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.usersaccountservice.models.dto.NotificationRequest;
import tdtu.edu.usersaccountservice.models.entity.Notification;
import tdtu.edu.usersaccountservice.repository.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create-notification-study-set")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> createNotificationByStudyId(@RequestBody NotificationRequest data) {
        notificationService.createNotification(data);
        List<Notification> result = notificationService.getAllByUserId(data.getUserId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/read-notification")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getNotificationById(Integer id) {
        Notification result = notificationService.getNotificationById(id);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/read-notification-studyId")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getListNotificationByStudyId(Integer studyId){
        List<Notification> result = notificationService.getAllByStudyId(studyId);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/read-notification-userId")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getListNotificationByUserId(Integer userId) {
        List<Notification> result = notificationService.getAllByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/read-notification-studyId-userId")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getListNotificationByStudyIdAndUserId(Integer studyId, Integer userId) {
        List<Notification> result = notificationService.getAllByStudyIdAndUserId(studyId, userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete-notification")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> deleteNotification(Integer id) {
        notificationService.deleteNotificationById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
