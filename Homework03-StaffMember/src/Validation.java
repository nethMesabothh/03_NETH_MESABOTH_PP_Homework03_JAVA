import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Validation {
  Scanner sc = new Scanner(System.in);

  public <T> T validateInput(String regex, String prompt, Function<String, T> converter) {
    while (true) {
      System.out.print(prompt);
      String input = sc.nextLine().trim();

      if(Pattern.matches(regex, input)){
        try{
          return converter.apply(input);
        }catch (NumberFormatException nfe){
          System.out.println("Invalid input.");
        }
      }else{
        System.out.println("Invalid input. Please try again.");
      }
    }
  }
}
