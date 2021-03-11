package ubb.CCA.repository;

import ubb.CCA.domain.Student;
import ubb.CCA.validation.*;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

