package AmidstUs;

import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate{
    private int numTasks;
    private int taskSpeed;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name) {
        super(name, 15); // susLevel default to 15
        this.numTasks = 6; // default number of tasks
        this.taskSpeed = 10; // default task speed
    }

    @Override
    public void completeTask() {
        // TODO Auto-generated method stub
        if (this.isFrozen()) {
            return; // cannot complete tasks if frozen
        }
        if (taskSpeed > 20) {
            this.numTasks -=2;
        }else{
            this.numTasks --;
        }
        if (this.numTasks <0){
            this.numTasks = 0; // cannot have negative tasks
        }
        if (this.numTasks==0){
            System.out.println("I have completed all my tasks!");
            this.setSusLevel(this.getSusLevel()/2);
        }
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
        String returnString =parentString + " I have "
                + this.numTasks + " left over.";
        if (this.getSusLevel() > 15){
            return returnString.toUpperCase();
        }else{
            return returnString;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlueAstronaut)) {
            return false;
        }
        BlueAstronaut other = (BlueAstronaut) o;
        return super.equals(o) &&
            this.numTasks == other.numTasks &&
            this.taskSpeed == other.taskSpeed;
}

}
