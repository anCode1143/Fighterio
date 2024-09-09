import processing.core.PApplet;

public abstract class CameraMove {
	
    public static int offsetX = 0;
    public static int offsetY = 0;
	
    public static void cameraMove(PApplet reference) {
        if (reference.keyPressed) 
        {
            if (reference.key == 'w' || reference.key == 'W') {
                offsetY += 10; // Move up
            }
            if (reference.key == 's' || reference.key == 'S') {
                offsetY -= 10; // Move down
            }
            if (reference.key == 'a' || reference.key == 'A') {
                offsetX += 10; // Move left
            }
            if (reference.key == 'd' || reference.key == 'D') {
                offsetX -= 10; // Move right
            }
            // Constrain the camera offsets so the viewport doesn't move out of the border
            offsetY = PApplet.constrain(offsetY, -MySketch.PLANE_HEIGHT + reference.height, 0);
            offsetX = PApplet.constrain(offsetX, -MySketch.PLANE_WIDTH + reference.width, 0);
        }
    }
}