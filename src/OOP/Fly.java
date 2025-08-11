package OOP;

public class Fly {
    private double mass;
    private double speed;

    public Fly(double mass, double speed){
        this.mass = mass;
        this.speed = speed;
    }

    public Fly(){
        this(5,10);
    }

    public Fly(double mass){
        this(mass,10);
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        if (this.isDead()) {
            return String.format(
                "I'm dead, but I used to be a fly with a speed of %.2f.",
                this.speed
            );
        } else {
            return String.format(
                "I'm a speedy fly with %.2f speed and %.2f mass.",
                this.speed, this.mass
            );
        }
    }

    public void grow(int addedMass){
        double massToAdd = (double) addedMass;
        while (this.getMass()<20.0 && massToAdd>0.0){
            this.setSpeed(this.getSpeed()+1);
            this.setMass(this.getMass()+1);
            massToAdd--;
        }
        while (this.getMass()>=20.0 && massToAdd>0.0){
            this.setSpeed(this.getSpeed()-0.5);
            this.setMass(this.getMass()+1);
            massToAdd--;
        }
    }

    public boolean isDead(){
        return this.getMass()==0 ? true : false;
    }

}
