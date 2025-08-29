package AmidstUs;

import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    
    private String skill;

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name){
        super(name, 15); //susLevel default to 15
        this.skill = "experienced";
    }

    @Override
    public void sabotage(Player p) {
        // TODO Auto-generated method stub
        if (p instanceof Impostor || this.isFrozen() || p.isFrozen()){
            return;
        } else{
            if (this.getSusLevel() < 20) {
                p.setSusLevel(p.getSusLevel() * 3/2);
            }else{
                p.setSusLevel(p.getSusLevel() * 5/4);
            }
        }
    }

    @Override
    public void freeze(Player p) {
        // TODO Auto-generated method stub
        // Check if player is imposter. if imposter cannot freeze.
        // If you yourself frozen, cannot freeze
        // if player already frozen, cannot freeze
        if (p instanceof Impostor || p.isFrozen() || this.isFrozen()){
            return;
        }else{
            if (this.getSusLevel() < p.getSusLevel()) {
                p.setFrozen(true);
            }else{
                // unsucessful freeze
                this.setSusLevel(this.getSusLevel()*2);
            }
        }
        gameOver();
    }

    @Override
    public void emergencyMeeting() {
        // TODO Auto-generated method stub
        if (this.isFrozen()){
            return;
        }
        Player[] referenceArr = getPlayers();
        // Filter out frozen players
        int activeCount = 0;
        for (Player p : referenceArr) {
            if (!p.isFrozen() && !p.equals(this)) activeCount++;
        }
        Player[] activePlayers = new Player[activeCount];
        int idx = 0;
        for (Player p : referenceArr) {
            if (!p.isFrozen() && !p.equals(this)) activePlayers[idx++] = p;
        }
        Arrays.sort( (Object[]) activePlayers);

        // assuming Player has a compareTo method implemented
        if (activePlayers.length>1 && activePlayers[activePlayers.length - 1].getSusLevel() == activePlayers[activePlayers.length - 2].getSusLevel()) {
            System.out.println("Tie! No one is ejected.");
        } else {
            System.out.println(activePlayers[activePlayers.length - 1].getName() + " is ejected!");
            activePlayers[activePlayers.length - 1].setFrozen(true);
        }
        gameOver();
    }

    public String toString() {
        String frozenString = this.isFrozen() ? "frozen" : "not frozen";
        String parentString = super.toString();
        String returnString =parentString + " I am an "
                + this.skill + " player!";
        if (this.getSusLevel() <= 15) {
            return returnString;
        }else{
            return returnString.toUpperCase();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RedAstronaut)) {
            return false;
        }
        RedAstronaut other = (RedAstronaut) o;
        return super.equals(o) && 
            ((this.skill == null && other.skill == null) || 
                (this.skill != null && this.skill.equals(other.skill)));
    }

}
