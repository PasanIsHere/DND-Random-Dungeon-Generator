package pundugod;

import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;
import dnd.models.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Chamber extends Space {
	private Random rand = new Random();
	private ChamberContents myContents;
	private ChamberShape mySize;
	private ArrayList<Door> doorList = new ArrayList<>();
	private ArrayList<Monster> monsterList = new ArrayList<>();
	private ArrayList<Treasure> treasureList = new ArrayList<>();
	private String extras;

	/******************************
	 Required Methods for that we will test during grading
	 *******************************/
	/**
	 * constructor
	 */
	public Chamber() {
		doorList = new ArrayList<>();
		monsterList = new ArrayList<>();
		treasureList = new ArrayList<>();
		myContents = new ChamberContents();
		myContents.chooseContents((rand.nextInt(19) + 1));
		mySize = ChamberShape.selectChamberShape(rand.nextInt(10) + 1);
		mySize.setNumExits((rand.nextInt(14) + 1));
		contentHelper();
		extras = "";
	}

	/**
	 * Constructor with paramaters
	 *
	 * @param theShape    New shape of chamber
	 * @param theContents New content of chamber
	 */
	public Chamber(ChamberShape theShape, ChamberContents theContents) {
		doorList = new ArrayList<>();
		monsterList = new ArrayList<>();
		treasureList = new ArrayList<>();
		mySize = theShape;
		myContents = theContents;

	}

	/**
	 * sets the shape of the chamber
	 *
	 * @param theShape
	 */
	public void setShape(ChamberShape theShape) {
		mySize = theShape;

	}

	/**
	 * gets the doors of the chamber
	 *
	 * @returns reurns the doorList
	 */
	public ArrayList<Door> getDoors() {
		return doorList;
	}

	/**
	 * add Monster to the function
	 *
	 * @param theMonster the monster to be added
	 */
	public void addMonster(Monster theMonster) {
		monsterList.add(theMonster);
	}

	/**
	 * @return
	 */
	public ArrayList<Monster> getMonsters() {
		return monsterList;
	}

	/**
	 * @param theTreasure
	 */
	public void addTreasure(Treasure theTreasure) {
		treasureList.add(theTreasure);
	}

	/**
	 * @return
	 */
	public ArrayList<Treasure> getTreasureList() {
		return treasureList;
	}

	/**
	 * @return
	 */
	@Override
	public String getDescription() {
		String strDoors = "";
		String strDimensions = "";
		String strContents = "whose contents are " + myContents.getDescription() + extras +":";
		try {
			strDimensions = mySize.getLength() + "x" + mySize.getWidth();

		} catch (UnusualShapeException Error) {
			strDimensions = mySize.getArea() + "ft^2";
		}
		strContents += getTreasureDescription() + getMonsterDescription();
		strDoors += getDoorDescriptions();
		return "There is a " + strDimensions + " " + mySize.getShape() + " chamber " + strContents + "\n  " + doorList.size() + " Doors in the Chamber:\n" + strDoors;
	}

	/**
	 * @param newDoor
	 */
	@Override
	public void setDoor(Door newDoor) {
		doorList.add(newDoor);
		newDoor.setSpaces(this, null);
	}

	/**
	 *
	 */
	private void contentHelper() {

		if (myContents.getDescription().contains("monster")) {
			Monster myMonster = new Monster();
			myMonster.setType(rand.nextInt(100) + 1);
			monsterList.add(myMonster);

		}
		if (myContents.getDescription().contains("treasure")) {
			Treasure myTreasure = new Treasure();
			myTreasure.setContainer(rand.nextInt(20) + 1);
			myTreasure.setDescription(rand.nextInt(100) + 1);
			treasureList.add(myTreasure);
		}


	}

	private void genExits() {
		ArrayList exitsList = mySize.getExits();
		Iterator itExits = exitsList.iterator();
		while (itExits.hasNext() && doorList.size() < 4) {
			Exit curExit = (Exit) itExits.next();
			doorList.add(new Door(curExit));
		}
		if (doorList.isEmpty()) { //initial
			doorList.add(new Door());
		}

	}

	private String getTreasureDescription() {
		String t = "";
		for (int i = 0; i < treasureList.size() ; i++) {
			t += "\n  Treasure: " + treasureList.get(i).getDescription() + " contained in a " + treasureList.get(i).getContainer();

			try {
				t += " protected by " + treasureList.get(i).getProtection();
			} catch (NotProtectedException Error) {
				t += (" protected by nothing,");
			}
		}
		return t;
	}


	private String getMonsterDescription() {
		String m = "";
		for (int i = 0; i < monsterList.size() ; i++) {
			m += "\n  Monsters: " + monsterList.get(i).getMinNum() + " to " + monsterList.get(i).getMaxNum() + " " + monsterList.get(i).getDescription();
		}
		return m;
	}

	private String getDoorDescriptions() {
		String d = "";
		genExits();
		for (int i = 0; i < doorList.size(); i++) {
			d += "      Door " + (i + 1) + doorList.get(i).getDescription() + "\n";
		}
		return d;
	}

	/**
	 * returns length of chamber
	 * @return int length
	 */
	public int getChamberLen() {
		return mySize.getLength();
	}
	/**
	 * returns width of chamber
	 * @return int width
	 */
	public int getChamberWid() {
		return mySize.getWidth();
	}

	public void addMonster() {
		Monster myMonster = new Monster();
		myMonster.setType(rand.nextInt(100) + 1);
		monsterList.add(myMonster);
		extras += "(extra monster added)";

	}

	public void addTreasure() {
		Treasure myTreasure = new Treasure();
		myTreasure.setContainer(rand.nextInt(20) + 1);
		myTreasure.setDescription(rand.nextInt(100) + 1);
		treasureList.add(myTreasure);
		extras += "(extra treasure added)";

	}


}