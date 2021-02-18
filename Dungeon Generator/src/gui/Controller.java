package gui;
import java.util.ArrayList;
import java.util.HashMap;
import pundugod.Chamber;
import pundugod.Door;
import pundugod.Main;
import pundugod.Passage;
import pundugod.PassageSection;
import pundugod.Space;
import pundugod.Level;


public class Controller {
    private GuiDemo myGui;
    private Level level;


    private static ArrayList<String>viewDesc = new ArrayList();
    private static HashMap<String,String> descMap = new HashMap<>();
    private static HashMap<String,Integer> descW = new HashMap<>();
    private static HashMap<String,Integer> descL = new HashMap<>();
    private static HashMap<String,Chamber>  chamberMap = new HashMap<>();


    public Controller(GuiDemo theGui) {
        myGui = theGui;
        level = new Level();

    }

    public ArrayList<String> getNameList() {
        int n = 1;
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Chamber> chamber = level.getChamberList();
        ArrayList<Passage> passage = level.getPassageList();
        for(Chamber c: chamber) {
            nameList.add("Chamber " + n);
            descMap.put("Chamber " + n, c.getDescription());
            descW.put("Chamber " + n, c.getChamberWid());
            descL.put("Chamber " + n, c.getChamberLen());
            chamberMap.put("Chamber " + n, c);
            n++;
        }
        n = 1;
        for(Passage p: passage) {
            nameList.add("Passage " + n);
            descMap.put("Passage " + n, p.getDescription());
            descW.put("Passasge " + n, 4);
            descL.put("Passage  " + n, 10);

            n++;
        }
        return nameList;
    }

    public String getCPDesc(String s) { //returns passage or chamber description
        return descMap.get(s);
    }


    public String getNewDescription() {
        return String.join("\n", getNameList());
    }

    public int getWidth(String s) {
        if (descW.get(s) == null) {
            return 20;
        }
       return  descW.get(s);
    }

    public int getLength(String s) {

        if (descL.get(s) == null) {
            return 3;
        }
        return descL.get(s);
    }

    public int getDoorNums(String s) {
        if (s.contains("Passage")){
            return 2;
        }
        return chamberMap.get(s).getDoors().size();
    }

    public void addTreasureC(String s) {

        chamberMap.get(s).addTreasure();

    }

    public void addMonsterC(String s) {
        chamberMap.get(s).addMonster();
    }
    public String getFullDescription(){
        return level.getDescription();
    }




}
