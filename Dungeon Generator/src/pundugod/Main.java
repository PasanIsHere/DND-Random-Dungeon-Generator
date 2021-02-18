package pundugod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {
    private static HashMap<Chamber,ArrayList<Door>> chamberDoorMap;
    private static HashMap<Door, Door> doorConnections;
    private static ArrayList<Chamber> chamberList = new ArrayList<>();
    public static void main(String[] args) {

        chamberDoorMap = new HashMap<>();
        doorConnections = new HashMap<>();
        String description = "";

        for (int i = 0; i<5; i++) { //generate 5 chambers
            chamberList.add(new Chamber());
            chamberDoorMap.put(chamberList.get(i), chamberList.get(i).getDoors());
            description += chamberList.get(i).getDescription() + "\n";
        }

        for (int i = 0; i < 5; i++) {
             for (int j = 0; j < chamberDoorMap.get(chamberList.get(i)).size(); j++) {
                 description += "Chamber " + (i + 1) + " Door " + (j+1) +" connects to" + getTarget(i,j);
            }
        }
        System.out.print(description);
    }

    /***
     * Selects the targeter for a chamber door
     * @param i  chamber number
     * @return string
     */
     public static String getTarget(int i, int j) {
         Random rand = new Random();
         int x;
         int y;
         Passage doorPassage = new Passage();
         doorPassage.addPassageSection( new PassageSection("passage goes straight for 10 ft"));
         do {
             x = rand.nextInt((4) + 1);
         } while( x == i+1);

         y = rand.nextInt((chamberDoorMap.get(chamberList.get(i)).size()-1 + 1));
         String cd = " Chamber " + (x+1) + " Door " + (y+1) + "\n" ;
         return cd;
    }
    public static ArrayList<Chamber> getChamber(){
         return chamberList;
    }
}
