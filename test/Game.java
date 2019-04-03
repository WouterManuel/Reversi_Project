public interface Game{

    public int getSquare(int i,int j);

    public void setSquare(int i,int j, byte value);

    public void playMove(int i,int j);

	public void highlight(int i, int j);

	public void highlightRemove(int i, int j);

	public void highlightPossible(int i, int j);

}

