package ubb.CCA;

import static org.junit.Assert.assertEquals;

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

import java.util.Arrays;

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
    public void all_tests()
    {
        tc_01();
        tc_02();
        tc_03();
        tc_05();
        tc_07();
        tc_08();
        tc_09();
        tc_10();
        tc_11();
        tc_12();
        tc_13();
        tc_14();
        tc_15();
        tc_16();
        tc_17();
        tc_18();
        tc_19();
        tc_20();
    }

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
    }

    @Test
    public void tc_07() {

        assertEquals(0, service.saveStudent("15", "BVA5", 99));
    }

    @Test
    public void tc_08()
    {
        assertEquals(0, service.saveStudent("36", "BVA8", 1000));
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

    @Test
    public void tc_15(){
        assertEquals(1, service.saveStudent("24", "Jo", 245));
        studentRepo.delete("24");
    }

    @Test
    public void tc_16(){
        assertEquals(1, service.saveStudent("25", reallyLongString, 245));
        studentRepo.delete("25");
    }

    @Test
    public void tc_17(){
        assertEquals(1, service.saveStudent("31", "BVA3", 100));
        studentRepo.delete("31");
    }

    @Test
    public void tc_18(){
        assertEquals(1, service.saveStudent("32", "BVA4", 999));
        studentRepo.delete("32");
    }

    @Test
    public void tc_19(){
        assertEquals(1, service.saveStudent("34", "BVA6", 101));
        studentRepo.delete("34");
    }

    @Test
    public void tc_20(){
        assertEquals(1, service.saveStudent("35", "BVA7", 998));
        studentRepo.delete("35");
    }
}
