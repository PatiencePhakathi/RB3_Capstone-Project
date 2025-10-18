/*Student.java
Student POJO class
Author: S Peck (230207170)
Date: 10/05/2025
 */

package za.ac.cput.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("Student")
public class Student extends User{

    private String studentNumber;
    // Compatibility for tests expecting studentId
    public void setStudentId(String studentId) { this.studentNumber = studentId; }
    public void setStudent(String studentId) { this.studentNumber = studentId; }
    public String getStudentId() { return this.studentNumber; }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Student() {
    }

    public Student (Builder builder) {
        super(builder.password, builder.name, builder.surname, builder.phone, builder.email);
        this.studentNumber = builder.studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    // Compatibility getters for tests
    public String getStudent() { return studentNumber; }





    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", id=" + getUserId() +
                ", password='" + getPassword() + '\'' +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String studentNumber;
        private String password;
        private String name;
        private String surname;
        private String phone;
        private String email;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setStudentNumber(String studentNumber){
            this.studentNumber = studentNumber;
            return this;

        }
        public Builder setPassword(String password){
            this.password = password;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setSurname(String surname){
            this.surname = surname;
            return this;
        }
        public Builder setPhone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder copy(Student student){
            this.id = student.getUserId();
            this.studentNumber = student.getStudentNumber();
            this.password = student.getPassword();
            this.name = student.getName();
            this.surname = student.getSurname();
            this.phone = student.getPhone();
            this.email = student.getEmail();
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }


}

