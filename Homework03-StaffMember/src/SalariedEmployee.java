public class SalariedEmployee extends StaffMember {

  private double salary;
  private double bonus;

  public SalariedEmployee(int id, String name, String address, double salary, double bonus) {
    super(id, name, address);
    this.salary = salary;
    this.bonus = bonus;
  }

  @Override
  public String toString() {
    return "SalariedEmployee{salary=%s, bonus=%s}".formatted(salary, bonus);
  }

  @Override
  public double pay() {
    return salary + bonus;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public void setBonus(double bonus) {
    this.bonus = bonus;
  }

  @Override
  public String[] getTableRow() {
    return new String[]{
            "Salaries Employee",
            String.valueOf(getId()),
            getName(),
            getAddress(),
            String.valueOf(salary),
            String.valueOf(bonus),
            "---",                 // Hour
            "---",                 // Rate
            String.valueOf(pay())
    };
  }

}
