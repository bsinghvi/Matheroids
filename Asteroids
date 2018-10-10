package Matheroids;

public class Asteroids 
{
	double[] xAs   = {-45,380,750,-75,800,-45,380,750};
	double[] yAs   = {100,45,100,340,340,540,595,540};
	double[] xSpeed= {0.15,  0,-0.15,0.15,-0.15, 0.15,   0,-0.15};
	double[] ySpeed= {0.10,0.1, 0.10,0.00, 0.00,-0.10,-0.1,-0.10};
	double xPos, yPos;
	int width, height;
	double xS, yS; 
	boolean alive; 
	public Asteroids(int number) 
	{
		xPos=xAs[number];
		yPos=yAs[number];
		xS=xSpeed[number];
		yS=ySpeed[number];
		width=80;
		height=80;
		alive=true;
	}
	public void setXpos(double location)
	{
		xPos=location;
	}
	public void setYpos(double location)
	{
		yPos=location;
	}
	public void setXSpeed(double number)
	{
		xS=number;
	}
	public void setYSpeed(double number)
	{
		yS=number;
	}
	public void setLife(boolean checker)
	{
		alive=checker;
	}
	public double getXSpeed()
	{
		return xS;
	}
	public double getYSpeed()
	{
		return yS;
	}
	public boolean getLife()
	{
		return alive;
	}
	public double getXpos()
	{
		return xPos;
	}
	public double getYpos()
	{
		return yPos;
	}
}
