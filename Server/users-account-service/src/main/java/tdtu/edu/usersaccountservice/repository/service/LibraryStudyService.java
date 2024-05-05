package tdtu.edu.usersaccountservice.repository.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tdtu.edu.usersaccountservice.models.entity.DetailLibraryStudySet;
import tdtu.edu.usersaccountservice.repository.DetailLibraryStudyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryStudyService {

    private final DetailLibraryStudyRepository detailLibraryStudyRepository;

    public DetailLibraryStudySet addLibraryStudy(DetailLibraryStudySet data) {
        return detailLibraryStudyRepository.save(data);
    }

    public List<DetailLibraryStudySet> getAllByUserId(Integer userId) {
        return detailLibraryStudyRepository.findAllByUserId(userId);
    }

    public List<DetailLibraryStudySet> getAllByStudyId(Integer studyId) {
        return  detailLibraryStudyRepository.findAllByStudyId(studyId);
    }

    public void deleteByStudyIdAndUserId(Integer studyId, Integer userId) {
        detailLibraryStudyRepository.deleteByStudyIdAndUserId(studyId, userId);
    }

}
