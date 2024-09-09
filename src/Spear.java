import processing.core.PApplet;

public class Spear extends MeleeWeapon{
    
	public Spear(PApplet reference) {
		super(reference);
        weaponImage = reference.loadImage("spear.png");
        // Set subclass-specific values
        this.maxAttackDistance = 50;  // The distance the sword moves forward when attacking
        this.attackSpeed = 0.06f;     // Speed of the attack animation
        // Calculate the position of the sword relative to the player
        weaponOffsetDistance = (-weaponImage.height / 18);
        
        // Resize the sword image
        float scale = 0.35f;
        this.weaponImage.resize((int) (weaponImage.width * scale), (int) (weaponImage.height * scale));
        
        holdingAngle = (5 * PApplet.PI / 4);
        holdingPosition = 1.5f;
        
        attackArcStart = 0;
        attackArcEnd = (2*PApplet.PI/3);
	}

}
