package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.Notification;
import tdtu.edu.usersaccountservice.models.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO public.user (user_name, email, first_name, " +
                    "last_name, gender, is_teacher, date_of_birth, user_role_id) " +
                    "VALUES (:userName, :email, :firstName, :lastName, :gender, :isTeacher,  :dateOfBirth, :userRoleId)",
            nativeQuery = true
    )

    Integer saveUser(
            @Param("userName") String userName,
            @Param("email") String email,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("gender") String gender,
            @Param("isTeacher") boolean isTeacher,
            @Param("dateOfBirth") Date dateOfBirth,
            @Param("userRoleId") Integer userRoleId

    );

    List<User> getUserByEmail(String email);

    Optional<User> getUserByUserId(int userId);

//    @Query(value = "select s.* from showtime s " +
//            "join movie m on s.movie_id = m.movie_id " +
//            "join detail_movie_genre dmg on m.movie_id = dmg.movie_id " +
//            "where dmg.genre_id = :genreId and start_time >= :startTime and end_time <= :endTime ", nativeQuery = true)
//    Optional<List<ShowTime>> getShowTimeByDateAndGenre(
//            @Param("startTime") LocalDateTime start,
//            @Param("endTime") LocalDateTime end,
//            @Param("genreId") Integer genreId);


    boolean existsByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query("update User u set u.firstName = ?1, u.lastName = ?2, u.userName=?3 where u.email = ?4")
    void updateUserByEmail(String firstName, String lastName, String userName, String email);

}
