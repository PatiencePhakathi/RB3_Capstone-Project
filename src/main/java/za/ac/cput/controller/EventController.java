/* Event Controller.java
 * Event Controller class
 * Author: Nobahle Vuyiswa Nzimande (222641533)
 * Date: 25 May 2025
 */
package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Event;
import za.ac.cput.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event createdEvent = eventService.create(event);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> read(@PathVariable String id) {
        Event event = eventService.read(id);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Event> update(@RequestBody Event event) {
        Event updatedEvent = eventService.update(event);
        return updatedEvent != null ? ResponseEntity.ok(updatedEvent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = eventService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> events = eventService.getAll();
        return ResponseEntity.ok(events);
    }
}