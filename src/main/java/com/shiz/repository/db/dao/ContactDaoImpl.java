package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.*;
import com.shiz.model.data.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
                        ,(cb.equal(appEntityRoot.get(ContactBookEntity_.devDbId),contact.getDb_id()))
                        ,(cb.equal(appEntityRoot.get(ContactBookEntity_.contactBookByDeviceId),deviceId)));
                criteria.where(p);

                ContactBookEntity contactBookEntity = session.createQuery(criteria).getSingleResult();
                if (contactBookEntity == null) {
                    ContactBookEntity contactEntity = new ContactBookEntity();
                    contactEntity.setDevDbId(contact.getDb_id());
                    contactEntity.setEMail(contact.getE_mail());
                    contactEntity.setInfo(contact.getInfo());
                    contactEntity.setName(contact.getName());
                    contactEntity.setNumber(contact.getNumber());
                    contactEntity.setHomeNumber(contact.getHome_number());
                    contactEntity.setMainNumber(contact.getMain_number());
                    contactEntity.setWorkNumber(contact.getWork_number());
                    contactEntity.setContactBookByDeviceId(deviceEntity);
                    deviceEntity.addContactByDeviceId(contactEntity);
                    session.save(contactEntity);

                } else {
                    contactBookEntity.setEMail(contact.getE_mail());
                    contactBookEntity.setInfo(contact.getInfo());
                    contactBookEntity.setHomeNumber(contact.getHome_number());
                    contactBookEntity.setMainNumber(contact.getMain_number());
                    contactBookEntity.setWorkNumber(contact.getWork_number());
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
                contact.setE_mail(contactBookEntity.getEMail());
                contact.setNumber(contactBookEntity.getNumber());
                contact.setDb_id(contactBookEntity.getDevDbId());
                contact.setInfo(contactBookEntity.getInfo());
                contact.setName(contactBookEntity.getName());
                contact.setHome_number(contactBookEntity.getHomeNumber());
                contact.setWork_number(contactBookEntity.getWorkNumber());
                contact.setMain_number(contactBookEntity.getMainNumber());
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
