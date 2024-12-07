package com.klef.jfsd.exam.Hibernate_HCQL;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Insert records
        Customer customer1 = new Customer();
        customer1.setName("Swarna");
        customer1.setEmail("swarna@gmail.com");
        customer1.setAge(25);
        customer1.setLocation("Vijayawada");

        Customer customer2 = new Customer();
        customer2.setName("Susmitha");
        customer2.setEmail("susmitha@gmail.com");
        customer2.setAge(30);
        customer2.setLocation("Guntur");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Criteria Queries
        System.out.println("\nEqual Restriction:");
        List<Customer> customersEqual = session.createCriteria(Customer.class)
                .add(Restrictions.eq("location", "Vijayawada"))
                .list();
        customersEqual.forEach(System.out::println);

        System.out.println("\nNot Equal Restriction:");
        List<Customer> customersNotEqual = session.createCriteria(Customer.class)
                .add(Restrictions.ne("age", 30))
                .list();
        customersNotEqual.forEach(System.out::println);

        System.out.println("\nGreater Than Restriction:");
        List<Customer> customersGreaterThan = session.createCriteria(Customer.class)
                .add(Restrictions.gt("age", 20))
                .list();
        customersGreaterThan.forEach(System.out::println);

        System.out.println("\nLess Than Restriction:");
        List<Customer> customersLessThan = session.createCriteria(Customer.class)
                .add(Restrictions.lt("age", 30))
                .list();
        customersLessThan.forEach(System.out::println);

        System.out.println("\nBetween Restriction:");
        List<Customer> customersBetween = session.createCriteria(Customer.class)
                .add(Restrictions.between("age", 20, 30))
                .list();
        customersBetween.forEach(System.out::println);

        System.out.println("\nLike Restriction:");
        List<Customer> customersLike = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", "A%"))
                .list();
        customersLike.forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}
