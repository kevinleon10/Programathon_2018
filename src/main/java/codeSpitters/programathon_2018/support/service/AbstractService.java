package codeSpitters.programathon_2018.support.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@EnableTransactionManagement
public abstract class AbstractService<T>{

    private Class< T > clazz;

    @Autowired
    SessionFactory sessionFactory;

    public AbstractService(Class< T > clazzToSet) {
        clazz = clazzToSet;
    }

    public T findOne( long id ){
        return (T) getCurrentSession().get( clazz, id );
    }

    public List< T > findAll(){
        System.out.println(clazz);
        return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
    }

    public void create( T entity ){
        getCurrentSession().persist( entity );
    }

    public void update( T entity ){
        getCurrentSession().merge( entity );
    }

    public void delete( T entity ){
        getCurrentSession().delete( entity );
    }

    public void deleteById( long entityId ) {
        T entity = findOne( entityId );
        delete( entity );
    }

    public final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public T search(T searchedObject) {
        return (T) sessionFactory.getCurrentSession().createCriteria(searchedObject.getClass()).add(Example.create(searchedObject)).uniqueResult();
    }

}
