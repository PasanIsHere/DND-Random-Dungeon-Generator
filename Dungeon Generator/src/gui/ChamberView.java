package gui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ChamberView extends GridPane {
    private String floor;
    private String treasure;
    private String door;
    private int length
            ;
    private int width;



    public ChamberView(int len, int wid, int doors){
        int i,j,k;
        floor = "/res/floor.png";
        treasure = "/res/tres.png";
        door = "/res/door.png";
        length = len;
        width = wid; //user these values to decide the size of the view and how many tiles


        Node[] tiles = makeTiles(len*width, width/2,width*len/2 , width*len - width/2, width*len/2 + width-1,doors);
        //should definitely be a loop and possibly a method
        k = 0;
        for(i = 0; i < len ; i++) {
            for (j = 0; j < wid; j++){
                add(tiles[k],i,j,1,1);
                k++;
            }
        }



    }


    private Node[] makeTiles(int numTiles, int j, int k ,int l, int p, int doors) {  //should have a parameter and a loop
        int i;

        Node[] toReturn = new Node[numTiles];
        for (i =0; i < numTiles; i++){
            toReturn[i] = (floorFactory(floor));
        }
        if (numTiles != 60){

            if (doors >= 1) {
                toReturn[j] = (floorFactory(door));

            }
            if (doors >= 2) {
                toReturn[k] = (floorFactory(door));

            }
            if (doors >= 3) {
                toReturn[l] = (floorFactory(door));

            }
            if (doors >= 4) {
                toReturn[p] = (floorFactory(door));

            }

        }else{
            toReturn[20] = (floorFactory(door));
            toReturn[39] =  (floorFactory(door));
        }

        return toReturn;
    }


    public Node floorFactory(String img) {
        Image floor = new Image(getClass().getResourceAsStream(img));
        Label toReturn = new Label();
        ImageView imageView = new ImageView(floor);
        imageView.setFitWidth(15);
        imageView.setFitHeight(15);
        toReturn.setGraphic(imageView);
        return toReturn;
    }


}
