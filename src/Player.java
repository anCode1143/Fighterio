import processing.core.PApplet;

class Player{
    PApplet reference;
    int colorR = 47;
    int colorG = 141;
    int colorB = 255;
    static int x;
    static int y;
    static int speed = 2; 
    

	//weapon instantiation
	Sword sword;  
	Scythe scythe;
	Spear spear;
	Crossbow crossbow;
    Weapon selectedWeapon;
    Weapon[] weapons = {};
    
    Player(PApplet reference) {
        this.reference = reference;
        x = reference.width / 2;   // Starting coordinates
        y = reference.height / 2;
        
    	sword = new Sword(reference);  
    	scythe = new Scythe(reference);
    	spear = new Spear(reference);
    	crossbow = new Crossbow(reference);
    	weapons = new Weapon[]{sword, scythe, spear, crossbow};
    	selectedWeapon = weapons[0]; //choose weapon
    }

    void display() {
        reference.fill(colorR, colorG, colorB);
        reference.stroke(230);
        reference.ellipse(x, y, 30, 30);
        moveTowardsCursor();
        selectedWeapon.displayWeapon();
        selectWeapon();
    }

    void moveTowardsCursor() {
        if (x >= reference.mouseX) { // movement logic; chasing the mouse given the negative/positive space between them
            if (y == reference.mouseY) { // conditional added to movement case ensuring same speed from diagonal to straight
                x -= speed * 0.5; // the movement speed when player is diagonal is at a sqrt(2)% increase
            }
            x -= speed;
        } else if (x <= reference.mouseX) {
            if (y == reference.mouseY) {
                x += speed * 0.5;
            }
            x += speed;
        }

        if (y >= reference.mouseY) {
            if (x == reference.mouseX) {
                y -= speed * Math.sqrt(2);
            }
            y -= speed;
        } else if (y <= reference.mouseY) {
            if (x == reference.mouseX) {
                y += speed * Math.sqrt(2);
            }
            y += speed;
        }

        if (Math.abs(y - reference.mouseY) <= 3) { // conditional prevent ball getting fuzzy when its at the cursor
            y = reference.mouseY;
        }
        if (Math.abs(x - reference.mouseX) <= 3) {
            x = reference.mouseX;
        }

        if (reference.keyPressed && (x >= 0 && x <= MySketch.SCREENX && y >= 0 && y <= MySketch.SCREENY)) 
        {
            if (reference.key == 'w' || reference.key == 'W') {
                y += speed; // Move up
            }
            if (reference.key == 's' || reference.key == 'S') {
                y -= speed; // Move down
            }
            if (reference.key == 'a' || reference.key == 'A') {
                x += speed; // Move left
            }
            if (reference.key == 'd' || reference.key == 'D') {
                x -= speed; // Move right
            }
        }
        
    }
    public void attack() {
    	selectedWeapon.attack();
    }
    
    public void selectWeapon() {
        if (reference.keyPressed) 
        {
            if (reference.key == '1') {
                selectedWeapon = weapons[0];
            }
            if (reference.key == '2') {
            	selectedWeapon = weapons[1];
            }
            if (reference.key == '3') {
            	selectedWeapon = weapons[2];
            }
            if (reference.key == '4') {
            	selectedWeapon = weapons[3];
            }
        }
    }
}
