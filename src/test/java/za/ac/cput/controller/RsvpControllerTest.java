package za.ac.cput.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Rsvp.Status;
import za.ac.cput.domain.Rsvp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RsvpControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String BASE_URL = "/api/rsvp";

    private static Rsvp testRsvp;
    private static String createdRsvpId;

    // A simple builder class for the Rsvp domain object
    private static class RsvpBuilder {
        private String rsvpId;
        private String name;
        private Status status;
        private LocalDateTime createdDate;

        public RsvpBuilder withId(String id) { this.rsvpId = id; return this; }
        public RsvpBuilder withName(String name) { this.name = name; return this; }
        public RsvpBuilder withStatus(Status status) { this.status = status; return this; }
        public RsvpBuilder withCreatedDate(LocalDateTime date) { this.createdDate = date; return this; }

        public Rsvp build() {
            return new Rsvp.Builder()
                    .setRsvpId(this.rsvpId)
                    .setStatus(this.status)
                    .setRsvpDate(this.createdDate.toLocalDate())
                    .build();
        }

    }

    @BeforeAll
    static void setUp() {
        // Create a valid RSVP object for testing
        testRsvp = new RsvpBuilder()
                .withName("Darling Ashton")
                .withStatus(Status.CONFIRMED)
                .withCreatedDate(LocalDateTime.now())
                .build();
    }

    @Test
    @Order(1)
    void create() {
        // Send a POST request to the create endpoint
        ResponseEntity<Rsvp> response = restTemplate.postForEntity(BASE_URL, testRsvp, Rsvp.class);

        // Assert that the HTTP status code is OK as per the current controller implementation
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body is not null and has a valid ID
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getRsvpId());

        // Store the generated ID for subsequent tests
        createdRsvpId = response.getBody().getRsvpId();
        System.out.println("Created RSVP with ID: " + createdRsvpId);
    }

    @Test
    @Order(2)
    void createWithInvalidStatus() {
        Rsvp invalidRsvp = new Rsvp.Builder().setStatus(null).build();
        ResponseEntity<Rsvp> response = restTemplate.postForEntity(BASE_URL, invalidRsvp, Rsvp.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Order(3)
    void read() {
        // Send a GET request to read the created RSVP by its ID
        ResponseEntity<Rsvp> response = restTemplate.getForEntity(BASE_URL + "/" + createdRsvpId, Rsvp.class);

        // Assert that the HTTP status code is OK and the returned object matches
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdRsvpId, response.getBody().getRsvpId());
        System.out.println("Read RSVP: " + response.getBody());
    }

    @Test
    @Order(4)
    void getAll() {
        // Send a GET request to get all RSVPs
        ResponseEntity<List> response = restTemplate.getForEntity(BASE_URL, List.class);

        // Assert that the HTTP status code is OK and the list is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        System.out.println("Found " + response.getBody().size() + " RSVPs.");
    }

    // The update and delete tests are commented out because the corresponding
    // controller methods are also commented out. Uncomment these tests once
    // the controller methods are enabled.

//    @Test
//    @Order(5)
//    void update() {
//        // Read the existing object to modify it
//        Rsvp rsvpToUpdate = restTemplate.getForEntity(BASE_URL + "/" + createdRsvpId, Rsvp.class).getBody();
//        assertNotNull(rsvpToUpdate);
//
//        // Update the status to DECLINED
//        rsvpToUpdate.setStatus(Status.DECLINED);
//
//        // Create an HTTP entity with the updated object
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<Rsvp> requestEntity = new HttpEntity<>(rsvpToUpdate, headers);
//
//        // Send a PUT request to the update endpoint
//        ResponseEntity<Rsvp> response = restTemplate.exchange(
//                BASE_URL + "/" + createdRsvpId,
//                HttpMethod.PUT,
//                requestEntity,
//                Rsvp.class);
//
//        // Assert that the HTTP status code is OK and the update was successful
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(Status.DECLINED, response.getBody().getStatus());
//        System.out.println("Updated RSVP: " + response.getBody());
//    }
//
//    @Test
//    @Order(6)
//    void delete() {
//        // Send a DELETE request to remove the RSVP
//        restTemplate.delete(BASE_URL + "/" + createdRsvpId);
//
//        // Attempt to read the item again to confirm it's deleted
//        ResponseEntity<Rsvp> response = restTemplate.getForEntity(BASE_URL + "/" + createdRsvpId, Rsvp.class);
//
//        // Assert that the HTTP status code is NOT_FOUND
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        System.out.println("Successfully deleted RSVP with ID: " + createdRsvpId);
//    }
}
