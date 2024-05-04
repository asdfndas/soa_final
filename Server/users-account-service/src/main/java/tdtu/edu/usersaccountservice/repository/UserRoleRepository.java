package tdtu.edu.usersaccountservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tdtu.edu.usersaccountservice.models.entity.UserRole;

import java.util.Date;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole save(UserRole userRole);

    UserRole getById(Integer roleId);

    @Modifying
    @Transactional
    @Query("update UserRole u set u.createdAt = ?1, u.roleName = ?2 where u.id = ?3")
    UserRole updateUserRoleById(Date createAt, String roleName, Integer id);


    void deleteById(Integer roleId);

}
