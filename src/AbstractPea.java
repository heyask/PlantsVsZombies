import java.awt.Rectangle;

public abstract class AbstractPea implements OnLevelUpListener {
    private int posX;
    protected GamePanel gp;
    private int myLane;
    private PlantMovingStrategy plantMovingStrategy;


    public AbstractPea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
        gp.addLevelUpObservers(this);
    }

    public void setMovingStrategy(PlantMovingStrategy movingStrategy) {
        this.plantMovingStrategy = movingStrategy;
    }


    public void advance() {
		plantMovingStrategy.move();
    }


    /* edited */
    public boolean attackZombie(Zombie z, Rectangle pRect, Rectangle zRect, int i) {

        final boolean zombiehasHealth = z.getHealth() >= 0;
        boolean exit = false;

        if (pRect.intersects(zRect)) {
            z.setHealth(z.getHealth() - 300);

            if (!zombiehasHealth) {
                System.out.println("ZOMBIE DIED");
                gp.getLaneZombies().get(myLane).remove(i);
                gp.setLevelPoint(10);
                exit = true;
            }
        }
        return exit;
    }

    @Override
    public void onLevelUp() {

    }

    public int getPosX() {
        return plantMovingStrategy.getPosX();
    }

    public void setPosX(int posX) {
        plantMovingStrategy.setPosX(posX);
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
