import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.*;

public class StaffOperation {

  Scanner sc = new Scanner(System.in);
  List<StaffMember> staff = Constant.staffMemberCollection();
  Validation v = new Validation();

  int rowLimit = 3;
  int currentStartIndex = 0;
  int pageCount = 1;

  public void insertEm(String type) {

    int auto_id = staff.getLast().getId() + 1;

    while (true) {
      try {
        // Insert Volunteer
        if (type.equalsIgnoreCase("Volunteer")) {

          System.out.printf("ID : %d%n", auto_id);
          String name = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Name : ", s -> s);
          String address = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Address : ", s -> s);


          double salary = v.validateInput("^\\d+$", "=> Enter Salary : ", Double::parseDouble);

          StaffMember newVolunteer = new Volunteer(auto_id, name, address, salary);

          staff.add(newVolunteer);

          System.out.printf("* You added %s of type %s successfully! *%n", name, type);
          break;
        }
        // Insert Salaries Employee
        if (type.equalsIgnoreCase("Salaries Employee")) {

          System.out.printf("ID : %d%n", auto_id);
          String name = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Name : ", s -> s);


          String address = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Address : ", s -> s);

          double salary = v.validateInput("^\\d+$", "=> Enter Salary : ", Double::parseDouble);


          double bonus = v.validateInput("^\\d+$", "=> Enter Bonus : ", Double::parseDouble);

          StaffMember newSalariesEmployee = new SalariedEmployee(auto_id, name, address, salary, bonus);

          staff.add(newSalariesEmployee);

          System.out.printf("* You added %s of type %s successfully! *%n", name, type);
          break;
        }
        // Insert Hourly Employee
        if (type.equalsIgnoreCase("Hourly Employee")) {

          System.out.printf("ID : %d%n", auto_id);
          String name = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Name : ", s -> s);

          String address = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Enter Address : ", s -> s);

          int hourWorked = v.validateInput("^\\d+$", "=> Enter Work hours : ", Integer::parseInt);

          double rate = v.validateInput("^\\d+$", "=> Enter Rate : ", Double::parseDouble);

          StaffMember newHourlyEmployee = new HourlySalaryEmployee(auto_id, name, address, hourWorked, rate);

          staff.add(newHourlyEmployee);

          System.out.printf("* You added %s of type %s successfully! *%n", name, type);
          break;
        }
      } catch (Exception e) {
        System.out.println(e);
      }
    }
  }


  public void displayEm() {

    try {
      outer:
      while (true) {

        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        t.setColumnWidth(0, 20, 30); // Type column
        t.setColumnWidth(1, 20, 30); // ID column
        t.setColumnWidth(2, 20, 30); // Name column
        t.setColumnWidth(3, 20, 30); // Address column
        t.setColumnWidth(4, 20, 30); // Salary column
        t.setColumnWidth(5, 20, 30); // Bonus column
        t.setColumnWidth(6, 20, 30); // Hour column
        t.setColumnWidth(7, 20, 30); // Rate column
        t.setColumnWidth(8, 20, 30); // Pay column

        t.addCell("\033[32mTYPE\033[0m", numberStyle);
        t.addCell("\033[32mID\033[0m", numberStyle);
        t.addCell("\033[32mNAME\033[0m", numberStyle);
        t.addCell("\033[32mADDRESS\033[0m", numberStyle);
        t.addCell("\033[32mSALARY\033[0m", numberStyle);
        t.addCell("\033[32mBONUS\033[0m", numberStyle);
        t.addCell("\033[32mHOUR\033[0m", numberStyle);
        t.addCell("\033[32mRATE\033[0m", numberStyle);
        t.addCell("\033[32mPAY\033[0m", numberStyle);


        staff.sort(Comparator.comparing(StaffMember::getId));

        staff.stream().skip(currentStartIndex).limit(rowLimit).forEach(staffMember -> {
          String[] rowData = staffMember.getTableRow();
          t.addCell(rowData[0], numberStyle);
          t.addCell(rowData[1], numberStyle);
          t.addCell(rowData[2], numberStyle);
          t.addCell(rowData[3], numberStyle);
          t.addCell(rowData[4], numberStyle);
          t.addCell(rowData[5], numberStyle);
          t.addCell(rowData[6], numberStyle);
          t.addCell(rowData[7], numberStyle);
          t.addCell(rowData[8], numberStyle);
        });

        System.out.println(t.render());

        int totalPages = (staff.size() + rowLimit - 1) / rowLimit;


          // Pagination
          System.out.printf("\nPage : %d/%d\n".formatted(pageCount, totalPages));
          System.out.print("\n1. First Page\t\t");
          System.out.print("2. Next Page\t\t");
          System.out.print("3. Previous Page\t\t");
          System.out.print("4. Last Page\t\t");
          System.out.println("5. Exit");


          int choose = v.validateInput("^[1-5]$", "\n=> Choose : ", Integer::parseInt);

          switch (choose) {
            // TODO: First Page
            case 1:
              currentStartIndex = 0;
              pageCount = 1;
              break;
            // TODO: Next Page
            case 2:

              int lastIndex = -1;
              for (int i = staff.size() - 1; i >= 0; i++) {
                if (staff.get(staff.size() - 1) != null) {
                  lastIndex = i;
                  break;
                }
              }
              if (lastIndex == -1) {
                System.out.println("No data available!");
                continue;
              }
              int maxStartIndex = Math.max(0, lastIndex - (rowLimit - 1));

              if (currentStartIndex >= maxStartIndex) {

                System.out.println("You already on the last page.");
                continue;
              }

              if (currentStartIndex + rowLimit <= lastIndex) {
                currentStartIndex += rowLimit;
                pageCount++;
              } else {
                currentStartIndex = maxStartIndex;
              }
              continue;

              // TODO: Previous Page
            case 3:
              if (currentStartIndex - rowLimit >= 0) {
                currentStartIndex -= rowLimit;
                pageCount = pageCount - 1;
              }
              continue;
              // TODO: Last Page
            case 4:
              int lastIndexLastPage = -1;
              for (int i = staff.size() - 1; i >= 0; i++) {
                if (staff.get(staff.size() - 1) != null) {
                  lastIndexLastPage = i;
                  break;
                }
              }

              if (lastIndexLastPage != -1) {
                currentStartIndex = lastIndexLastPage - (lastIndexLastPage % rowLimit);
                pageCount = totalPages;
              } else {
                currentStartIndex = 0;
              }
              continue;
              // Exit
            case 5:
              break outer;
          }
        }

      }catch(Exception e){
        System.out.println(e);
      }
    }


    public void updateEm () {
      System.out.print("=> Enter or search ID to update: ");
      int updateId = sc.nextInt();
      sc.nextLine();

      Optional<StaffMember> optionalStaffMember = staff.stream()
              .filter(staffMember -> staffMember.getId() == updateId)
              .findFirst();

      if (optionalStaffMember.isPresent()) {
        StaffMember staffMember = optionalStaffMember.get();
        String[] rowData = staffMember.getTableRow();

        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table t = new Table(9, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        t.setColumnWidth(0, 20, 30);
        t.setColumnWidth(1, 20, 30);
        t.setColumnWidth(2, 20, 30);
        t.setColumnWidth(3, 20, 30);
        t.setColumnWidth(4, 20, 30);
        t.setColumnWidth(5, 20, 30);
        t.setColumnWidth(6, 20, 30);
        t.setColumnWidth(7, 20, 30);
        t.setColumnWidth(8, 20, 30);

        t.addCell("\033[32mTYPE\033[0m", numberStyle);
        t.addCell("\033[32mID\033[0m", numberStyle);
        t.addCell("\033[32mNAME\033[0m", numberStyle);
        t.addCell("\033[32mADDRESS\033[0m", numberStyle);
        t.addCell("\033[32mSALARY\033[0m", numberStyle);
        t.addCell("\033[32mBONUS\033[0m", numberStyle);
        t.addCell("\033[32mHOUR\033[0m", numberStyle);
        t.addCell("\033[32mRATE\033[0m", numberStyle);
        t.addCell("\033[32mPAY\033[0m", numberStyle);

        t.addCell(rowData[0], numberStyle);
        t.addCell(rowData[1], numberStyle);
        t.addCell(rowData[2], numberStyle);
        t.addCell(rowData[3], numberStyle);
        t.addCell(rowData[4], numberStyle);
        t.addCell(rowData[5], numberStyle);
        t.addCell(rowData[6], numberStyle);
        t.addCell(rowData[7], numberStyle);
        t.addCell(rowData[8], numberStyle);

        System.out.println(t.render());

        System.out.print("\nChoose one column to update : ");
        System.out.println(Constant.getUpdateMenu(rowData[0]));

        System.out.print("=> Select column number : ");
        int columnUpdate = sc.nextInt();
        sc.nextLine();

        outer:
        while (true) {
          if (rowData[0].equalsIgnoreCase("Volunteer")) {
            switch (columnUpdate) {
              case 1:
                String newName = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change name to : ", s -> s);
                staffMember.setName(newName);

                System.out.println("\nName has been updated!!");
                break outer;
              case 2:
                String newAddress = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change address to : ", s -> s);
                staffMember.setAddress(newAddress);

                System.out.println("\nAddress has been updated!!");
                break outer;
              case 3:

                double newSalary = v.validateInput("^\\d+$", "=> Change salary to : ", Double::parseDouble);

                optionalStaffMember.filter(staff -> staff instanceof Volunteer).map(staff -> (Volunteer) staff).ifPresent(volunteerStaff -> {
                  volunteerStaff.setSalary(newSalary);
                });

                System.out.println("\nSalary has been updated!!");
                break outer;
              case 4:
                break outer;
              default:
                System.out.println("\nInvalid input!!");
                break outer;
            }
          } else if (rowData[0].equalsIgnoreCase("Salaries Employee")) {
            switch (columnUpdate) {
              case 1:
                String newName = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change name to : ", s -> s);
                staffMember.setName(newName);

                System.out.println("\nName has been updated!!");
                break outer;
              case 2:

                String newAddress = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change address to : ", s -> s);
                staffMember.setAddress(newAddress);

                System.out.println("\nAddress has been updated!!");
                break outer;
              case 3:
                double newSalary = v.validateInput("^\\d+$", "=> Change address to : ", Double::parseDouble);

                optionalStaffMember.filter(staff -> staff instanceof SalariedEmployee).map(staff -> (SalariedEmployee) staff).ifPresent(salariedEmployee -> {
                  salariedEmployee.setSalary(newSalary);
                });
                System.out.println("\nSalary has been updated!!");

                break outer;
              case 4:

                double newBonus = v.validateInput("^\\d+$", "=> Change bonus to : ", Double::parseDouble);

                optionalStaffMember.filter(staff -> staff instanceof SalariedEmployee).map(staff -> (SalariedEmployee) staff).ifPresent(salariedEmployee -> {
                  salariedEmployee.setBonus(newBonus);
                });

                System.out.println("\nBonus has been updated!!");
                break outer;
              case 5:
                break outer;
              default:
                System.out.println("\nInvalid input!!");
                break outer;
            }

          } else if (rowData[0].equalsIgnoreCase("Hourly Employee")) {
            switch (columnUpdate) {
              case 1:
                String newName = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change name to : ", s -> s);

                staffMember.setName(newName);
                System.out.println("\nName has been updated!!");
                break outer;
              case 2:
                String newAddress = v.validateInput("([a-zA-Z0-9_\\s]+)", "=> Change address to : ", s -> s);

                staffMember.setAddress(newAddress);
                System.out.println("\nAddress has been updated!!");
                break outer;
              case 3:

                int newWorkedHour = v.validateInput("^\\d+$", "=> Change work hour to : ", Integer::parseInt);


                optionalStaffMember.filter(staff -> staff instanceof HourlySalaryEmployee).map(staff -> (HourlySalaryEmployee) staff).ifPresent(hourlySalaryEmployee -> {
                  hourlySalaryEmployee.setHourWorked(newWorkedHour);
                });

                System.out.println("\nWorkedHour has been updated!!");
                break outer;
              case 4:

                double newRate = v.validateInput("^\\d+$", "=> Change rate to : ", Double::parseDouble);

                optionalStaffMember.filter(staff -> staff instanceof HourlySalaryEmployee).map(staff -> (HourlySalaryEmployee) staff).ifPresent(hourlySalaryEmployee -> {
                  hourlySalaryEmployee.setRate(newRate);
                });

                System.out.println("\nRate has been updated!!");
                break outer;
              case 5:
                break outer;
              default:
                System.out.println("\nInvalid input!!");
                break outer;
            }
          }
        }


      } else {
        System.out.printf("\nEmployee with ID %d not found.%n", updateId);
      }
    }


    public void removeEm () {

      System.out.print("=> Enter id to remove : ");


      int deleteId = sc.nextInt();
      sc.nextLine();

      if (staff.stream().anyMatch(s -> s.getId() == deleteId)) {
        staff.removeIf(item -> item.getId() == deleteId);
      } else {
        System.out.println("\nInvalid id to remove!");
        return;
      }


      System.out.println("* Removing user successfully!!");
    }


  }
