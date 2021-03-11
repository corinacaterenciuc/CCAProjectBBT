package ubb.CCA.repository;

import ubb.CCA.domain.*;
import ubb.CCA.validation.*;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
