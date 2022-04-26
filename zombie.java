import javax.swing.ImageIcon;

class zombie{
    Double x;
    int y;
    int health;
    int damage;
    int delay;
    int currentFrame;
    boolean isEating;
    Double movementSpeed;
    private int pictureIndex = 0;
    private boolean removed;
    private boolean frozen;
    private ImageIcon[] pictures = {new ImageIcon("graphics/zombieWalkingFrames/frame_00__delay-0.07s.gif"), 
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_01_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_02_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_03_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_04_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_05_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_06_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_07_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_08_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_09_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_10_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_11_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_12_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_13_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_14_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_15_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_16_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_17_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_18_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_19_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_20_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_21_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_22_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_23_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_24_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_25_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_26_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_27_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_28_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_29_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_30_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_31_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_32_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_33_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_34_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_35_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_36_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_37_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_38_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_39_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_40_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_41_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_42_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_43_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_44_delay-0.14s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_44_delay-0.14s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_45_delay-0.07s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_46_delay-0.07s.gif")};
        private ImageIcon[] pictures1 = {new ImageIcon("graphics/zombieWalkingFrames/frame_00__delay-0.09s.gif"), 
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_01_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_02_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_03_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_04_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_05_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_06_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_07_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_08_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_09_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_10_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_11_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_12_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_13_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_14_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_15_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_16_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_17_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_18_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_19_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_20_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_21_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_22_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_23_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_24_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_25_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_26_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_27_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_28_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_29_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_30_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_31_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_32_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_33_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_34_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_35_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_36_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_37_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_38_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_39_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_40_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_41_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_42_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_43_delay-0.09s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_44_delay-0.18s.gif"),
                                    new ImageIcon("graphics/zombieWalkingFrames/frame_45_delay-0.09s.gif")};
    public zombie(Double x, int y, int health, int damage, boolean frozen){
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.currentFrame = 0;
        this.isEating = false;
        this.movementSpeed = 0.0005;
        this.frozen = frozen;
    }

    public Double getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHealth(){
        return health;
    }

    public int getDamage(){
        return damage;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public Double getMovementSpeed() {
        return this.movementSpeed;
    }

    public void setMovementSpeed(Double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public boolean isFrozen(){
        return this.isEating;
    }

    public void setFrozen(boolean f){
        this.frozen = f;
    }

    public boolean isEating(){
        return this.isEating;
    }

    public void incrementX(){
        if(isEating){
            x-= 0.0;
        }else{
            x -= movementSpeed;
        }
    }

    public void deductHp(int hp){
        this.health -= hp;
    }

    public void setIsEating(boolean eating){
        this.isEating = eating;
    }

    public void incrementFrame(){
        currentFrame++;
    }

    public ImageIcon getPicture(){
        if(currentFrame%25 == 0){
            pictureIndex++;
            if(pictureIndex == pictures.length){
                removed = true;
            }
        }        
        int index = pictureIndex - 1;
        if(index < 0){
            index = 0;
        }
        if(frozen){
            return pictures1[pictureIndex % pictures1.length];
        }
        return pictures[pictureIndex % pictures.length];
    }


    public zombie copy(){
        return new zombie(x, y, health, damage, frozen);
    }
}