package controller;

import connection.InputByUserUtil;
import model.Specialty;
import repository.hibernate.SpecialtyRepositoryImp;

import java.util.List;

public class SpecialtyController implements Controller<Specialty, Long> {
    private SpecialtyRepositoryImp specialtyRepositoryImp = new SpecialtyRepositoryImp();

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepositoryImp.save(specialty);
    }

    @Override
    public Specialty read(Long id) {
        return specialtyRepositoryImp.read(id);
    }

    @Override
    public List<Specialty> readAll() {
        return  specialtyRepositoryImp.getAll();
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepositoryImp.update(specialty);
    }
}
