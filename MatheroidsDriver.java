package Matheroids;

import java.applet.Applet;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MatheroidsDriver extends Applet implements Runnable, MouseListener, KeyListener
{
	Image pic1, pic2, pic3, pic4;
	QuestionsClass[] equations = new QuestionsClass[20];
	String[][] answers = new String[20][8];
	Asteroids[] asteroid = new Asteroids[8];
	double[][] stars = new double[100][3];
	Laser laser = new Laser();
	int questionsAnswered=0;
	int x = 370;
	int y = 318;
	ShipClass ship = new ShipClass();
	double RatioX = 0;
	double RatioY = 0;
	double RatioX1 = 1; 
	double RatioY1 = 0;
	double xRun = 0;
	double yRun = 0;
	double acceleration = 0;
	Image[] hearts = new Image[6];
	int lives = 3; 
	boolean paused = false;
	double bYrun = 0;
	double bXrun = 0;
	boolean lasered = false;
	String message = "";
	int messageX = 900;
	long startTime;
	long time;
	int minutes = 0;
	String seconds = "00";
	boolean start = false;
	int score = 0;
	int heartCounter = 0;
	int newHeart = 0;
	double newHeartX=800;
	double newHeartY=-50;
	int messageFont = 30;
	
	public void init()
	{
		setSize(800,650);
		setBackground(Color.BLACK);
		setFocusable(true);
		for(int i=0; i<stars.length; i++)
		{
			stars[i][0]=(Math.random()*800)+50;
			stars[i][1]=(Math.random()*650)+50;
			stars[i][2]=(Math.random()*10)+2;
		}
		for(int i=0; i<8; i++)
		{
			asteroid[i] = new Asteroids(i);
		}
		for(int i=0; i<6; i++)
		{
			hearts[i] = getImage(getDocumentBase(),"heart.png");
		}
		pic2 = getImage(getDocumentBase(),"Title.png");
		pic3 = getImage(getDocumentBase(),"START.png");
		pic4 = getImage(getDocumentBase(),"Asteroid.png");
		newHeart = (int) Math.random()*1000+4000;
	}
	public void start()
	{
		Thread th = new Thread(this);
		addMouseListener(this);
		addKeyListener(this);
		th.start();
	}
	public void stop(){}
	public void paint(Graphics g)
	{
		if(start==false)
		{
			g.setColor(Color.WHITE);
			for(int i=0; i<stars.length; i++)
			{
				g.fillArc((int)(stars[i][0]), (int)(stars[i][1]), (int)(stars[i][2]), (int)(stars[i][2]),100,100);
			}
			g.setColor(new Color(139, 69,19));
			g.fillRect(0, 0, 25, 650);
			g.fillRect(775, 0, 25, 650);
			g.fillRect(0, 0, 800, 25);
			g.fillRect(0, 625, 800, 25);
			g.setColor(Color.GRAY);
			g.drawImage(pic3,50,140,700,400,this);
			g.fill3DRect(240, 540, 150, 50,true);
			g.fill3DRect(440, 540, 150, 50,true);
			g.setColor(new Color(0, 238, 0));
			g.setFont(new Font("Times Roman", Font.BOLD, 30));
			g.drawString("EASY", 275, 575);
			g.drawImage(pic2,50,30,710,120,this);
			g.setColor(Color.RED);
			g.drawString("HARD", 472, 575);
		}
		else if(start==true)
		{
			g.setColor(Color.YELLOW);
			g.drawLine(x+(int)(ship.radius*Math.cos(ship.getPosition()*(Math.PI)/180))+ship.radius, y+(int)(ship.radius*Math.sin((ship.getPosition())*(Math.PI)/180))+ship.radius, x-(int)(ship.radius*Math.cos((ship.getPosition()-ship.angle)*(Math.PI)/180))+ship.radius, y-(int)(ship.radius*Math.sin((ship.getPosition()-ship.angle)*(Math.PI)/180))+ship.radius);
			g.drawLine(x+(int)(ship.radius*Math.cos(ship.getPosition()*(Math.PI)/180))+ship.radius, y+(int)(ship.radius*Math.sin((ship.getPosition())*(Math.PI)/180))+ship.radius, x-(int)(ship.radius*Math.cos((ship.getPosition()+ship.angle)*(Math.PI)/180))+ship.radius, y-(int)(ship.radius*Math.sin((ship.getPosition()+ship.angle)*(Math.PI)/180))+ship.radius);
			g.drawLine(x+ship.radius, y+ship.radius, x-(int)(ship.radius*Math.cos((ship.getPosition()-ship.angle)*(Math.PI)/180))+ship.radius, y-(int)(ship.radius*Math.sin((ship.getPosition()-ship.angle)*(Math.PI)/180))+ship.radius);
			g.drawLine(x+ship.radius, y+ship.radius, x-(int)(ship.radius*Math.cos((ship.getPosition()+ship.angle)*(Math.PI)/180))+ship.radius, y-(int)(ship.radius*Math.sin((ship.getPosition()+ship.angle)*(Math.PI)/180))+ship.radius);
			g.setColor(Color.WHITE);
			for(int i=0; i<50; i++)
			{
				g.fillArc((int)(stars[i][0]), (int)(stars[i][1]), (int)(stars[i][2]), (int)(stars[i][2]),100,100);
			}
			g.setFont(new Font("Times Roman", Font.BOLD, 20));
			for(int i=0; i<8; i++)
			{
				if(asteroid[i].getLife()==true&&questionsAnswered!=20)
				{
					g.setColor(Color.GRAY);
					g.drawImage(pic4,(int)asteroid[i].getXpos(), (int)asteroid[i].getYpos(), asteroid[i].width,  asteroid[i].height,this);
					g.setFont(new Font("Chalkboard", Font.BOLD, 21));
					g.setColor(Color.RED);
					g.drawString(answers[questionsAnswered][i],(int)asteroid[i].getXpos()+10,(int)asteroid[i].getYpos()+50);
				}
			}
			if(questionsAnswered!=20)
			{
				g.setColor(new Color(255, 246, 143));
				g.setFont(new Font("Chalkboard", Font.BOLD, 30));
				g.drawString(equations[questionsAnswered].getEquation(), 300, 60);
			}
			if(paused==true)
			{
				g.setColor(Color.BLACK);
				g.fill3DRect(0, 0, 800, 650, false);
			}
			g.setColor(new Color(255, 246, 143));
			g.drawRect(742, 15, 16, 40);
			g.drawRect(764, 15, 16, 40);
			g.setFont(new Font("Times Roman", Font.BOLD, 20));
			g.drawString("Score: " + score, 610, 60);
			g.setFont(new Font("Times Roman", Font.BOLD, 25));
			g.drawString(minutes + ":" + seconds, 380, 20);
			g.setFont(new Font("Courier New", Font.BOLD, 35));
			g.drawString("               Paused               ", messageX, 250);
			g.drawString("Press 'Pause' Button Again to Resume", messageX, 290);
			g.setColor(new Color(238, 232, 205));
			g.drawLine(0, 70, 800, 70);
			for(int i=0; i<lives; i++)
			{
				g.drawImage(hearts[i], i*25, 25, 18, 18, this);
			}
			if(lives<5)
			{
				g.drawImage(hearts[5], (int)(newHeartX), (int)(newHeartY), 30, 30, this);
			}
			if(questionsAnswered==20||lives==0)
			{
				g.setFont(new Font("Courier New", Font.BOLD, 120));
				if(lives==0)
				{
					g.drawString("GAME OVER",100,290);
				}
				if(questionsAnswered==20)
				{
					g.drawString("YOU WON!",140,290);
				}
				g.setFont(new Font("Courier New", Font.BOLD, 40));
				g.drawString("Would you like to play again?", 60, 500);
				g.setColor(Color.GRAY);
				g.fill3DRect(240, 540, 150, 50,true);
				g.fill3DRect(440, 540, 150, 50,true);
				g.setColor(new Color(238, 232, 205));
				g.setFont(new Font("Times Roman", Font.BOLD, 30));
				g.drawString("YES", 320, 575);
				g.drawImage(pic2,50,70,710,120,this);
				g.setColor(Color.RED);
				g.drawString("NO", 520, 575);
			}
			if(lasered==true)
			{
				g.setColor(Color.RED);
				g.fillRect((int)laser.getXpos(),(int)laser.getYpos(), laser.width, laser.height);
			}
		}
	}
	@Override
	public void run() 
	{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while(true)
		{	
			if(start==true&&paused==false)
			{
				heartCounter+=1;
				if(heartCounter>=newHeart&&lives<5)
				{
					//System.out.println("Counter: " + heartCounter + " New Heart: " + newHeart );
					newHeartY+=0.6;
					newHeartX-=0.6;
					if(newHeartX<=0)
					{
						newHeartX=800;
						newHeartY=-50;
						newHeart = (int) (Math.random()*1000)+4000;
						heartCounter=0;
					}
				}
				for(int i=0; i<8; i++)
				{
					asteroid[i].setXpos(asteroid[i].getXpos()+asteroid[i].getXSpeed());
					asteroid[i].setYpos(asteroid[i].getYpos()+asteroid[i].getYSpeed());
				}
				if(questionsAnswered!=20&&lives!=0)
				{
					time = (System.currentTimeMillis() - startTime) / 1000;
				}
				seconds = time+"";
				if(time<10)
				{
					seconds = "0"+time;
				}
				if(time>=60)
				{
					startTime=System.currentTimeMillis(); 
					minutes+=1;
				}
				x= (int) (x+xRun * RatioX);
				if(lasered==true)
				{
					laser.setXpos((int) (laser.getXpos() + 15* bXrun));
					laser.setYpos((int) (laser.getYpos() + 15* bYrun));
				}
				if(questionsAnswered==20||lives==0)
				{
					newHeart=10000000;
					for(int a=0; a<8; a++)
					{
						asteroid[a].setLife(false);
					}
					lasered=false;
				}
				CheckHit();
				CheckShipHit();
			}
			repaint();
			try
			{
				Thread.sleep(40);
			}
			catch(InterruptedException Ex)
			{
			}
		}
	}
	public void CheckHit()
	{
		for(int a=0; a<8; a++)
		{
			if(asteroid[a].getXpos()>840||asteroid[a].getXpos()<-100||asteroid[a].getYpos()>600||asteroid[a].getYpos()<-100)
			{
				if(asteroid[a].getLife()==true)
				{
					lives-=1;
					questionsAnswered+=1;
					for(int i=0;i<8;i++)
					{
						asteroid[i]=new Asteroids(i);
					}
					break;
				}
			}
			if(laser.getXpos()>=asteroid[a].getXpos()&&laser.getXpos()<=asteroid[a].getXpos()+asteroid[a].width)
			{
				if(laser.getYpos()>=asteroid[a].getYpos()&&laser.getYpos()<=asteroid[a].getYpos()+asteroid[a].height)
				{
					if(asteroid[a].getLife()==true)
					{
						//System.out.println(equations[questionsAnswered].checkAnswer(answers[questionsAnswered][a]));
						laser.setXpos(370);
						laser.setYpos(-120);
						if(equations[questionsAnswered].checkAnswer(answers[questionsAnswered][a])==true)
						{
							score+=100;
							questionsAnswered+=1;
							for(int i=0;i<8;i++)
							{
								asteroid[i]=new Asteroids(i);
							}
							lasered=false;
							break;
						}
						if(equations[questionsAnswered].checkAnswer(answers[questionsAnswered][a])==false)
						{
							score-=100;
							asteroid[a].setLife(false);
							for(int i=0; i<8; i++)
							{
								asteroid[i].setXSpeed(asteroid[i].getXSpeed()*1.2);
								asteroid[i].setYSpeed(asteroid[i].getYSpeed()*1.3);
							}
							lasered=false;
							break;
						}
					}
				}
			}
		}
		if(laser.getXpos()>=newHeartX && laser.getXpos()<=newHeartX+40)
		{
			if(laser.getYpos()>=newHeartY && laser.getYpos()<=newHeartY+40)
			{
				if(lives<5)
				{
					laser.setXpos(370);
					laser.setYpos(-120);
					lasered=false;
					newHeartX=800;
					newHeartY=-50;
					lives=lives+1;
					newHeart = (int) Math.random()*200+100;
					heartCounter=0;
				}
			}
		}
	}
	public void CheckShipHit()
	{
		for(int a=0; a<8; a++)
		{
			if(ship.getXpos()+ship.radius>=asteroid[a].getXpos() && ship.getXpos()+ship.radius<=asteroid[a].getXpos()+asteroid[a].width)
			{
				if(ship.getYpos()+ship.radius>=asteroid[a].getYpos() && ship.getYpos()+ship.radius<=asteroid[a].getYpos()+asteroid[a].height)
				{
					if(asteroid[a].getLife()==true)
					{
						questionsAnswered+=1;
						for(int i=0;i<8;i++)
						{
							asteroid[i]=new Asteroids(i);
						}
						lives-=1;
						lasered=false;
						break;
					}
				}
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(paused==false&&start==true&&lives>0&&questionsAnswered<20)
		{
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ) 
			{
				ship.setPosition((int)(ship.getPosition()+15.05));
				RatioX1 = Math.cos(ship.getPosition() * (Math.PI)/180);
				RatioY1 = Math.sin(ship.getPosition() * (Math.PI)/180);
			} 
			if (e.getKeyCode() == KeyEvent.VK_LEFT ) 
			{
				ship.setPosition((int)(ship.getPosition()-15.05)); 
				RatioX1 = Math.cos(ship.getPosition() * (Math.PI)/180);
				RatioY1 = Math.sin(ship.getPosition() * (Math.PI)/180);
			} 
			if (e.getKeyCode() == KeyEvent.VK_SPACE) 
			{
				if(laser.getYpos()<10||laser.getYpos()>650||laser.getXpos()<10||laser.getXpos()>800)
				{
					lasered = true;
					bYrun = RatioY1;
					bXrun = RatioX1;
					laser.setXpos(ship.getXpos()+ship.radius);
					laser.setYpos(ship.getYpos()+ship.radius);	
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(questionsAnswered==20||lives==0)
		{
			if(e.getY()>=540 && e.getY()<=540+50)
			{
				if(e.getX()>=240 && e.getX()<=240+150)
				{
					for(int i=0;i<8;i++)
					{
						asteroid[i]=new Asteroids(i);
					}
					minutes=0;
					seconds="00";
					startTime = System.currentTimeMillis()+40;
					questionsAnswered=0;
					score=0;
					lives=3;
					start=false;
				}
				else if(e.getX()>=440 && e.getX()<=440+150)
				{
					System.exit(0);
				}
			}	
		}
		if(e.getX()>=742 && e.getX()<=742+36 && e.getY()>=15 && e.getY()<=15+40)
		{
			paused=!paused;
			if(paused==true)
			{
				messageX = 20;
			}
			else
			{
				message = "";
				messageX = 1050;
			}
		}
		if(start==false)
		{
			if(e.getY()>=540 && e.getY()<=540+50)
			{
				if(e.getX()>=440 && e.getX()<=440+150)
				{
					for(int i=0;i<20;i++)
					{
						equations[i]= new QuestionsClass(i,true);
						for(int a=0; a<8; a++)
						{
							answers[i][a]=equations[i].choices[a];
						}
						int spot=(int)(Math.random()*8);
						answers[i][spot]= equations[i].answer;
					}
					for(int i=0; i<8; i++)
					{
						asteroid[i].setXSpeed(asteroid[i].getXSpeed()*1.5);
						asteroid[i].setYSpeed(asteroid[i].getYSpeed()*1.5);
					}
				}
				else if(e.getX()>=240 && e.getX()<=240+150)
				{
					for(int i=0;i<20;i++)
					{
						equations[i]= new QuestionsClass(i);
						for(int a=0; a<8; a++)
						{
							answers[i][a]=equations[i].choices[a];
						}
						int spot=(int)(Math.random()*8);
						answers[i][spot]= equations[i].answer;
					}
				}
				start=!start;
				startTime = System.currentTimeMillis();
			}	
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
