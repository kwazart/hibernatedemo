package viewer;

import connection.InputByUserUtil;
import controller.SkillController;
import model.Skill;
import model.Status;

import java.util.List;
import java.util.Scanner;

public class SkillViewer implements Viewer<Skill> {
    private SkillController skillController = new SkillController();

    public SkillController getSkillController() {
        return skillController;
    }
    @Override
    public void veiwAdd() {
        Skill skill = new Skill();
        skill.setId(0);
        skill.setStatus(Status.ACTIVE);
        System.out.print("Enter skill name: ");
        skill.setName(InputByUserUtil.inputData());
        skillController.create(skill);
    }

    @Override
    public void viewGet() {
        long id = InputByUserUtil.inputLong();
        Skill skill = skillController.read(id);
        if (!InputByUserUtil.isNotFound(skill)) return;
        System.out.println(skillController.read(id));
    }

    @Override
    public void viewGetAll() {
        printAll(skillController.readAll());
    }

    @Override
    public void viewDelete() {
        long id = InputByUserUtil.inputLong();
        Skill skill = skillController.read(id);
        if (!InputByUserUtil.isNotFound(skill)) return;
        if (skill.getStatus().equals(Status.DELETED)) {
            System.out.println("Skill already deleted.");
            return;
        }
        skill.setStatus(Status.DELETED);
        skillController.update(skill);
        System.out.println("Complete.");
    }

    @Override
    public void viewUpdate() {
        long id = InputByUserUtil.inputLong();
        Skill skill = skillController.read(id);
        if (!InputByUserUtil.isNotFound(skill)) return;
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - skill name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) { return; }
            if (variant.equals("1")) {
                System.out.print("Enter new skill name: ");
                skill.setName(InputByUserUtil.inputData());
                break;
            }
            else if (variant.equals("2")) {
                if (skill.getStatus().equals(Status.ACTIVE)) {
                    skill.setStatus(Status.DELETED);
                } else  {
                    skill.setStatus(Status.ACTIVE);
                }
                break;
            }
            else { System.out.println("\nWrong choice. Try again.\n"); }
        }
        skillController.update(skill);
    }


    @Override
    public void printAll(List<Skill> list) {
        for (Skill skill : list) {
            printByIndex(skill);
        }
    }

    public List<Skill> printCurrentListSkills() {
        List<Skill> list = skillController.readAll();
        for (Skill skill : list) {
            printByIndex(skill);
        }
        return list;
    }
}
