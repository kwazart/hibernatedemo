package controller;

import connection.InputByUserUtil;
import model.Developer;
import repository.hibernate.DeveloperRepositoryImp;

import java.util.List;

public class DeveloperController implements Controller<Developer, Long> {
    private DeveloperRepositoryImp developerRepositoryImp = new DeveloperRepositoryImp();

    @Override
    public Developer create(Developer developer)  {
        return developerRepositoryImp.save(developer);
    }

    @Override
    public Developer read(Long id) {
        return developerRepositoryImp.read(id);
    }

    @Override
    public List<Developer> readAll() {
        return developerRepositoryImp.getAll();
    }

    @Override
    public Developer update(Developer developer) {
        return developerRepositoryImp.update(developer);
    }
}
