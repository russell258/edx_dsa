package Clinic;

public class Dog extends Pet{
    private double droolRate;
    public Dog (String name, double health, int painLevel, double droolRate) {
        super(name, health,painLevel);
        if (droolRate<=0.0) {
            this.droolRate=0.5;
        }else{
            this.droolRate = droolRate;
        }
    }

    public Dog (String name, double health, int painLevel) {
        this(name, health, painLevel, 5.0);
    }

    public double getDroolRate() {
        return droolRate;
    }

    public void setDroolRate(double droolRate) {
        this.droolRate = droolRate;
    }

    @Override
    public int treat() {
        // compute using current values, then apply heal()
        double healthBefore = getHealth();
        int painBefore = getPainLevel();
        int minutes;
        if (droolRate < 3.5) {
            minutes = (int) Math.ceil((painBefore * 2.0) / healthBefore);
        } else if (droolRate <= 7.5) {
            minutes = (int) Math.ceil(painBefore / healthBefore);
        } else {
            minutes = (int) Math.ceil(painBefore / (healthBefore * 2.0));
        }
        heal();
        return minutes;
    }

    public void speak() {
        super.speak(); // call parent method
        String sound = "bark";
        if (getPainLevel() > 5){
            sound = sound.toUpperCase();
        }
        StringBuilder barkSound = new StringBuilder();
        for (int i = 0; i < getPainLevel(); i++) {
            barkSound.append(sound).append(" ");
        }
        System.out.println(barkSound.toString().trim());
    }

    public boolean equals(Object o){
        if (super.equals(o)){
            if (o instanceof Dog){
                Dog other = (Dog) o;
                return this.droolRate == other.droolRate;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
