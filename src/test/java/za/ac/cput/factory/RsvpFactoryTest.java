package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Rsvp.Status;
import za.ac.cput.domain.Event;
import za.ac.cput.domain.Rsvp;
import za.ac.cput.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RsvpFactoryTest {



    private static Rsvp rsvp = RsvpFactory.createRsvp(Status.CONFIRMED, LocalDate.now());

    @Test
    void testCreateRsvp() {

        assertNotNull(rsvp);
        System.out.println(rsvp + " created successfully");
    }

}