import processing.core.PApplet;
import java.util.ArrayList;

public class MySketch extends PApplet {
	public static void main(String[] args) {
        PApplet.main("MySketch");
    }
	
    public static final int SCREENX = 1400;
    public static final int SCREENY = 780;
    public static final int PLANE_WIDTH = 5000;
    public static final int PLANE_HEIGHT = 5000;
    
    public void settings(){
      size(SCREENX, SCREENY);
    }

    Player player;
    static ArrayList<Civilian> civilians;
    
    public void setup() {
      size(SCREENX, SCREENY);
      player = new Player(this); // Pass 'this' PApplet instance to Player
      civilians = new ArrayList<Civilian>();// Create 100 Civilian instances
      for (int i = 0; i < 100; i++) {
          civilians.add(new Civilian(this));
      }
    }

    public void draw() {
      Background.display(this, PLANE_WIDTH, PLANE_HEIGHT);
      player.display();
      MiniMap.display(this, PLANE_WIDTH, PLANE_HEIGHT, player);
      CameraMove.cameraMove(this);
      for (Civilian civilian : civilians) { // Update and display each civilian
          civilian.display();
      }
      if (keyPressed) {
    	    if (key == 't' || key == 'T') {
    	        System.out.println(Player.y - CameraMove.offsetY);
    	    }
    	}
      
    }
    public void mousePressed() {
        player.attack(); // Assuming your player object is named `player`
    }
}
