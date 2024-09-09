import processing.core.PApplet;

public class Sword extends MeleeWeapon {

    // Constructor
    public Sword(PApplet reference) {
        super(reference);
        weaponImage = reference.loadImage("sword.png");
        // Subclass-specific values
        this.maxAttackDistance = 50;
        this.attackSpeed = 0.085f;
        // Calculate the position of the sword relative to the player
        weaponOffsetDistance = (-weaponImage.height / 15);
        
        // Resize the sword image
        float scale = 0.20f;
        this.weaponImage.resize((int) (weaponImage.width * scale), (int) (weaponImage.height * scale));
        
        holdingAngle = (5 * PApplet.PI / 4);
        holdingPosition = 1;
        attackArcStart = PApplet.QUARTER_PI;
        attackArcEnd = PApplet.PI;
    }
}
