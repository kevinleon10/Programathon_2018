package codeSpitters.programathon_2018.service.user.responsible;

import codeSpitters.programathon_2018.model.Responsible;
import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("responsibleService")
public class ResponsibleServiceImpl extends AbstractService<Responsible> implements ResponsibleService {

    public ResponsibleServiceImpl() {
        super(Responsible.class);
    }

    public void createPatient(User user) {
        Responsible patient = new Responsible(user);
        this.create(patient);
    }

    @Override
    public void createResponsible(User user) {
        Responsible responsible = new Responsible();
        responsible.setUser(user);
        this.create(responsible);
    }

    @Override
    public Responsible getResponsible(String userSsn) {
        Query q = this.getCurrentSession().createQuery("from Responsible where userSsn = :userSsn");
        q.setParameter("userSsn", userSsn);
        try {
            return (Responsible) q.uniqueResult();
        } catch (SQLGrammarException e) {
            throw new UsernameNotFoundException("No responsible found with username " + userSsn);
        }
    }

    @Override
    public void updateResponsible(Responsible responsible) {
        super.update(responsible);
    }

}
