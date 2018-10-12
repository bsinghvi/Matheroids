package Matheroids;

public class ShipClass 
{
	int xPos, yPos, radius, position, angle;
	public ShipClass() 
	{
		xPos = 370;
		yPos = 318;
		radius = 25;
		position = 0;
		angle = 40;
	}
	public void setXpos(int location)
	{
		xPos=location;
	}
	public void setYpos(int location)
	{
		yPos=location;
	}
	public int getXpos()
	{
		return xPos;
	}
	public int getYpos()
	{
		return yPos;
	}
	public void setPosition(int location)
	{
		position = location;
	}
	public int getPosition()
	{
		return position;
	}
}
