import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constant {

  public static final String MENU_MESSAGE =
          "\n" +  // Cyan color
                  "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                  "┃    STAFF MANAGEMENT SYSTEM     ┃\n" +
                  "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                  "┃  1. Insert Employee            ┃\n" +
                  "┃  2. Update Employee            ┃\n" +
                  "┃  3. Display Employee           ┃\n" +
                  "┃  4. Remove Employee            ┃\n" +
                  "┃  5. Exit                       ┃\n" +
                  "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

  public static final String CHOOSE_TYPE =
          "\n" +  // Cyan color
                  "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                  "┃      CHOOSE TYPE EMPLOYEE      ┃\n" +
                  "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                  "┃  1. Volunteer                  ┃\n" +
                  "┃  2. Salaried Employee          ┃\n" +
                  "┃  3. Hourly Employee            ┃\n" +
                  "┃  4. Back                       ┃\n" +
                  "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";


  public static String getUpdateMenu(String employeeType) {
    return switch (employeeType) {
      case "Volunteer" ->
              "\n" +
                      "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                      "┃      UPDATE VOLUNTEER          ┃\n" +
                      "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                      "┃  1. Name                       ┃\n" +
                      "┃  2. Address                    ┃\n" +
                      "┃  3. Salary                     ┃\n" +
                      "┃  4. Back                       ┃\n" +
                      "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

      case "Salaries Employee" ->
              "\n" +
                      "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                      "┃    UPDATE SALARIED EMPLOYEE    ┃\n" +
                      "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                      "┃  1. Name                       ┃\n" +
                      "┃  2. Address                    ┃\n" +
                      "┃  3. Salary                     ┃\n" +
                      "┃  4. Bonus                      ┃\n" +
                      "┃  5. Back                       ┃\n" +
                      "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

      case "Hourly Employee" ->
              "\n" +
                      "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                      "┃   UPDATE HOURLY EMPLOYEE       ┃\n" +
                      "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n" +
                      "┃  1. Name                       ┃\n" +
                      "┃  2. Address                    ┃\n" +
                      "┃  3. Hours Worked               ┃\n" +
                      "┃  4. Hourly Rate                ┃\n" +
                      "┃  5. Back                       ┃\n" +
                      "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

      default -> "Invalid Employee Type!";
    };
  }



  public static List<StaffMember> staffMembers = Stream.of(
          new Volunteer(1, "Tina", "PP", 0.0),
          new SalariedEmployee(2, "Dana", "KPS", 300.0, 10.0),
          new HourlySalaryEmployee(3, "Sokha", "BTB", 60, 10.0),
          new Volunteer(4, "Lee", "SR", 0.0),
          new HourlySalaryEmployee(5, "Ka", "PV", 50, 10.0),
          new SalariedEmployee(6, "Fy", "KT", 300.0, 200.0)
  ).collect(Collectors.toList());

  public static List<StaffMember> staffMemberCollection() {
    return staffMembers;
  }

}
