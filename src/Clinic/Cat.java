package Clinic;

public class Cat extends Pet{
    private int miceCaught;
    public Cat (String name, double health, int painLevel, int miceCaught) {
        super(name, health,painLevel);
        if (miceCaught<0) {
            this.miceCaught=0;
        }else{
            this.miceCaught = miceCaught;
        }
    }
    
    public Cat (String name, double health, int painLevel) {
        this(name, health, painLevel, 0);
    }

    public int getMiceCaught() {
        return miceCaught;
    }

    public void setMiceCaught(int miceCaught) {
        this.miceCaught = miceCaught;
    }

    @Override
    public int treat() {
        // compute using current values, then apply heal()
        double healthBefore = getHealth();
        int painBefore = getPainLevel();
        int minutes;
        if (miceCaught < 4) {
            minutes = (int) Math.ceil((painBefore * 2.0) / healthBefore);
        } else if (miceCaught <= 7) {
            minutes = (int) Math.ceil(painBefore / healthBefore);
        } else {
            minutes = (int) Math.ceil(painBefore / (healthBefore * 2.0));
        }
        heal();
        return minutes;
    }

    public void speak() {
        super.speak();
        String sound = "meow";
        if (getPainLevel() > 5) {
            sound = sound.toUpperCase();
        }
        StringBuilder meowSound = new StringBuilder();
        for (int i = 0; i < getPainLevel(); i++) {
            meowSound.append(sound).append(" ");
        }
        System.out.println(meowSound.toString().trim());
    }

    public boolean equals(Object o){
        if (super.equals(o)){
            if (o instanceof Cat){
                Cat other = (Cat) o;
                return this.miceCaught == other.miceCaught;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
