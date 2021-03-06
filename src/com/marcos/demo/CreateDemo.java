package com.marcos.demo;

import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo
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

            int theId = 3;

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
            System.out.println("Associated Instructor: " + tempInstructorDetail.getInstructor());

            //  This line breaks the relationship between instructor and instructorDetail
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            session.delete(tempInstructorDetail);
            session.getTransaction().commit();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            System.out.println("All tasks completed...");
            session.close();
            factory.close();
        }
    }
}
