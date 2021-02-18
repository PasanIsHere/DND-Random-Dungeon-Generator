package pundugod;

import dnd.models.Monster;
import java.util.ArrayList;



public class Passage extends Space {

    /**
     * Array list to hold passage sections in the passage.
     */
    private ArrayList<PassageSection> thePassage;

    /**
     * Array list to hold  the doors in the passage.
     */
    private ArrayList<Door> doorList;

    /**
     * Passage constructor.
     */
    public Passage() {

        thePassage = new ArrayList<>();
        doorList = new ArrayList<>();

    }

    /**
     * gets all of the doors in the entire passage.
     * @return all doors in the passsage
     */
    public ArrayList getDoors() {
    return this.doorList;
    }

    /**
     * gets the door of a passage section.
     * @param i passage section #
     * @return  the door in section 'i'. If there is no door, returns null
     */
    public Door getDoor(int i) {
    try {
        return this.thePassage.get(i).getDoor();
    } catch (Exception e) {
        return null;
    }

}

    /**
     * adds a monster to section 'i' of the passage.
     * @param theMonster monster to add to the passage section
     * @param i the passage section
     */
    public void addMonster(Monster theMonster, int i) {
    this.thePassage.get(i).setMonster(theMonster);

}

    /**
     * gets the monster in section 'i' of the passage if there is one.
     * @param i passage section
     * @return monster
     */
    public Monster getMonster(int i) {

    try {
        return thePassage.get(i).getMonster();
    } catch (Exception e) {
        return null;
    }
}


    /**
     * adds the passage section to the the Passage.
     * @param toAdd passage section
     */
    public void addPassageSection(PassageSection toAdd) {
    this.thePassage.add(toAdd);

}

    /**
     * adds a door connection to the current Passage Section.
     * @param newDoor Door
     */
    @Override
    public void setDoor(Door newDoor) {
    thePassage.add(new PassageSection());

    try {
        this.thePassage.get(thePassage.size() - 1).setDoor(newDoor);
        this.doorList.add(newDoor);
    } catch (Exception e) {

    }

}

    /**
     * Gets the description of the whole passage.
     * @return String
     */
    @Override
public String getDescription() {

    String passageDesc = "";

    int i;

    for (i = 0; i < thePassage.size(); i++) {

        passageDesc += thePassage.get(i).getDescription() + "\n";
    }
    return passageDesc;
}

}
