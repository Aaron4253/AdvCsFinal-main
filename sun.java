class sun{
    private int x;
    private double y;
	private boolean removed;
	private double decrease;
    public sun(int x, double y, double decrease){
        this.x = x;
        this.y = y;
		removed = false;
		this.decrease = decrease;
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
        y+=decrease;
    }

	public void setRemoved(boolean r){
		this.removed = r;
	}

	public boolean isRemoved(){
		return removed;
	}

}