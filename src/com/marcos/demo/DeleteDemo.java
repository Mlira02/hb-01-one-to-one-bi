package com.marcos.demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();
            int theId = 2;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            System.out.println("Found instructor " + tempInstructor);

            if(tempInstructor != null)
            {
                System.out.println("Deleting " + tempInstructor);
//                Deletion of this instructor will also delete instructor detail for this instructor as well
//                CASACADE.ALL is selected for the instructor currently
                session.delete(tempInstructor);
            }
            else
            {
                System.out.println("instructor was not found...");
            }

            session.getTransaction().commit();
        }
        finally
        {
            System.out.println("All tasks completed...");
            factory.close();
        }
    }
}
