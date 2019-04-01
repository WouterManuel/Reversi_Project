package test;

public interface Game{

    public int getSquare(int i,int j);

    public void setSquare(int i,int j,int value);

    public void handleClick(int i,int j);

	public void highlight(int i, int j);

	public void highlightRemove(int i, int j);

	public void highlightPossible(int i, int j);

}

