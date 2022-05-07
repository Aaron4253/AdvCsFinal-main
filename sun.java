class sun{
    private int x;
    private double y;
	private boolean removed;
    public sun(int x, double y){
        this.x = x;
        this.y = y;
		removed = false;
    }

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
    
    public void decreaseY(){
        y+=0.01;
    }

	public void setRemoved(boolean r){
		this.removed = r;
	}

	public boolean isRemoved(){
		return removed;
	}

}