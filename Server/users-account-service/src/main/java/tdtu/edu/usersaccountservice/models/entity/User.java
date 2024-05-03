package tdtu.edu.usersaccountservice.models.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tdtu.edu.usersaccountservice.models.dto.Gender;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "is_teacher")
    private boolean isTeacher;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    //Relationship schema
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_notification", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private Set<Notification> listNotification = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, targetEntity = UserRole.class)
    @JoinColumn(name = "user_role_id", nullable = false)
    @JsonBackReference(value = "user-customer")
    private UserRole userRole;

//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, targetEntity = UserRole.class)
//    @JsonBackReference(value = "booking-customer")
//    private List<Booking> bookings;
}
