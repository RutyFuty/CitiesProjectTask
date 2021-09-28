package ru.sberbank.DSMelnikov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.sberbank.DSMelnikov.model.City;
import ru.sberbank.DSMelnikov.util.HibernateSessionFactoryUtil;

import javax.persistence.PersistenceException;
import java.util.List;

public class CityDao {

    public void save(City city) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(city);
        transaction.commit();
        session.close();
    }

    public void saveAll(List<City> cities) {
        for (City city : cities) {
            try {
                this.save(city);
            } catch (PersistenceException e) {
                System.out.printf("Город %s уже внесен в базу данных или совпадает id = %d\n", city.getName(), city.getId());
            }
        }

    }

    public List<City> findAll() {
        return (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From City").list();
    }

    public List<City> findAllSorted() {
        return (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From City c order by c.name desc").list();
    }
}
