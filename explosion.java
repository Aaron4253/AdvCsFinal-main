import javax.swing.ImageIcon;

class explosion{
    private double x;
    private int y;
    private int runTime;
    private int currentFrame;
    private int pictureIndex = 0;
    private boolean removed;
    private ImageIcon[] pictures = {new ImageIcon("graphics/explosionEffectFrames/frame_00_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_01_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_02_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_03_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_04_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_05_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_06_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_07_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_08_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_09_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_10_delay-0.1s.gif"),
                                    new ImageIcon("graphics/explosionEffectFrames/frame_11_delay-0.1s.gif")};
    public explosion(double x, int y, int runTime){
        this.x = x;
        this.y = y;
        this.runTime = runTime;
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

    public explosion copy(){
        return new explosion(x, y, runTime);
    }
}