class zombie{
    Double x;
    int y;
    int health;
    int damage;
    int delay;
    int currentFrame;
    boolean isEating;
    Double movementSpeed;
    private boolean frozen;
    private final int randomState;
    public zombie(Double x, int y, int health, int damage, boolean frozen){
        this.x = x;
        this.y = y;
        this.health = health;
        this.damage = damage;
        this.currentFrame = 0;
        this.isEating = false;
        this.movementSpeed = 0.0007;
        this.frozen = frozen;
        this.randomState = (int)(Math.random()*(5-1+1)+1);
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

    public int getRandomState(){
        return randomState;
    }


    public zombie copy(){
        return new zombie(x, y, health, damage, frozen);
    }
}