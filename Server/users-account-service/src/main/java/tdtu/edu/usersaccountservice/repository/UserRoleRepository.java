package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.UserRole;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole save(UserRole userRole);

    UserRole getById(Integer roleId);

    @Query(value = "SELECT r.* FROM public.user_role r JOIN public.user u ON r.user_role_id = u.user_role_id WHERE u.email = :email", nativeQuery = true)
    Optional<UserRole> getUserRoleByEmail(@Param("email") String email);


    @Modifying
    @Transactional
    @Query("update UserRole u set u.createdAt = ?1, u.roleName = ?2 where u.id = ?3")
    void updateUserRoleById(Date createAt, String roleName, Integer id);



    void deleteById(Integer roleId);
}
