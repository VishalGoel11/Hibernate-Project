package com.company.Mavon2;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scan = new Scanner(System.in);
      Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
      SessionFactory sess= con.buildSessionFactory();
      Session session= sess.openSession();
      Transaction t = session.beginTransaction();
      Student s=null;
      while(true) {
      System.out.println("press 1 for insertion ");
      System.out.println("press 2 for deletion ");
      System.out.println("press 3 for fetching ");
      System.out.println("press 4 for updating ");
      System.out.println("press 5 for exit ");
      int a=scan.nextInt();
      if(a==5) {
    	  break;
      }
      if(a==1) {
    	  s= new Student();
    	  System.out.println("Enter the roll Number of the student : ");
    	  int b= scan.nextInt();
    	  s.setRollNo(b);
    	  
    	  System.out.println("Enter the First name of the student : ");
    	  String c= scan.next();
    	  s.setFirstNmae(c);
    	  
    	  System.out.println("Enter the last name of the student : ");
    	  String d= scan.next();
    	  s.setLastName(d);
    	  
    	  System.out.println("Enter the class name of the student : ");
    	  String e= scan.next();
    	  s.setClassName(e);
    	  
    	  session.save(s);
    	  t.commit();
    	  
      }
      
      
    if(a==3) {
    	
    	org.hibernate.Query q=session.createQuery("from Student");
    	List<Student> list = q.list();
    	System.out.println("Enter the roll number of the student : ");
    	int rollNo= scan.nextInt();
    	for(Student std : list) {
    		if(rollNo==std.getRollNo()) {
    			System.out.println(rollNo+" "+std.getFirstNmae()+" "+std.getLastName()+" "+std.getClassName());
    			break;
    		}
    	}
    	
    }
if(a==4) {
    	Transaction tt= session.beginTransaction();
    	org.hibernate.Query q=session.createQuery("from Student");
    	List<Student> list = q.list();
    	System.out.println("Enter the roll number of the student whose value yo want to update : ");
    	int rollNo= scan.nextInt();
    	
    	System.out.println("Enter the updated first name : ");
    	String name= scan.next();
    	
    	System.out.println("Enter the updated last name : ");
    	String lname= scan.next();
    	
    	System.out.println("Enter the updated class name : ");
    	String cname= scan.next();
    	for(Student std : list) {
    		if(rollNo==std.getRollNo()) {
    			std.setClassName(cname);
    			std.setFirstNmae(name);
    			std.setLastName(lname);
    			break;
    		}
    	}
    	int p=0;
    	for(Student std : list) {
    		Student st=list.get(p);
    		p++;
    		session.save(st);
    	}
    	tt.commit();
    	
    }
      }
      session.close();
	  sess.close();
    }
}
