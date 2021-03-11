package ubb.CCA.repository;

import ubb.CCA.domain.Tema;
import ubb.CCA.validation.*;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

