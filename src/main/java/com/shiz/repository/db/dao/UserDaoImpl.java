package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.UserEntity;
import com.shiz.entity.UserEntity_;
import com.shiz.model.Authentication;
import com.shiz.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by OldMan on 09.06.2017.
 */
@Component
public class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    private EntityManager em;

    private SessionFactory sessionFactory;

    public UserDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addUser(User user) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            UserEntity userEntity = new UserEntity();
            userEntity.setDepartmen(user.getDepartment());
            userEntity.setLogin(user.getLogin());
            userEntity.setPassword(getHashedValue(user.getPassword()));
            userEntity.setRank(user.getRank());
            userEntity.setUsername(user.getUsername());
            session.persist(userEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User getUser(Authentication authentication) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);
            Root<UserEntity> userRoot = criteria.from(UserEntity.class);
            Predicate p = cb.and(cb.equal(userRoot.get(UserEntity_.login), authentication.getLogin())
                    ,(cb.equal(userRoot.get(UserEntity_.password), authentication.getPassword())));
            criteria.where(p);

            UserEntity userEntity = session.createQuery(criteria).getSingleResult();
            User user = new User();
            user.setLogin(userEntity.getLogin());
            user.setDepartment(userEntity.getDepartmen());
            user.setRank(userEntity.getRank());
            user.setUsername(userEntity.getUsername());
            return user;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private String getHashedValue(String inputData) {
        String sResp = null;
        try {
            byte byteHash[];
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.update(inputData.getBytes("utf-8"));
            byteHash = sha1.digest();
            sha1.reset();
            return Base64.getEncoder().encodeToString(byteHash);
        } catch (Exception e) {
            System.err.println("getHashedValue failed: " + e.getMessage());
            return null;
        }
    }

}
