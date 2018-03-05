//package org.springframework.samples.app;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.samples.app.person.Person;
//import org.springframework.samples.app.person.PersonRepository;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collection;
//import java.util.UUID;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicationTest {
//
//    @Autowired
//    PersonRepository repo;
//
//    @Test
//    public void check(){
//        Collection s = repo.findByLastName("Franklin");
//
//        Person person = (Person) s.iterator().next();
//
//
//
//        System.out.println(person.getFirstName());
//    }
//}
