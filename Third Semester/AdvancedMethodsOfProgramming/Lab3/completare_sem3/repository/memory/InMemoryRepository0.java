package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository0;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryRepository0<ID, E extends Entity<ID>> implements Repository0<ID,E> {


    Map<ID,E> entities;

    public InMemoryRepository0() {

        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        if(entities.get(entity.getId()) != null) {
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Null Id cannot be deleted");
        }

        this.entities.remove(id);
        return null;
    }

    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");


        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;

    }

    public Set<Map.Entry<ID, E>> getAll() {

        return this.entities.entrySet();
    }

    public E getEntity (ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Null id cannot be searched for.");
        }
        return this.entities.get(id);
    }

}
