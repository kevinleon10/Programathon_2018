package codeSpitters.programathon_2018.service.user;

import codeSpitters.programathon_2018.model.User;
import codeSpitters.programathon_2018.support.service.AbstractService;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends AbstractService<User> implements UserDetailsService, UserService {

    public UserServiceImpl() {
        super(User.class);
    }

    @Override
    public User loadUserByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("Null username sent to loadUserByUsername");

        User user = this.getUser(username);
        if (user == null)
            throw new UsernameNotFoundException("No user found with username " + username);

        return this.search(user);
    }

    public void createUser(String userId, String userName, String userEmail, String userPassword, int userPhone) {
        User user = new User();
        user.setSsn(userId);
        user.setFullName(userName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setPhoneNumber(userPhone);
        this.create(user);
    }

    public User getUser(String username) {
        Query q = this.getCurrentSession().createQuery("from User where email=:username");
        q.setParameter("username", username);
        try {
            return (User) q.uniqueResult();
        } catch (SQLGrammarException e) {
            throw new UsernameNotFoundException("No user found with username " + username);
        }
    }

    public User getUserBySsn(String userSsn){
        Query q = this.getCurrentSession().createQuery("from User where ssn=:ssn");
        q.setParameter("ssn", userSsn);
        try {
            return (User) q.uniqueResult();
        } catch (SQLGrammarException e) {
            throw new UsernameNotFoundException("No user found with user id " + userSsn);
        }
    }

    public void updateUser(User user){
        super.update(user);
    }

}
