import processing.core.PApplet;

public class Scythe extends MeleeWeapon{
    
	public Scythe(PApplet reference) {
		super(reference);
        weaponImage = reference.loadImage("scythe.png");
        // Set subclass-specific values
        this.maxAttackDistance = 50;  // The distance the sword moves forward when attacking
        this.attackSpeed = 0.03f;     // Speed of the attack animation
        // Calculate the position of the sword relative to the player
        weaponOffsetDistance = (-weaponImage.height / 5.5f);
        
        // Resize the sword image
        float scale = 0.40f;
        this.weaponImage.resize((int) (weaponImage.width * scale), (int) (weaponImage.height * scale));
        
        holdingAngle = (5 * PApplet.PI / 4);
        holdingPosition = 1.3f;
        attackArcStart = PApplet.QUARTER_PI;
        attackArcEnd = ((4/3) * PApplet.PI);
	}	

}
