import processing.core.PApplet;

public class Crossbow extends RangedWeapon{
	public Crossbow(PApplet reference) {
		super(reference);
        weaponImage = reference.loadImage("crossbow.png");
        // Subclass-specific values
        maxAttackDistance = 1000;
        attackSpeed = 0.085f;
        
        // Resize the sword image
        float scale = 0.30f;
        weaponImage.resize((int) (weaponImage.width * scale), (int) (weaponImage.height * scale));
        
        // Calculate the position of the sword relative to the player
        weaponOffsetDistance = weaponImage.height/100;
        holdingPosition = 5;
        projectileImage = reference.loadImage("crossbowProjectile.png");
	}
}