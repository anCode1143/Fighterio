
import processing.core.PApplet;
import processing.core.PImage;

public class Civilian extends CameraMove {
    PApplet reference; // Reference from this class to PApplet instance in MySketch
    float x;
    float y;
    float speed;
    float directionX;
    float directionY;
    float height;
    float width;
    PImage npcImage;
    float skinShade;
    boolean isStartled = false;
    boolean closeToPlayer;
    float distanceToPlayer;
    boolean isRunning = false;

    Civilian(PApplet reference) {
        this.reference = reference;
        x = reference.random(4980);  // Random position within the 5000x5000 plane
        y = reference.random(4980);  // Random position within the 5000x5000 plane
        speed = .7f + reference.random(1); // Random speed for more variation
        directionX = reference.random(-1, 1);
        directionY = reference.random(-1, 1);
        int size = (int) reference.random(30, 50);
        npcImage = reference.loadImage("npc.png"); // Load the image
        float aspectRatio = (float) npcImage.width / npcImage.height;
        height = size;
        width = size * aspectRatio;
        skinShade = reference.random(155, 255);
    }
    
    public void display() {
        // Calculate the angle of direction vector
        float angle = PApplet.atan2(directionY, directionX) - PApplet.HALF_PI * 3; // 270 degree flip proper direction
        reference.tint(skinShade);
        
        reference.pushMatrix();
        // Offset moves with background
        reference.translate(x + CameraMove.offsetX, y + CameraMove.offsetY);
        reference.rotate(angle);
        reference.image(npcImage, -width / 2, -height / 2, width, height);
        reference.popMatrix();
        
        // Update distance to player and check if close to the player
        distanceToPlayer = PApplet.dist(x, y, Player.x - offsetX, Player.y - offsetY);
        closeToPlayer = (x - (Player.x - offsetX)) < MySketch.SCREENX && (y - (Player.y - offsetY)) < MySketch.SCREENY;

        
        if ((reference.mousePressed || isStartled) && closeToPlayer) {
        	isStartled = true;
            startledWalk(); // Startled behavior if mouse is pressed or close to the player
        }
        else {
            wander(); // Default wandering behavior
        }
    }
    
    public void wander() {
        // Edge detection and direction change
        if (x <= 20 || x >= 4980) {
            directionX *= -1;
            x = PApplet.constrain(x, 20, 4980);
        }
        if (y <= 20 || y >= 4980) {
            directionY *= -1;
            y = PApplet.constrain(y, 20, 4980);
        }
        
        // Simplified movement logic
        x += directionX * speed;
        y += directionY * speed;

        // Normalize direction vectors to keep them consistent
        float length = PApplet.sqrt(directionX * directionX + directionY * directionY);
        directionX /= length;
        directionY /= length;
        
        // Randomly change direction slightly for more natural movement
        if (reference.random(1) < 0.05) {
            directionX += reference.random(-0.5f, 0.5f);
            directionY += reference.random(-0.5f, 0.5f);
        }
    }
    
    public void startledWalk() {
        // Edge detection and direction change
        if (x <= 20 || x >= 4980) {
            directionX *= -1;
            x = PApplet.constrain(x, 20, 4980);
        }
        if (y <= 20 || y >= 4980) {
            directionY *= -1;
            y = PApplet.constrain(y, 20, 4980);
        }
    	
        if (isStartled && distanceToPlayer < 1500) {
            // Calculate the direction vector away from the player
            float directionAwayX = x - (Player.x - offsetX);
            float directionAwayY = y - (Player.y - offsetY);

            // Normalize the direction vector
            float length = PApplet.sqrt(directionAwayX * directionAwayX + directionAwayY * directionAwayY);
            directionAwayX /= length;
            directionAwayY /= length;

            // Apply a slightly randomized movement away from the player
            directionX = directionAwayX + reference.random(-0.3f, 0.3f);
            directionY = directionAwayY + reference.random(-0.3f, 0.3f);

            // Move the civilian
            x += directionX * speed;
            y += directionY * speed;
        }
        else {
        	isStartled = false;
            wander(); // If too far from the player, wander normally
        }
    }

    public void runAway() {
        // Edge detection and direction change
        if (x <= 20 || x >= 4980) {
            directionX *= -1;
            x = PApplet.constrain(x, 20, 4980);
        }
        if (y <= 20 || y >= 4980) {
            directionY *= -1;
            y = PApplet.constrain(y, 20, 4980);
        }
        
        if (isRunning && distanceToPlayer < 1500) {
        	Weapon.someoneDied = false;
            // Calculate the direction vector away from the player
            float directionAwayX = x - (Player.x - offsetX);
            float directionAwayY = y - (Player.y - offsetY);

            // Normalize the direction vector
            float length = PApplet.sqrt(directionAwayX * directionAwayX + directionAwayY * directionAwayY);
            directionAwayX /= length;
            directionAwayY /= length;

            // Set the civilian's direction to flee directly away from the player
            directionX = directionAwayX;
            directionY = directionAwayY;

            // Move the civilian away from the player at a faster speed
            x += directionX * (speed * 3);  // Increase speed to simulate panic
            y += directionY * (speed * 3);
        }
        else {
            isRunning = false; // Reset startle state only when far enough from the player
            wander(); // Go back to wandering behavior
        }
    }
}
