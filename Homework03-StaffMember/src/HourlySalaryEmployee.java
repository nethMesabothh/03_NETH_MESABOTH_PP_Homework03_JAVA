public class HourlySalaryEmployee extends StaffMember {
  private int hourWorked;
  private double rate;


  public HourlySalaryEmployee(int id, String name, String address,int hourWorked, double rate){
    super(id,name,address);
    this.hourWorked = hourWorked;
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "HourlySalaryEmployee{hourWorked=%d, rate=%s}".formatted(hourWorked, rate);
  }


  @Override
  public double pay(){
    return hourWorked * rate;
  }

  public void setHourWorked(int hourWorked) {
    this.hourWorked = hourWorked;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  @Override
  public String[] getTableRow() {
    return new String[]{
            "Hourly Employee",
            String.valueOf(getId()),
            getName(),
            getAddress(),
            "---",                 // Salary
            "---",                 // Bonus
            String.valueOf(hourWorked),
            String.valueOf(rate),
            String.valueOf(pay())  // Total Pay
    };
  }
}
