package AmidstUs;

public class Gameplay {
    public static void main(String[] args) {
        // Create BlueAstronauts
        BlueAstronaut bob = new BlueAstronaut("Bob", 20, 6, 30);
        BlueAstronaut heath = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut albert = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut angel = new BlueAstronaut("Angel", 0, 1, 0);

        // Create RedAstronauts
        RedAstronaut liam = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut suspicious = new RedAstronaut("Suspicious Person", 100, "expert");

        // Liam sabotages Bob
        liam.sabotage(bob);
        System.out.println(bob); // Bob: susLevel = 30, frozen = false

        // Liam tries to freeze Suspicious Person (should do nothing)
        liam.freeze(suspicious);

        // Liam freezes Albert
        liam.freeze(albert);
        System.out.println(liam);   // Liam: susLevel = 19
        System.out.println(albert); // Albert: frozen = true

        // Albert calls emergency meeting (should do nothing)
        albert.emergencyMeeting();

        // Suspicious Person calls emergency meeting (tie between Bob and Heath)
        suspicious.emergencyMeeting();

        // Bob calls emergency meeting (Suspicious Person should be frozen)
        bob.emergencyMeeting();
        System.out.println(suspicious); // Suspicious Person: frozen = true

        // Heath completes tasks
        heath.completeTask(); // numTasks = 1
        System.out.println(heath);

        // Heath completes tasks again
        heath.completeTask(); // numTasks = 0, susLevel = 15, prints completion message
        System.out.println(heath);

        // Heath completes tasks again (should do nothing)
        heath.completeTask();

        // Liam tries to freeze Angel (should fail, Liam's susLevel doubles)
        liam.freeze(angel);
        System.out.println(angel); // Angel: frozen = false
        System.out.println(liam);  // Liam: susLevel = 38

        // Liam sabotages Bob twice
        liam.sabotage(bob);
        liam.sabotage(bob);
        System.out.println(bob); // Bob: susLevel = 46

        // Liam freezes Bob
        liam.freeze(bob);
        System.out.println(bob); // Bob: frozen = true

        // Option 1: Angel calls emergency meeting (Liam should be frozen, Crewmates win)
        angel.emergencyMeeting();

        // Option 2: Uncomment below to test impostor win scenario
        /*
        for (int i = 0; i < 5; i++) {
            liam.sabotage(heath);
        }
        System.out.println(heath); // Heath: susLevel = 41
        liam.freeze(heath);
        System.out.println(heath); // Heath: frozen = true
        */
    }
}