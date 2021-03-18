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
    final String reallyLongString = new String(new char[Integer.MAX_VALUE / 10]);

//    @Test
//    public void all_tests()
//    {
//        tc_01();
//        tc_02();
//        tc_03();
//        //tc_04();
//        tc_05();
//        //tc_06();
//        tc_07();
//        tc_08();
//        tc_09();
//        tc_10();
//        tc_11();
//        tc_12();
//        tc_13();
//        tc_14();
//        tc_15();
//        tc_16();
//        tc_17();
//        tc_18();
//        tc_19();
//        tc_20();
//
//    }

    @Test
    public void tc_01()
    {
        assertEquals(1, service.saveStudent("10", "Eu Eulescu", 234));
        studentRepo.delete("10");
    }

    @Test
    public void tc_02()
    {
        assertEquals(0, service.saveStudent("", "Eu Eulescu", 234));
        //studentRepo.delete("");
    }

    @Test
    public void tc_03()
    {
        assertEquals(1, service.saveStudent(reallyLongString, "BVA1", 765));
        studentRepo.delete(reallyLongString);
    }


    @Test
    public void tc_05()
    {
        assertEquals(0, service.saveStudent("6", "", 234));
        //studentRepo.delete("6");
    }



    @Test
    public void tc_07() {

        assertEquals(0, service.saveStudent("15", "BVA5", 99));
        //studentRepo.delete("15");
    }

    @Test
    public void tc_08()
    {
        assertEquals(0, service.saveStudent("36", "BVA8", 1000));
        //studentRepo.delete("36");
    }
}
