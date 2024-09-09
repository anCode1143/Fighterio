
import processing.core.PApplet;

public class MiniMap extends CameraMove{
	
	public static void display(PApplet reference, int planeWidth, int planeHeight, Player player) 
	{
     float miniMapScale = 0.05f;         // Scale factor for the mini-map (5% of the plain size)
     float miniMapWidth = planeWidth * miniMapScale;
     float miniMapHeight = planeHeight * miniMapScale;
     float miniMapX = reference.width - miniMapWidth - 20;  // X position of the mini-map (top right corner)
     float miniMapY = 20;              // Y position of the mini-map (20 pixels from the top)

     // Draw mini-map background
     reference.fill(0, 0, 0, 100);               // Set fill color to black with transparency
     reference.rect(miniMapX, miniMapY, miniMapWidth, miniMapHeight);  // Draw the mini-map background

     // Draw player on mini-map
     reference.fill(47, 141, 255);               // Set fill color to blue
     // Calculate scaled position of the player
     float playerX = miniMapX + (Player.x + offsetX*-1) * miniMapScale;	// offsetX*-1 to reverse the
     float playerY = miniMapY + (Player.y + offsetY*-1) * miniMapScale;	// direction for minimap display of ball
     
     reference.ellipse(playerX, playerY, 10, 10);  // Draw the player as a small circle on the mini-map
	}
	
}
