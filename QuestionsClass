package Matheroids;

public class QuestionsClass 
{
	int coefficient, number1, number2, sum;
	String equation = "";
	String answer;
	EquationSolver solver = new EquationSolver();
	boolean check = false;
	String[] choices = new String[8];
	String[] terms;
	int leftTerm, rightTerm;
	public QuestionsClass(int received) 
	{
		coefficient = (int)(Math.random()*10)+1;
		number1 = (int)(Math.random()*15)+1;
		number2 = (int)(Math.random()*10)+1;
		sum = (int)(Math.random()*15)+1;
		int randomizer1=(int) (Math.random()*5+1);
		if(randomizer1%2==0)
		{
			equation = coefficient + "x + " + number1 + " = " + sum + " + " + number2 + "x";
		}
		else
		{
			equation = coefficient + "x + " + number1 + " = " + sum;
		}
		answer = solver.AnswerSolver(equation) +"";
		terms = answer.split("/");
		makeChoices(terms);
		if(Integer.parseInt(terms[1])==0)
		{
			answer="undefined";
		}
		else if(Integer.parseInt(terms[0])==0)
		{
			answer="0";
		}
		else if((Integer.parseInt(terms[0]))%(Integer.parseInt(terms[1]))==0)
		{
			answer=(Integer.parseInt(terms[0]))/(Integer.parseInt(terms[1]))+"";
		}
		else if(Integer.parseInt(terms[1])==1)
		{
			answer=terms[0];
		}
	}
	public QuestionsClass(int received, boolean mode) 
	{
		coefficient = (int)(Math.random()*10)+1;
		number1 = (int)(Math.random()*15)+1;
		number2 = (int)(Math.random()*10)+1;
		sum = (int)(Math.random()*15)+1;
		int randomizer1=(int) (Math.random()*5+1);
		int randomizer2=(int) (Math.random()*5+1);
		if(randomizer1%2==0)
		{
			if(randomizer2%2==0)
			{
				equation = coefficient + "(x + " + number1 + ") = " + sum + " + 2x";
			}
			else
			{
				equation = coefficient + "x + " + number1 + " = " + sum + " + " + number2 + "x";
			}
		}
		else
		{
			equation = coefficient + "x + " + number1 + " = " + sum;
		}
		answer = solver.AnswerSolver(equation) +"";
		terms = answer.split("/");
		makeChoices(terms);
		if(Integer.parseInt(terms[1])==0)
		{
			answer="undefined";
		}
		else if(Integer.parseInt(terms[0])==0)
		{
			answer="0";
		}
		else if((Integer.parseInt(terms[0]))%(Integer.parseInt(terms[1]))==0)
		{
			answer=(Integer.parseInt(terms[0]))/(Integer.parseInt(terms[1]))+"";
		}
		else if(Integer.parseInt(terms[1])==1)
		{
			answer=terms[0];
		}
	}
	public String getEquation()
	{
		return equation;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void makeChoices(String[] term)
	{
		leftTerm = Integer.parseInt(term[0]);
		rightTerm = Integer.parseInt(term[1]);
		if(rightTerm==0)
		{
			rightTerm=(int)(Math.random()*30+3);
		}
		if(leftTerm==0)
		{
			leftTerm=(int)(Math.random()*10+1);
		}
		choices[0]=((int)(leftTerm+Math.random()*50+3) + "/" + rightTerm);
		choices[1]=((int)(leftTerm-Math.random()*20+3) + "/" + rightTerm);
		choices[2]=((int)(rightTerm) + "/" + leftTerm);
		choices[3]=((int)(leftTerm*Math.random()*20+3) + "/" + (int)(rightTerm*Math.random()*20+3));
		choices[4]=(leftTerm + "/" + (int)(rightTerm-Math.random()*20+1));
		choices[5]=(leftTerm + "/" + (int)(rightTerm+Math.random()*50+1));
		choices[6]=((int)(rightTerm+1)+"");
		choices[7]=((int)(leftTerm+4)+"");
		checkChoices();
	}
	public void checkChoices()
	{
		for(int i=0; i<6; i++)
		{
			String choice[]=choices[i].split("/");
			if(Integer.parseInt(choice[1])==0)
			{
				choices[i]="undefined";
			}
			else if(Integer.parseInt(choice[0])==0)
			{
				choices[i]="0";
			}
			else if((Integer.parseInt(choice[0]))%(Integer.parseInt(choice[1]))==0)
			{
				choices[i]=(Integer.parseInt(choice[0]))/(Integer.parseInt(choice[1]))+"";
			}
			else if(Integer.parseInt(choice[1])==1)
			{
				choices[i]=choice[0];
			}
		}
	}
	public boolean checkAnswer(String answers)
	{
		if(answers.contains("/"))
		{
			String[] selected = answers.split("/");
			if(Integer.parseInt(selected[0])==leftTerm && Integer.parseInt(selected[1])==rightTerm)
			{
				check = true;
			}
			else
			{
				check = false;
			}
		}
		else
		{
			if(answers==answer)
			{
				check = true;
			}
			else
			{
				check = false;
			}
		}
		return check;
	}
}
