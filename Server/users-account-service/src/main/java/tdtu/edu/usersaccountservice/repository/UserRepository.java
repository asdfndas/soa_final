package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.dto.Gender;
import tdtu.edu.usersaccountservice.models.dto.UserResponse;
import tdtu.edu.usersaccountservice.models.entity.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO public.user (user_name, email, first_name, " +
                    "last_name, gender, is_teacher, date_of_birth) " +
                    "VALUES (:userName, :email, :fullName, :dateOfBirth)",
            nativeQuery = true
    )
    void saveUser(
            @Param("userName") String userName,
            @Param("email") String email,
            @Param("first_name") String firstName,
            @Param("last_name") String lastName,
            @Param("gender") Gender gender,
            @Param("is_teacher") boolean isTeacher,
            @Param("dateOfBirth") Date dateOfBirth
    );

    User save(User user);

    List<User> getUserByEmail(String email);

    Optional<User> getUserByUserId(int userId);

    @Modifying
    @Transactional
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.userName=?3 where u.email = ?4")
    User updateUserByEmail(String firstName, String lastName, String userName, String email);


}
