package ubb.CCA;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ubb.CCA.domain.Nota;
import ubb.CCA.domain.Pair;
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
import java.util.Arrays;

public class AppTest 
{
    final Validator<Student> studentValidator = new StudentValidator();
    final Validator<Tema> temaValidator = new TemaValidator();
    final Validator<Nota> notaValidator = new NotaValidator();
    final StudentXMLRepository studentRepo = new StudentXMLRepository(studentValidator, "test-studenti.xml");
    final TemaXMLRepository temaRepo = new TemaXMLRepository(temaValidator, "test-teme.xml");
    final NotaXMLRepository notaRepo = new NotaXMLRepository(notaValidator, "note.xml");
    final Service service = new Service(studentRepo, temaRepo, notaRepo);
    String reallyLongString;

    public AppTest() {
        char[] fill = new char [Integer.MAX_VALUE / 1000];
        Arrays.fill(fill, 'a');
        reallyLongString = new String(fill);
    }

    @Test
    public void test_saveStudent01()
    {
        assertEquals(1, service.saveStudent("extra_test", "Eu Eulescu", 234));
        studentRepo.delete("extra_test");
    }

    @Test
    public void test_saveTema01()
    {
        assertEquals(1, service.saveTema("extra_test", "the easiest assignment", 4, 2));
        temaRepo.delete("extra_test");

    }

    @Before
    public void init_test()
    {
        service.saveStudent("student_nota", "Eu Eulescu", 932);
        service.saveTema("tema_nota", "the easiest assignment", 8, 2);
    }

    @After
    public void exit_test()
    {
        studentRepo.delete("student_nota");
        temaRepo.delete("tema_nota");

    }

    @Test
    public void test_saveNota01()
    {
        assertEquals(1, service.saveNota("student_nota", "tema_nota", 9, 7, "good tests"));
        notaRepo.delete(new Pair("student_nota", "tema_nota"));
    }


    @Test
    public void test_integrationBigBang01()
    {
        service.saveStudent("grade_test_student", "Eu Eulescu", 234);
        service.saveTema("grade_test_tema", "test assigment", 10, 5);
        assertEquals(1, service.saveNota("grade_test_student", "grade_test_tema", 9, 10, "not perfect, but ok."));
        studentRepo.delete("grade_test_student");
        temaRepo.delete("grade_test_tema");
        notaRepo.delete(new Pair("grade_test_student", "grade_test_tema"));
    }

    @Test
    public void all_intergrationTest_inClass()
    {
        test_saveStudent01();
        test_saveTema01();
        test_saveNota01();
        test_integrationBigBang01();
    }


    @Test
    public void test_all_wbt()
    {
        tc_01_wbt();
        tc_02_wbt();
        tc_03_wbt();
        tc_04_wbt();
        tc_05_wbt();
        tc_06_wbt();
    }

    @Test
    public void tc_01_wbt()
    {
        assertEquals(1, service.saveTema("102", "the easiest assignment", 4, 2));
        temaRepo.delete("102");
    }

    @Test
    public void tc_02_wbt()
    {
        assertEquals(0, service.saveTema("4", "hardest",3 , 5));
    }

    @Test
    public void tc_03_wbt()
    {
        assertEquals(0, service.saveTema("5", "assign1",12 , -1));
    }

    @Test
    public void tc_04_wbt()
    {
        assertEquals(0, service.saveTema("6", "assign2",20 , 4));
    }

    @Test
    public void tc_05_wbt()
    {
        assertEquals(0, service.saveTema("", "assign3",6 , 5));
    }

    @Test
    public void tc_06_wbt()
    {
        assertEquals(0, service.saveTema("7", "",6 , 5));
    }

    @Test
    public void tc_23_wbt()
    {
        /*int size1 = temaRepo.findAll().
        service.saveTema("3", "the easiest assignment", 4, 2);
        File file2 = new File("teme.xml");
        long size2 = file2.length();
        assertTrue(size1<size2);
        service.deleteTema("3");*/
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
