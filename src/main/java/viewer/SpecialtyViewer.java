package viewer;

import connection.InputByUserUtil;
import controller.SpecialtyController;
import model.Specialty;
import model.Status;

import java.util.List;
import java.util.Scanner;

public class SpecialtyViewer implements Viewer<Specialty> {
    private SpecialtyController specialtyController = new SpecialtyController();

    public SpecialtyController getSpecialtyController() {
        return specialtyController;
    }

    @Override
    public void veiwAdd() {
        Specialty specialty = new Specialty();
        specialty.setId(0);
        specialty.setStatus(Status.ACTIVE);
        System.out.print("Enter specialty name: ");
        specialty.setName(InputByUserUtil.inputData());
        specialtyController.create(specialty);
    }

    @Override
    public void viewGet() {
        long id = InputByUserUtil.inputLong();
        System.out.println(specialtyController.read(id));
    }

    @Override
    public void viewGetAll() {
        printAll(specialtyController.readAll());
    }

    @Override
    public void viewDelete() {
        long id = InputByUserUtil.inputLong();
        Specialty specialty = specialtyController.read(id);
        if (!InputByUserUtil.isNotFound(specialty)) return;
        if (specialty.getStatus().equals(Status.DELETED)) {
            System.out.println("Specialty already deleted.");
            return;
        }
        specialty.setStatus(Status.DELETED);
        specialtyController.update(specialty);
        System.out.println("Complete.");
    }

    @Override
    public void viewUpdate() {
        long id = InputByUserUtil.inputLong();
        Specialty specialty = specialtyController.read(id);
        if (!InputByUserUtil.isNotFound(specialty)) return;
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - specialty name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) { return; }
            if (variant.equals("1")) {
                System.out.print("Enter new specialty name: ");
                specialty.setName(InputByUserUtil.inputData());
                break;
            }
            else if (variant.equals("2")) {
                if (specialty.getStatus().equals(Status.ACTIVE)) {
                    specialty.setStatus(Status.DELETED);
                } else {
                    specialty.setStatus(Status.ACTIVE);
                }
                break;
            }
            else { System.out.println("\nWrong choice. Try again.\n"); }
        }
        specialtyController.update(specialty);
    }

    @Override
    public void printAll(List<Specialty> list) {
        for (Specialty specialty : list) {
            System.out.println(specialty);
        }
    }

    public List<Specialty> printCurrentListSpecialties() {
        List<Specialty> list = specialtyController.readAll();
        for (Specialty specialty : list) {
            System.out.println(specialty);
        }
        return list;
    }
}
