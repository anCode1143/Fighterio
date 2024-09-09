import processing.core.PApplet;

public abstract class MeleeWeapon extends Weapon{
	
    protected float maxAttackDistance;
    protected float attackArcStart;
    protected float attackArcEnd;

    // Constructor to initialize the PApplet reference
    public MeleeWeapon(PApplet reference) {
        this.reference = reference;
    }
    
    @Override
    public void displayWeapon() {
        // Calculate the angle towards the mouse cursor, adding an offset for rotation
        float angle = PApplet.atan2(reference.mouseY - Player.y, reference.mouseX - Player.x) + holdingAngle;

	    // If the player is moving, update the direction tracking
	    if (Player.x != reference.mouseX || Player.y != reference.mouseY) {
	        directions[0] = directions[1];
	        directions[1] = angle;
	    } else {
	        // If the player is not moving, use the previous direction
	        angle = directions[1];
	    }

        // Adjust angle during attack to create a swinging arc
        if (isAttacking) {
            float attackAngle = attackArcStart - (attackArcEnd * attackProgress);
            angle += attackAngle;
        }
        
        // Prevent random dimming when drawn
        reference.blendMode(PApplet.BLEND);
        reference.noTint();
        
        float weaponX = Player.x + weaponOffsetDistance * PApplet.cos(angle);
        float weaponY = Player.y + weaponOffsetDistance * PApplet.sin(angle);

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
	}
    
    @Override
    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            attackProgress = 0;
        }
        kill();
	}
    
    public void kill() {
        boolean killed = false;

        float playerWorldX = Player.x - CameraMove.offsetX;
        float playerWorldY = Player.y - CameraMove.offsetY;

        for (Civilian iterator : MySketch.civilians) {
            float civilianWorldX = iterator.x;
            float civilianWorldY = iterator.y;

            float distanceDiffX = Math.abs(playerWorldX - civilianWorldX);
            float distanceDiffY = Math.abs(playerWorldY - civilianWorldY);
            
          //if civilian is within range, kill it
            if (distanceDiffX < maxAttackDistance && distanceDiffY < maxAttackDistance) {
                killed = true;
                break;
            }
        }

        if (killed) {
            MySketch.civilians.removeIf(c -> 
                c != null && 
                Math.abs(playerWorldX - c.x) < maxAttackDistance &&
                Math.abs(playerWorldY - c.y) < maxAttackDistance
            );
            someoneDied = true;
        }
    }
}
