package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Rsvp;
import za.ac.cput.repository.RsvpRepository;

import java.util.List;

@Service
public class RsvpServiceImpl implements RsvpService {

    private final RsvpRepository repository;

    @Autowired
    public RsvpServiceImpl(RsvpRepository repository) {
        this.repository = repository;
    }

    @Override
    public Rsvp create(Rsvp rsvp) {
        return this.repository.save(rsvp);
    }

    @Override
    public Rsvp read(String s) {
        return this.repository.findById(s).orElse(null);
    }

    @Override
    public Rsvp update(Rsvp rsvp) {
        return this.repository.save(rsvp);
    }

    @Override
    public List<Rsvp> getAll() {
        return this.repository.findAll();
    }

    @Override
    public boolean delete(String id) {
        this.repository.deleteById(id);
        return true;
    }
}