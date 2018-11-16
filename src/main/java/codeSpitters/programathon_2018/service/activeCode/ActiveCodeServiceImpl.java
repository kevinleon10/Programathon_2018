package codeSpitters.programathon_2018.service.activeCode;

import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Transactional
//@Service("activeCodeService")
//public class ActiveCodeServiceImpl extends AbstractService<ActivePasswordCodes> implements ActiveCodeService {
//
//    public ActiveCodeServiceImpl() {
//        super(ActivePasswordCodes.class);
//    }
//
//    public void createActiveCode(User user, String code) {
//        ActivePasswordCodes passwordCodes = new ActivePasswordCodes(user, code);
//        this.create(passwordCodes);
//    }
//
//    public ActivePasswordCodes getActiveCode(User user) {
//        String userIdentificator = user.getIdentificator();
//        Query q = this.getCurrentSession().createQuery("from ActivePasswordCodes where userId=:userIdentificator");
//        q.setParameter("userIdentificator", userIdentificator);
//        try {
//            return (ActivePasswordCodes) q.uniqueResult();
//        } catch (SQLGrammarException e) {
//            throw new UsernameNotFoundException("No active password found with username " + userIdentificator);
//        }
//    }
//
//    public void updateActiveCode(ActivePasswordCodes passwordCodes){
//        super.update(passwordCodes);
//    }
//
//    public void deleteActiveCode(ActivePasswordCodes passwordCodes){
//        super.delete(passwordCodes);
//    }
//}
