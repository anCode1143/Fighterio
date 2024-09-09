import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PImage;

public abstract class RangedWeapon extends Weapon{
    ArrayList<Projectile> projectiles = new ArrayList<>();
    float maxAttackDistance;
    float attackSpeed;
    float angle;
    PImage projectileImage;
    
    public RangedWeapon(PApplet reference) {
        this.reference = reference;
    }
    
	@Override
    public void displayWeapon() {
        // Calculate the angle towards the mouse cursor, adding an offset for rotation
        angle = PApplet.atan2(reference.mouseY - Player.y, reference.mouseX - Player.x) + holdingAngle;

	    // If the player is moving, update the direction tracking
	    if (Player.x != reference.mouseX || Player.y != reference.mouseY) {
	        directions[0] = directions[1];
	        directions[1] = angle;
	    } else {
	        // If the player is not moving, use the last valid direction
	        angle = directions[1];
	    }

        float weaponX = Player.x + PApplet.cos(angle);
        float weaponY = Player.y + PApplet.sin(angle);

        // Prevent random dimming when drawn
        reference.blendMode(PApplet.BLEND);
        reference.noTint();

        // Rotate and draw the scaled sword image
        reference.pushMatrix();
        reference.translate(weaponX, weaponY);
        reference.rotate(angle);
        reference.image(weaponImage, -weaponImage.width / 2, -weaponImage.height / holdingPosition);
        reference.popMatrix();

        // Update attack progress
        if (isAttacking) {
            attackProgress += attackSpeed;
            if (attackProgress >= 1) {
                isAttacking = false;
                attackProgress = 0;
            }
        }
        
        for (Projectile projectile : projectiles) {
            projectile.update();
            projectile.display();

            if (projectile.active) {
                for (Civilian civilian : MySketch.civilians) {
                    if (projectile.isColliding(civilian)) {
                        MySketch.civilians.remove(civilian);
                        someoneDied = true;
                        projectile.active = false; // Remove the projectile upon collision
                        break;
                    }
                }
            }
        }
        // Clean up inactive projectiles
        projectiles.removeIf(p -> !p.active);
	}
	
	@Override
	public void attack() {
		projectiles.add(new Projectile(reference, Player.x, Player.y, angle, 10, maxAttackDistance));
	}
}
