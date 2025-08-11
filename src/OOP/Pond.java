package OOP;

public class Pond {
    public static void main(String[] args){
        // 4 frog objects
        Frog frog1 = new Frog("Peepo");
        Frog frog2 = new Frog("Peepaw", 4.6, 5);
        Frog frog3 = new Frog("Pepe", 10, 15);
        Frog myFrog = new Frog("Russell");

        // 3 fly objects
        Fly fly1 = new Fly(1.0,3);
        Fly fly2 = new Fly(6);
        Fly fly3 = new Fly(15, 4);

        // operations
        frog1.setSpecies("1331 Frogs");
        System.out.println(frog1.toString());
        frog1.eat(fly2);
        System.out.println(fly2.toString());
        frog1.grow(8);
        frog1.eat(fly2);
        System.out.println(fly2.toString());
        System.out.println(frog1.toString());
        System.out.println(myFrog.toString());
        frog2.grow(4);
        System.out.println(frog2.toString());
        System.out.println(frog3.toString());

    }
}
