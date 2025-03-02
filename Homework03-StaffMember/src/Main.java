import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    StaffOperation staffOperation = new StaffOperation();


    Scanner sc = new Scanner(System.in);
    int choice;

    outer:
    while (true) {

      System.out.println(Constant.MENU_MESSAGE);
      System.out.println("------------------------------------\n");
      System.out.print("-> Choose an Option() : ");
      choice = sc.nextInt();
      sc.nextLine();

    inner:
      while (true) {
        switch (choice) {
          case 1:
            System.out.println("\n==============* Insert Employee *==============\n");
            // Insert employee logic
            System.out.println("Choose Type:");

            System.out.println(Constant.CHOOSE_TYPE);
            System.out.print("=> Enter Type Number : ");
            int type = sc.nextInt();

            switch (type) {
              case 1:
                staffOperation.insertEm("Volunteer");
                break;
              case 2:
                staffOperation.insertEm("Salaries Employee");
                break;
              case 3:
                staffOperation.insertEm("Hourly Employee");
                break;
              case 4:
                break inner;
              default:
                System.out.println("\nInvalid input!");
                continue;
            }

            break;
          case 2:
            System.out.println("\n==============* Update Employee *==============\n");
            // Update employee logic
            staffOperation.updateEm();
            break inner;
          case 3:
            System.out.println("\n==============* Display Employee *==============\n");
            // Display employee logic
            staffOperation.displayEm();
            break inner;
          case 4:
            System.out.println("\n==============* Remove Employee *==============\n");
            // Remove employee logic
            staffOperation.removeEm();
            break inner;
          case 5:
            System.out.println("Exiting the system...");
            sc.close();
            System.exit(0);
            break outer;
          default:
            System.out.println("\nInvalid choice. Please try again.");
        }
      }
    }
  }
}
