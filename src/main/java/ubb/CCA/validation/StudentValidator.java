package ubb.CCA.validation;
import ubb.CCA.domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().equals("")) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() == null || student.getGrupa() <= 99 || student.getGrupa() >= 1000) {
            throw new ValidationException("Grupa invalida! \n");
        }
    }
}

