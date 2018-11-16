package codeSpitters.programathon_2018.service.antecedent;
import codeSpitters.programathon_2018.model.Antecedent;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("antecedentService")
public class AntecedentServiceImpl extends AbstractService<Antecedent> implements AntecedentService {

    public AntecedentServiceImpl() {
        super(Antecedent.class);
    }

    public void createAntecedent(Antecedent antecedent) {
        super.create(antecedent);
    }

    public Antecedent getAntecedent(String antecedentId) {
        Query q = this.getCurrentSession().createQuery("from Antedecent where id=:antecedentId");
        q.setParameter("antecedentId", antecedentId);
        try {
            return (Antecedent) q.uniqueResult();
        } catch (SQLGrammarException e) {
            throw new UsernameNotFoundException("No antecedent found with id " + antecedentId);
        }
    }

    public void updateAntecedent(Antecedent antecedent) {
        super.update(antecedent);
    }

    public List<Antecedent> getAntecedents(String ssn) {
        Query q = this.getCurrentSession().createQuery("SELECT AN FROM Antecedent AN JOIN Children CH ON CH.ssn = AN.children.ssn where AN.children.ssn = :ssn");
        q.setParameter("ssn", ssn);
        return (List<Antecedent>) q.list();
    }

}
