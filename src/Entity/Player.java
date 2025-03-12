package Entity;
import Main.Panel;
import Main.KeyHandler;

public class Player extends Entity{
    public Panel gP;
    public KeyHandler kH;

    public Player(Panel gP, KeyHandler kH) {
        this.gP = gP;
        this.kH = kH;
    }
}
