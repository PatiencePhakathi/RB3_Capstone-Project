/*Organizer.java
Admin POJO class
Author: OP Phologane (230690939)
Date: 10/05/2025
 */
package za.ac.cput.domain;


import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("Organizer")
public class Organizer extends User {
    public enum OrganizerType {
        STUDENT_CLUB,
        FACULTY,
        CORPORATION
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Organizer_Type", nullable = true)
    private OrganizerType organizerType;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    //@JoinColumn(name = "user_id")
//    private List<Event> events; // An Organizer can manage multiple events

    public Organizer() {

    }

    private Organizer(Builder builder) {
        super(builder.password, builder.name, builder.surname, builder.phone, builder.email);
        this.organizerType = builder.organizerType;
    }

    public OrganizerType getOrganizerType() {
        return organizerType;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                " id=" + getUserId() +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", organizerType=" + organizerType +
                '}';
    }

    public static class Builder {
        private long id;
        private String password;
        private String name;
        private String surname;
        private String phone;
        private String email;
        private OrganizerType organizerType;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setOrganizerType(OrganizerType organizerType) {
            this.organizerType = organizerType;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
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

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder copy(Organizer organizer) {
            this.id = organizer.getUserId() == null ? 0L : organizer.getUserId();
            this.password = organizer.getPassword();
            this.name = organizer.getName();
            this.surname = organizer.getSurname();
            this.phone = organizer.getPhone();
            this.email = organizer.getEmail();
            this.organizerType = organizer.organizerType;
            return this;
        }

        public Organizer build() {
            return new Organizer(this);
        }
    }
}
