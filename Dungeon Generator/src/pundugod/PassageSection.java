package pundugod;

import dnd.die.D20;
import dnd.models.Monster;



/**
 * Passace Section.
 */
public class PassageSection {

    private boolean hasDoor;
    private boolean hasChamber;
    private boolean deadend;
    private D20 d20;
    private Monster myMonster;
    private Door myDoor;
    private String myString;

/***
 * sets up the 10 foot section with default settings.
 */
public PassageSection() {
    myString = "passage goes straight for 10 ft";
    deadend = false;
    hasChamber = false;
    hasDoor = false;

}

    /**
     * Sets up passage section based on a string given.
     * @param description get the description
     */
    public PassageSection(String description) {
    myString = description;
    hasDoor = false;
    if (description == "passage goes straight for 10 ft") {
        myString += "";
    } else if (description == "passage ends in Door to a Chamber") {
        myDoor = new Door();
        hasDoor = true;
    } else if (description == "archway (door) to right (main passage continues straight for 10 ft)") {
        myDoor = new Door();
        myDoor.setArchway(true);
        hasDoor = true;
    } else if (description ==  "archway (door) to left (main passage continues straight for 10 ft)") {
        myDoor = new Door();
        myDoor.setArchway(true);
        hasDoor = true;

    }  else if (description ==  "passage ends in archway (door) to chamber") {
        myDoor = new Door();
        myDoor.setArchway(true);
        hasDoor = true;
    } else if (description ==  "Stairs, (passage continues straight for 10 ft)") {
        myString += "";
    } else if (description == "Dead End") {
        myString += "";
    } else {
        myMonster = new Monster();
        myString += " which has " + myMonster.getMinNum() + " to " + myMonster.getMaxNum() + " " + myMonster.getDescription();
    }

}

    /**
     * gets the door that is in the passage section, if there is one.
     * @return Door
     */
    public Door getDoor() {

    try {
        return myDoor;
    } catch (Exception e) {
        return null;
    }
}

    /**
     * gets the monster that is in the passage section, if there is one.
     * @return Monster
     */
    public Monster getMonster() {
    //returns the monster that is in the passage section, if there is one
    try {
        return myMonster;
    } catch (Exception e) {
        return null;
    }
}

    /**
     * gets the description of the passage section.
     * @return String
     */
    public String getDescription() {
        return myString;
}

    /**
     * sets a monster to be in passage section.
     * @param monster Monster
     */
    public void setMonster(Monster monster) {
        myMonster = monster;
}

    /**
     * sets a door to passage section.
     * @param door Door
     */
    public void setDoor(Door door) {
    myDoor = door;
}
}

