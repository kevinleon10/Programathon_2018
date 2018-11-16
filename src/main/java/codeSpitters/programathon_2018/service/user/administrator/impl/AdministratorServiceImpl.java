package codeSpitters.programathon_2018.service.user.administrator.impl;

import codeSpitters.programathon_2018.model.Administrator;
import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.service.user.administrator.AdministratorService;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("administratorService")
public class AdministratorServiceImpl extends AbstractService<Administrator> implements UserDetailsService, AdministratorService {

    public AdministratorServiceImpl() {
        super(Administrator.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public void createAdmininistrator(Administrator administrator) {

    }

    public Administrator getAdministrator(String userSsn) {
        Query q = this.getCurrentSession().createQuery("from Administrator where userSsn=:userSsn");
        q.setParameter("userSsn", userSsn);
        try {
            return (Administrator) q.uniqueResult();
        } catch (SQLGrammarException e) {
            throw new UsernameNotFoundException("No user found with ssn " + userSsn);
        }
    }

    public void updateAdministrator(Administrator administrator) {
        super.update(administrator);
    }

}
