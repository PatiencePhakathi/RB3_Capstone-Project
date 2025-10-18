package za.ac.cput.domain;

import jakarta.persistence.*;

import za.ac.cput.domain.Role;
import za.ac.cput.domain.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "User_Type")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Long userId;

    @Column(name = "Name",nullable = false)
    private String name;
    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Password", nullable = false)
    private String password;
    @Column(name = "Phone", nullable = false)
    private String phone;
    @Column(name = "Student_Number", unique = true, nullable = true)
    private String studentNumber;
    @Column(name = "Staff_Number", unique = true, nullable = true)
    private String staffNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();


   protected User() {}

   protected User(String password, String name, String surname, String phone, String email) {
       this.password = password;
       this.name = name;
       this.surname = surname;
       this.phone = phone;
       this.email = email;
   }

    private User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.studentNumber = builder.studentNumber;
        this.staffNumber = builder.staffNumber;
        this.userRoles = builder.userRoles;


    }


    public Long getUserId() { return userId; }
    public Long getId() { return userId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getStudentNumber() { return studentNumber; }
    public String getStaffNumber() { return staffNumber; }
    public Set<UserRole> getUserRoles() { return userRoles; }

    // Helper Method
    public void addRole(Role role) {
        UserRole userRole = new UserRole(this, role);
        userRoles.add(userRole);
        role.getUserRoles().add(userRole);
    }

    public void removeUserRole(UserRole userRole) {
        userRoles.remove(userRole);
        userRole.setUser(null);
    }

    public boolean hasRole(String roleName) {
       if (roleName == null) return false;
       return userRoles.stream()
               .anyMatch(ur -> ur.getRole().getRoleName().equalsIgnoreCase(roleName));
    }

    public Set<String> getRoleNames() {
       return userRoles.stream()
               .map(ur -> ur.getRole().getRoleName())
               .collect(Collectors.toSet());
    }


    public static class Builder {
        private Long userId;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String phone;
        private String studentNumber;
        private String staffNumber;
        private Set<UserRole> userRoles = new HashSet<>();

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
            return this;
        }
        public Builder setStaffNumber(String staffNumber) {
            this.staffNumber = staffNumber;
            return this;
        }

        public Builder setUserRoles(Set<UserRole> userRoles) {
            this.userRoles = userRoles;
            return this;
        }
        public Builder copy(User user) {
            this.userId = user.userId;
            this.name = user.name;
            this.surname = user.surname;
            this.email = user.email;
            this.password = user.password;
            this.phone = user.phone;
            this.studentNumber = user.studentNumber;
            this.staffNumber = user.staffNumber;
            this.userRoles = user.userRoles;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }


}






