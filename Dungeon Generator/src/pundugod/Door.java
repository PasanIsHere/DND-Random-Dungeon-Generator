package pundugod;

import dnd.die.D10;
import dnd.die.D20;
import dnd.die.D6;
import dnd.models.Exit;
import dnd.models.Trap;
import java.util.ArrayList;
import java.util.Random;

public class Door {

    /**
    * Trap object.
     */
    private Trap myTrap;
    /**
     *var to check if door is arched.
     */
    private boolean isArch;
    /**
     * var to check if door is traped.
     */
    private boolean isTrap;
    /**
     * var to check if  door is opened.
     */
    private boolean isOpened;
    /**
     * var to if door is locked.
     */
    private boolean isLocked;
    /**
     * Trap object.
     */
    private Random rand;
    /**
     * var tol hold the spaces.
     */
    private ArrayList<Space> spaceList;

    /**
     * Door constructor.
     */
    public Door() {
        rand = new Random();
        myTrap = new Trap();
        D20 d20 = new D20();
        D6 d6 = new D6();
        D10 d10 = new D10();
        spaceList = new ArrayList<>();
        if (d20.roll() == 1) {  // chance door is trapped
            isTrap = true;
            myTrap.chooseTrap(rand.nextInt(20) + 1);
        } else {
            isTrap = false;
        }
        if (d6.roll() == 1) { //chance door is locked
            isLocked = true;
            isOpened = false;
        } else {
            isLocked = false;
            if (d6.roll() == 1) { //chance door is open
                isOpened = true;
            } else {
                isOpened = false;
            }
        }
        if (d10.roll() == 1) {
            isArch = true;
            isOpened = true;
            isTrap = false;
        } else {
            isArch = false;
        }
    }

    /**
     * Door sets up the door based on the Exit from the tables.
     * @param theExit exit from the table
     */
    public Door(Exit theExit) {

        rand = new Random();
        D20 d20 = new D20();
        D6 d6 = new D6();
        D10 d10 = new D10();
        myTrap = new Trap();

        if (d20.roll() == 1) {  // chance door is trapped
            isTrap = true;
            myTrap.chooseTrap(rand.nextInt(20) + 1);
        } else {
            isTrap = false;
        }
        if (d6.roll() == 1) { //chance door is locked
            isLocked = true;
            isOpened = false;
        } else {
            isLocked = false;
            if (d6.roll() > 3) { //chance door is open
                isOpened = true;
            } else {
                isOpened = false;
            }
        }
        if (d10.roll() == 1) {
            isArch = true;
            isTrap = false;
            isOpened = true;
        } else {
            isArch = false;
        }
    }


    /**
     * sets trap.
     * @param flag boolean if trap is true/false
     * @param roll the roll of the trap
     */
    public void setTrapped(boolean flag, int... roll) {
        // true == trapped.  Trap must be rolled if no integer is given
        isTrap = flag;
        if (roll.length > 0) {
            myTrap.chooseTrap(roll[0]);
        } else {
            myTrap.chooseTrap(rand.nextInt(20) + 1);
        }

    }

    /**
     * sets the door to be open or closed.
     * @param flag set open to true or false
     */
    public void setOpen(boolean flag) {
        isOpened = flag;
        if (isArch) {
            isOpened = true;
        }
    }

    /**
     * sets the the door to be an Arch or not.
     * @param flag set arch to true or false
     */
    public void setArchway(boolean flag) {
        isArch = flag;
        isOpened = true;
    }

    /**
     *checks if door is Trapped.
     *@return if the door is trapped or not
     */
    public boolean isTrapped() {
        return isTrap;
    }

    /**
     * checks if the door is Open.
     * @return if the door is Open or not
     */

    public boolean isOpen() {
        return isOpened;
    }

    /**
     * checks if door is an Arch.
     * @return if the door is an Arch or not
     */
    public boolean isArchway() {
        return isArch;
    }

    /**
     * gets the description of the trap.
     * @return Trap description
     */
    public String getTrapDescription() {
        return myTrap.getDescription();
    }

    /**
     * gets the list of spaces.
     * @return the space list
     */
    public ArrayList<Space> getSpaces() {
        return spaceList;
    }

    /**
     * sets the spaces the door is connected to.
     * @param spaceOne the entrance of the door (chamber/ passage)
     * @param spaceTwo the exit of the door (passage/chamber)
     */

    public void setSpaces(Space spaceOne, Space spaceTwo) {
        if (spaceList.isEmpty()) {
            spaceList.add(spaceOne);
            spaceList.add(spaceTwo);
        } else {
            spaceList.set(0, spaceOne);
            spaceList.set(1, spaceTwo);
        }
    }

    /**
     * gets the full description of the door.
     * @return description of the door
     */
    public String getDescription() {
        String strDoor = "";
        if (isArch) {
            strDoor = " is an arch,";
        }
        if (isLocked) {
            strDoor += " is locked, ";
        } else {
            strDoor += " is unlocked,";
        }
        if (isOpened) {
            strDoor += " open,";
        } else {
            strDoor += " closed,";
        }
        if (isTrap) {
            strDoor += " and trapped with " + myTrap.getDescription().trim();
        } else {
            strDoor += " and not trapped.";
        }
        return strDoor;
    }

}

