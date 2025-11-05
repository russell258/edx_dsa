public class AnotherQuestion {
   public static void foo(int x) throws MyException, IllegalArgumentException {
       if (x < 0) {
           System.out.println("low");
           throw(new MyException(x));
       }
       if (x > 100) {
           System.out.println("high");
           throw(new IllegalArgumentException("YellowJacket"));
       }
       System.out.println("Bunny");
   }
}