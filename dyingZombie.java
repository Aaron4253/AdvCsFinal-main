import javax.swing.ImageIcon;

class dyingZombie{
    private double x;
    private int y;
    private int runTime;
    private int currentFrame;
    private int pictureIndex = 0;
    private boolean removed = false;
    private boolean frozen;
    private ImageIcon[] pictures = {new ImageIcon("graphics/dyingZombieFrames/frame_00_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_01_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_02_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_03_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_04_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_05_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_06_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_07_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_08_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_09_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_10_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_11_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_12_delay-0.07s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_13_delay-0.07s.gif")};
    //for slowed zombies
    private ImageIcon[] pictures1 = {new ImageIcon("graphics/dyingZombieFrames/frame_00_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_01_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_02_delay-0.9s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_03_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_04_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_05_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_06_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_07_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_08_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_09_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_10_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_11_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_12_delay-0.09s.gif"),
                                    new ImageIcon("graphics/dyingZombieFrames/frame_13_delay-0.09s.gif")};
    public dyingZombie(double x, int y, int runTime, boolean frozen){
        this.x = x;
        this.y = y;
        this.runTime = runTime;
        currentFrame = 0;
        this.frozen = frozen;
    }

    public void setX(Double x){
        this.x = x;
    }
    public Double getX(){
        return x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public int getRunTime(){
        return runTime;
    }
    
    public void incrementFrame(){
        currentFrame++;
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isRemoved() {
        return this.removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public ImageIcon getPicture(){
        if(currentFrame%40 == 0){
            pictureIndex++;
            if(pictureIndex == pictures.length){
                removed = true;
            }
        }   
        if(frozen){
            return pictures1[pictureIndex%pictures1.length];
        }     
        return pictures[pictureIndex%pictures.length];
    }


    public dyingZombie copy(){
        return new dyingZombie(x, y, runTime, frozen);
    }
}