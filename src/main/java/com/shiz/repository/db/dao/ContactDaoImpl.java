package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.*;
import com.shiz.model.data.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 23.05.2017.
 */
@Component
public class ContactDaoImpl implements ContactDao {
    private SessionFactory sessionFactory;

    public ContactDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addContactList(int deviceId, List<Contact> contactList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (Contact contact : contactList) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<ContactBookEntity> criteria = cb.createQuery(ContactBookEntity.class);
                Root<ContactBookEntity> appEntityRoot = criteria.from(ContactBookEntity.class);
                Predicate p = cb.and(cb.equal(appEntityRoot.get(ContactBookEntity_.name), contact.getName())
                        ,(cb.equal(appEntityRoot.get(ContactBookEntity_.devDbId),contact.getDbId()))
                        ,(cb.equal(appEntityRoot.get(ContactBookEntity_.contactBookByDeviceId),deviceId)));
                criteria.where(p);

                ContactBookEntity contactBookEntity = session.createQuery(criteria).getSingleResult();
                if (contactBookEntity == null) {
                    ContactBookEntity contactEntity = new ContactBookEntity();
                    contactEntity.setDevDbId(contact.getDbId());
                    contactEntity.setEMail(contact.getEMail());
                    contactEntity.setInfo(contact.getInfo());
                    contactEntity.setName(contact.getName());
                    contactEntity.setNumber(contact.getNumber());
                    contactEntity.setHomeNumber(contact.getHomeNumber());
                    contactEntity.setMainNumber(contact.getMainNumber());
                    contactEntity.setWorkNumber(contact.getWorkNumber());
                    contactEntity.setContactBookByDeviceId(deviceEntity);
                    deviceEntity.addContactByDeviceId(contactEntity);
                    session.save(contactEntity);

                } else {
                    contactBookEntity.setEMail(contact.getEMail());
                    contactBookEntity.setInfo(contact.getInfo());
                    contactBookEntity.setHomeNumber(contact.getHomeNumber());
                    contactBookEntity.setMainNumber(contact.getMainNumber());
                    contactBookEntity.setWorkNumber(contact.getWorkNumber());
                    session.saveOrUpdate(contactBookEntity);
                }
                session.flush();
                session.clear();
                session.saveOrUpdate(deviceEntity);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Contact> getContactList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<ContactBookEntity> contactBookEntities = deviceEntity.getContactBookByDeviceId();
            List<Contact> contactList = new ArrayList<>();
            for (ContactBookEntity contactBookEntity : contactBookEntities) {
                Contact contact = new Contact();
                contact.setEMail(contactBookEntity.getEMail());
                contact.setNumber(contactBookEntity.getNumber());
                contact.setDbId(contactBookEntity.getDevDbId());
                contact.setInfo(contactBookEntity.getInfo());
                contact.setName(contactBookEntity.getName());
                contact.setHomeNumber(contactBookEntity.getHomeNumber());
                contact.setWorkNumber(contactBookEntity.getWorkNumber());
                contact.setMainNumber(contactBookEntity.getMainNumber());
                contactList.add(contact);
            }
            return contactList;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
