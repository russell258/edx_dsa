package OOP;

public class Frog {
    private String name;
    private int age; //number of months
    private double tongueSpeed;
    private boolean isFroglet;
    private static String species = "Rare Pepe";

    public static void setSpecies(String species) {
        Frog.species = species;
    }

    public Frog(String name, int age, double tongueSpeed){
        this.name=name;
        this.age=age;
        this.tongueSpeed=tongueSpeed;
        if (this.age > 1 && this.age<7) this.isFroglet = true;
    }

    public Frog(String name, double ageInYears, double tongueSpeed){
        this(name,(int) ageInYears*12, tongueSpeed);
    }

    public Frog(String name){
        this(name, 5,5.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTongueSpeed() {
        return tongueSpeed;
    }

    public void setTongueSpeed(double tongueSpeed) {
        this.tongueSpeed = tongueSpeed;
    }

    public boolean isFroglet() {
        return isFroglet;
    }

    public void setFroglet(boolean isFroglet) {
        this.isFroglet = isFroglet;
    }

    public static String getSpecies() {
        return species;
    }

    public void grow(int months){
        while (this.getAge()<12 && months>0){
            this.setAge(this.getAge()+1);
            this.setTongueSpeed(this.getTongueSpeed()+1);
            months--;
        }
        
        while (this.age>=30 && months>0 && this.tongueSpeed>5.0){
            this.setAge(this.getAge()+1);
            this.setTongueSpeed(this.getTongueSpeed()-1);
            months--;
        }

        while (months>0){
            if (this.age>=30 && this.tongueSpeed>5.0){
                this.setAge(this.getAge()+1);
                this.setTongueSpeed(this.getTongueSpeed()-1);
                months--;
            }else{
                this.setAge(this.getAge()+1);
                months--;    
            }
        }
        if (this.age > 1 && this.age<7) this.isFroglet = true;
        else this.isFroglet = false;
    }

    public void grow(){
        if (this.getAge()<12){
            this.setAge(this.getAge()+1);
            this.setTongueSpeed(this.getTongueSpeed()+1);
        }
        if (this.getAge()>=30){
            this.setAge(this.getAge()+1);
            if (this.getTongueSpeed()>5.0){
                this.setTongueSpeed(this.getTongueSpeed()-1);
            }
        }
        if (this.age > 1 && this.age<7) this.isFroglet = true;
        else this.isFroglet = false;
    }

    public void eat(Fly fly){
        if (fly.isDead()) return;
        if (this.tongueSpeed>fly.getSpeed()){
            // fly is caught
            if (fly.getMass()*2 >= this.getAge()){
                this.grow();
            }
            fly.setMass(0);
        }else{
            fly.grow(1);
        }
    }

    @Override
    public String toString() {
        if (this.isFroglet) {
            return String.format(
                "My name is %s and I'm a rare froglet! I'm %d months old and my tongue has a speed of %.2f.",
                this.getName(), this.getAge(), this.getTongueSpeed()
            );
        } else {
            return String.format(
                "My name is %s and I'm a rare frog. I'm %d months old and my tongue has a speed of %.2f.",
                this.getName(), this.getAge(), this.getTongueSpeed()
            );
        }
    }

    

}
