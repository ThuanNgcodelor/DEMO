package com.mypack.repo;

import com.mypack.models.Registrant;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryRegistrantRepo {
    private final List<Registrant> data = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Registrant> findAll() { return new ArrayList<>(data); }

    public Registrant add(Registrant r) {
        r.setId(seq.getAndIncrement());
        data.add(r);
        return r;
    }

    public Optional<Registrant> findById(Long id) {
        return data.stream().filter(x -> Objects.equals(x.getId(), id)).findFirst();
    }

    public boolean update(Long id, Registrant updated) {
        return findById(id).map(old -> {
            old.setFullName(updated.getFullName());
            old.setEmail(updated.getEmail());
            old.setWorkshopTitle(updated.getWorkshopTitle());
            return true;
        }).orElse(false);
    }

    public boolean delete(Long id) {
        return data.removeIf(x -> Objects.equals(x.getId(), id));
    }
}