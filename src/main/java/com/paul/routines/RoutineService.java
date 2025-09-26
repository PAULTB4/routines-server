package com.paul.routines;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoutineService {
    private final RoutineRepository repo;
    private final ClientsApi clients;

    public RoutineService(RoutineRepository repo, ClientsApi clients) {
        this.repo = repo; this.clients = clients;
    }

    public Routine create(Routine r) {
        if (!clients.clientExists(r.getClientId()))
            throw new NoSuchElementException("Client not found");
        return repo.save(r);
    }

    public List<Routine> list() { return repo.findAll(); }

    public List<Routine> byClient(Long clientId) { return repo.findByClientId(clientId); }

    public Routine get(Long id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Routine update(Long id, Routine in) {
        Routine r = get(id);
        // si cambia clientId, volvemos a validar
        if (in.getClientId() != null && !in.getClientId().equals(r.getClientId())) {
            if (!clients.clientExists(in.getClientId())) throw new NoSuchElementException("Client not found");
            r.setClientId(in.getClientId());
        }
        if (in.getName() != null) r.setName(in.getName());
        r.setPlan(in.getPlan());
        return repo.save(r);
    }

    public void delete(Long id) { repo.delete(get(id)); }
}
