package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Rsvp {

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }

    @Id
    private String rsvpId;
    private LocalDate rsvpDate;

    @Enumerated(EnumType.STRING)
    private Status status;


    public Rsvp() {}

    private Rsvp(Builder builder) {
        this.rsvpId = builder.rsvpId;
        this.status = builder.status;
        this.rsvpDate = builder.rsvpDate;
    }

    // Getters
    public String getRsvpId() { return rsvpId; }
    // Compatibility for tests
    public String getRsvpID() { return rsvpId; }
    public Status getStatus() { return status; }
    public LocalDate getRsvpDate() { return rsvpDate; }

    // Setters
    public void setRsvpId(String rsvpId) { this.rsvpId = rsvpId; }
    // Compatibility for tests
    public void setRsvpID(String rsvpId) { this.rsvpId = rsvpId; }
    public void setStatus(Status status) { this.status = status; }
    public void setRsvpDate(LocalDate rsvpDate) { this.rsvpDate = rsvpDate; }

    @Override
    public String toString() {
        return "Rsvp{" +
                "rsvpId='" + rsvpId + '\'' +
                ", rsvpDate=" + rsvpDate +
                ", status=" + status +
                '}';
    }

    // Builder class
    public static class Builder {
        private String rsvpId;
        private Status status;
        private LocalDate rsvpDate;
        // The following fields are kept for compatibility with older tests
        private String studentId;
        private String eventId;


        public Builder setRsvpId(String rsvpId) {
            this.rsvpId = rsvpId;
            return this;
        }

        // Compatibility with older tests
        public Builder setRsvpID(String rsvpId) {
            this.rsvpId = rsvpId;
            return this;
        }

        // Compatibility with older tests
        public Builder setStudentID(String studentId) {
            this.studentId = studentId;
            return this;
        }

        // Compatibility with older tests
        public Builder setEventID(String eventId) {
            this.eventId = eventId;
            return this;
        }



        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setRsvpDate(LocalDate rsvpDate) {
            this.rsvpDate = rsvpDate;
            return this;
        }

        public Builder copy(Rsvp rsvp) {
            this.rsvpId = rsvp.rsvpId;
            this.status = rsvp.status;
            this.rsvpDate = rsvp.rsvpDate;
            return this;
        }

        public Rsvp build() {
            return new Rsvp(this);
        }
    }
}