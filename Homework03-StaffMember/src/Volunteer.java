public class Volunteer extends StaffMember {

  private double salary;

  public Volunteer(){} //IDK if there somewhere using no-arg constructor, but I leave it have for future use

  public Volunteer(int id, String name, String address, double salary) {
    super(id, name, address);
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "Volunteer{salary=%s}".formatted(salary);
  }

  @Override
  public double pay() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  @Override
  public String[] getTableRow() {
    return new String[]{
            "Volunteer",
            String.valueOf(getId()),
            getName(),
            getAddress(),
            String.valueOf(salary),
            "---", //Bonus
            "---", //Hour
            "---", //Rate
            String.valueOf(pay())
    };
  }
}
