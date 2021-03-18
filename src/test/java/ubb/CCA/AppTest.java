package ubb.CCA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubb.CCA.domain.Nota;
import ubb.CCA.domain.Student;
import ubb.CCA.domain.Tema;
import ubb.CCA.repository.NotaXMLRepository;
import ubb.CCA.repository.StudentXMLRepository;
import ubb.CCA.repository.TemaXMLRepository;
import ubb.CCA.service.Service;
import ubb.CCA.validation.NotaValidator;
import ubb.CCA.validation.StudentValidator;
import ubb.CCA.validation.TemaValidator;
import ubb.CCA.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * EC for [group] variable
 * variable < 100                    This should not work
 * variable == 100                   This should work
 * variable > 100 && variable < 999  This should work
 * variable === 999                  This should work
 * variable > 999                    This should not work
 */

public class AppTest 
{
    final Validator<Student> studentValidator = new StudentValidator();
    final Validator<Tema> temaValidator = new TemaValidator();
    final Validator<Nota> notaValidator = new NotaValidator();
    final StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
    final TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
    final NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");
    final Service service = new Service(studentRepo, temaRepo, notaRepo);
    String reallyLongString;

    public AppTest() {
        char[] fill = new char [Integer.MAX_VALUE / 10];
        Arrays.fill(fill, 'a');
        reallyLongString = new String(fill);
    }

    @Test
    public void tc_01()
    {
        assertEquals(1, service.saveStudent("10", "TestStudent", 932));
        studentRepo.delete("10");
    }

//    @Test
//    public void tc_UnsuccesfulAddBadGroup() {
//        assertEquals(0, service.saveStudent("10", "TestStudent", 4));
//        studentRepo.delete("10");
//        Validator<Student> studentValidator = new StudentValidator();
//        Validator<Tema> temaValidator = new TemaValidator();
//        Validator<Nota> notaValidator = new NotaValidator();
//
//        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
//        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
//        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");
//
//        Service service = new Service(studentRepo, temaRepo, notaRepo);
//
//        assertEquals(1, service.saveStudent("5", "Eu Eulescu", 234));
//
//        studentRepo.delete("5");
//    }

    @Test
    public void tc_07() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        assertEquals(0, service.saveStudent("15", "BVA5", 99));
        studentRepo.delete("15");
    }

    @Test
    public void tc_09() {
        assertEquals(1, service.saveStudent("21", "N", 245));
        // Don't clean up - tc_10 depends on tc_09
    }

    @Test
    public void tc_10() {
        // Check if same id fails
        assertEquals(0, service.saveStudent("21", "N", 245));
        studentRepo.delete("21");
    }

    @Test
    public void tc_11() {
        assertEquals(0, service.saveStudent("12", "Eu Eulescu", null));
    }

    @Test
    public void tc_12() {
        assertEquals(1, service.saveStudent("67", "BVA1", 765));
        studentRepo.delete("67");
    }

    @Test
    public void tc_13() {
        assertEquals(1, service.saveStudent(reallyLongString.substring(0, reallyLongString.length()-1), "BVA1", 765));
        studentRepo.delete(reallyLongString.substring(0, reallyLongString.length()-1));
    }

    @Test
    public void tc_14() {
        assertEquals(1, service.saveStudent("22", reallyLongString, 245));
        studentRepo.delete("22");
    }
}
