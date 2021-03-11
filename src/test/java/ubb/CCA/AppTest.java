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
    @After
    public void cleanUp() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
        studentRepo.delete("10");
    }

    @Test
    public void tc_SuccessfulUserAdd()
    {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        assertEquals(1, service.saveStudent("10", "TestStudent", 932));
    }

    @Test
    public void tc_UnsuccesfulAddBadGroup() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        assertEquals(0, service.saveStudent("10", "TestStudent", 4));
    }
}
