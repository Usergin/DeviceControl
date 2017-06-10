package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.UserEntity;
import com.shiz.model.Authentication;
import com.shiz.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Base64;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

/**
 * Created by OldMan on 09.06.2017.
 */
@Component
public class UserDaoImpl implements UserDao {
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
            session.save(userEntity);
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
            Criteria userCriteria = session.createCriteria(UserEntity.class)
                    .add(Restrictions.eq("login", authentication.getLogin()))
                    .add(Restrictions.eq("password",authentication.getPassword()));

            UserEntity userEntity = (UserEntity) userCriteria.uniqueResult();
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
