package controller;

import connection.InputByUserUtil;
import model.Skill;
import repository.hibernate.SkillRepositoryImp;

import java.util.List;

public class SkillController implements Controller<Skill, Long> {
    private SkillRepositoryImp skillRepositoryImp = new SkillRepositoryImp();

    @Override
    public Skill create(Skill skill) {
        return skillRepositoryImp.save(skill);
    }

    @Override
    public Skill read(Long id) {
        return skillRepositoryImp.read(id);

    }

    @Override
    public List<Skill> readAll() {
        return skillRepositoryImp.getAll();
    }

    @Override
    public Skill update(Skill skill) {
        return skillRepositoryImp.update(skill);
    }
}
