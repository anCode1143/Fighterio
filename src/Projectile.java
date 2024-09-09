import processing.core.PApplet;
import processing.core.PImage;

public class Projectile extends CameraMove{
    PApplet reference;
    PImage projectileImage;
    float x, y;
    float angle;
    float speed;
    float maxDistance;
    float distanceTraveled = 0;
    boolean active = true;
    
    public Projectile(PApplet reference, float startX, float startY, float angle, float speed, float maxDistance) {
        this.reference = reference;
        this.x = startX;
        this.y = startY;
        this.angle = angle;
        this.speed = speed;
        this.maxDistance = maxDistance;
        projectileImage = reference.loadImage("crossbowProjectile.png");
        
        float scale = 0.35f;
        projectileImage.resize((int) (projectileImage.width * scale), (int) (projectileImage.height * scale));
    }

    public void update() {
        if (active) {
            x += Math.cos(angle) * speed;
            y += Math.sin(angle) * speed;
            distanceTraveled += speed;
            if (distanceTraveled > maxDistance) {
                active = false; // Deactivate projectile after it travels maxDistance
            }
        }
    }

    public void display() {
        if (active) {
            // Save the current transformation matrix
            reference.pushMatrix();
            // Translate to the projectile's position
            reference.translate(x, y);
            // Rotate the coordinate system by the angle
            reference.rotate(angle);
            reference.image(projectileImage, 0, 0);
            // Restore the original transformation matrix
            reference.popMatrix();
        }
        
        if (reference.keyPressed) {
            if ((reference.key == 'w' || reference.key == 'W') && (Player.y - CameraMove.offsetY) > MySketch.SCREENY) {
                y += 10; // Move up
            }//
            if ((reference.key == 's' || reference.key == 'S') && 
            ((Player.y - CameraMove.offsetY) < MySketch.PLANE_HEIGHT- MySketch.SCREENY)) {
                y -= 10; // Move down
            }
            if ((reference.key == 'a' || reference.key == 'A') && (Player.x - CameraMove.offsetX) > MySketch.SCREENX) {
                x += 10; // Move left
            }//
            if ((reference.key == 'd' || reference.key == 'D') &&
            	(Player.y - CameraMove.offsetX) < MySketch.PLANE_WIDTH- MySketch.SCREENX) {
                x -= 10; // Move right
            }
        }
    }

    public boolean isColliding(Civilian civilian) {
        // Calculate the projectile's world coordinates
        float projectileWorldX = x - CameraMove.offsetX;
        float projectileWorldY = y - CameraMove.offsetY;

        // Calculate the distance between the projectile and the civilian
        float distanceX = Math.abs(projectileWorldX - civilian.x);
        float distanceY = Math.abs(projectileWorldY - civilian.y);

        // Adjust based on projectile and civilian size for collision detection
        return distanceX < 30 && distanceY < 30;
    }
}
