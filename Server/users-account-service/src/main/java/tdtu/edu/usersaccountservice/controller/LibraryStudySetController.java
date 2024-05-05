package tdtu.edu.usersaccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tdtu.edu.usersaccountservice.models.entity.DetailLibraryStudySet;
import tdtu.edu.usersaccountservice.repository.service.LibraryStudyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library-study-set")
@RequiredArgsConstructor
public class LibraryStudySetController {

    private final LibraryStudyService studyService;

    @PostMapping("/add-study-to-library")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> addStudyToLibrary(@RequestBody DetailLibraryStudySet data) {
        DetailLibraryStudySet result = studyService.addLibraryStudy(data);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/library-study-set")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getAllStudySetByUserId(Integer userId) {
        List<DetailLibraryStudySet> result = studyService.getAllByUserId(userId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/library-user")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> getAllUserByStudySet(Integer studyId) {
        List<DetailLibraryStudySet> result = studyService.getAllByStudyId(studyId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/library-delete-user-study")
    @PreAuthorize("hasAuthority('SCOPE_read:user')")
    public ResponseEntity<?> deleteAccessStudy(Integer studyId, Integer userId) {
        try {
            studyService.deleteByStudyIdAndUserId(studyId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
