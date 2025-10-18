package za.ac.cput.factory;

import za.ac.cput.domain.Rsvp;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class RsvpFactory {

    public static Rsvp createRsvp(Rsvp.Status status , LocalDate rsvpDate) {
        if (status == null) {
           return  null;
        }

        String rsvpId = Helper.generateId();
        return new Rsvp.Builder()
                .setRsvpId(rsvpId)
                .setStatus(status)
                .setRsvpDate(rsvpDate)
                .build();
    }

    // Compatibility overload for older tests: (studentId, eventId, statusName)
    public static Rsvp createRsvp(String studentId, String eventId, String statusName) {
        Rsvp.Status status;
        try {
            status = Rsvp.Status.valueOf(statusName);
        } catch (IllegalArgumentException e) {
            status = Rsvp.Status.PENDING;
        }
        return new Rsvp.Builder()
                .setRsvpID(Helper.generateId())
                .setStudentID(studentId)
                .setEventID(eventId)
                .setStatus(status)
                .setRsvpDate(LocalDate.now())
                .build();
    }
}