package codeSpitters.programathon_2018.support.service;

import org.hibernate.Session;

import java.util.List;

public interface Service<T> {
    public List< T > findAll();
    public void create( T entity );
    public void update( T entity );
    public void delete( T entity );
    public void deleteById( long entityId );
    public Session getCurrentSession();
    public T search(T searchedObject);
}
