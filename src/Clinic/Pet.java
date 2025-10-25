package Clinic;

public abstract class Pet {
    private String name;
    private double health;
    private int painLevel;

    public Pet(String name, double health, int painLevel) {
        this.name = name;
        if (health < 0.0){
            this.health = 0.0;
        }else  if (health > 1.0) {
            this.health = 1.0;
        }else{
            this.health = health;
        }

        if (painLevel >10){
            this.painLevel = 10;
        }else if (painLevel <1){
            this.painLevel = 1;
        }else{
        this.painLevel = painLevel;
        }
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }
    public int getPainLevel() {
        return painLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setPainLevel(int painLevel) {
        this.painLevel = painLevel;
    }

    public abstract int treat();
    
    public void speak(){
        if (painLevel > 5){
            System.out.println("HELLO! MY NAME IS " + name.toUpperCase());
        }else{
        System.out.println("Hello! My name is " + name);
        }
    }

    public boolean equals(Object o){
        // check if is pet
        if (o instanceof Pet){
            Pet other = (Pet) o;
            //check if name same
            return this.name.equals(other.getName());
        }else{
            return false;
        }
    }

    protected void heal(){
        setHealth(1.0);
        setPainLevel(1);
    }

}
