package by.rest.repository.inmemory;

import by.rest.repository.CrudRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InMemoryBaseRepository<T> implements CrudRepository<T, Long> {
    protected final Map<Long, T> storage = new ConcurrentHashMap<>();
    protected final AtomicLong seq = new AtomicLong(0);
    private final Function<T, Long> getId;
    private final BiConsumer<T, Long> setId;

    public InMemoryBaseRepository(Function<T, Long> getId, BiConsumer<T, Long> setId) {
        this.getId = getId;
        this.setId = setId;
    }

    @Override
    public T save(T entity) {
        Long id = getId.apply(entity);
        if (id == null) {
            id = seq.incrementAndGet();
            setId.accept(entity, id);
        }
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T update(Long id, T entity) {
        setId.accept(entity, id);
        storage.put(id, entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
