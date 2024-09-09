import processing.core.PApplet;

public class Background extends CameraMove {
    public static void display(PApplet reference, int screenX, int screenY) {
        reference.background(230);
        
        // Draw the rectangle with a lighter green color
        reference.stroke(210);
        reference.fill(35, 150, 35); // Light green color
        reference.strokeWeight(10);
        reference.rect(5 + offsetX, 5 + offsetY, screenX - 10, screenY - 10); // Adjust the size to fit within the canvas
        
        // Draw the grid lines with a darker green color
        reference.stroke(25, 100, 25); // Darker green color
        reference.strokeWeight(3);
        for (int i = 10 + offsetY; i <= screenY - 10 + offsetY; i += 25) {
            reference.line(0 + offsetX, i, screenX + offsetX, i); // horizontal backdrop lines
        }
        for (int i = 10 + offsetX; i <= screenX - 10 + offsetX; i += 25) {
            reference.line(i, 0 + offsetY, i, screenY + offsetY); // vertical backdrop lines
        }
    }
}
