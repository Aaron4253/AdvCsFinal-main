import javax.swing.ImageIcon;

class incineratedZombie{
    private double x;
    private int y;
    private int runTime;
    private int currentFrame;
    private int pictureIndex = 0;
    private boolean removed;
    private ImageIcon[] pictures = {new ImageIcon("graphics/IncineratedZombieFrames/frame_00.gif"), 
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_01.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_02.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_03.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_04.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_05.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_06.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_07.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_08.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_09.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_10.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_11.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_12.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_13.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_14.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_15.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_16.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_17.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_18.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_19.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_20.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_21.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_22.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_23.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_24.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_25.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_26.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_27.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_28.gif"),
                                    new ImageIcon("graphics/IncineratedZombieFrames/frame_29.gif")};
                               
    public incineratedZombie(double x, int y, int runTime){
        this.x = x;
        this.y = y;
        this.runTime = runTime;
        currentFrame = 0;       
        this.removed = false;
        
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
    public boolean isRemoved() {
        return this.removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
    public ImageIcon getPicture(){
        if(currentFrame%50 == 0){
            pictureIndex++;
            if(pictureIndex == pictures.length){
                removed = true;
            }
        }        
        return pictures[pictureIndex%pictures.length];
    }

    public incineratedZombie copy(){
        return new incineratedZombie(x, y, runTime);
    }
}