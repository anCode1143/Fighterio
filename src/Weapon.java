import processing.core.PApplet;
import processing.core.PImage;

public abstract class Weapon {
    protected PApplet reference;
    protected boolean isAttacking;
    protected float attackProgress = 0;
    protected float attackSpeed;
    protected float[] directions = new float[2];
    protected float weaponOffsetDistance;
    protected float holdingAngle = 0;
    protected float holdingPosition; 
    protected PImage weaponImage;
    public static boolean someoneDied = false;
    
	public void displayWeapon( ) {}
	
	public void attack() {}
	
}