package ubb.CCA;

import static org.junit.Assert.assertTrue;

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

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void tc_SuccessfulUserAdd()
    {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(studentRepo, temaRepo, notaRepo);

        assertTrue( service.saveStudent("10", "TestStudent", 932) == 1 );
        //assertTrue( true );
    }
    @Test
    public void test2()
    {

        assertTrue( true );
    }
}
