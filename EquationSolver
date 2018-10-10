package Matheroids;

import javax.swing.JOptionPane;

public class EquationSolver
{
	String answer;
	public EquationSolver()
	{
	}
	public String AnswerSolver(String stringy)
	{	
		String equation = stringy;
		answer = solve(equation);
		return answer;
	}
	public static String solve(String equation)
	{
		if(equation.contains(" + "))
		{
			equation = equation.replace(" + ", "+");
		}
		if(equation.contains(" = "))
		{
			equation = equation.replace(" = ", "=");
		}
		if(equation.contains(" - "))
		{
			equation = equation.replace(" - ", "+-");
		}
		else
		{
			equation = equation.replace("-", "+-");
		}
		equation.replace("+x", "+1x");
		equation.replace("-x", "-1x");
		String [] sides = equation.split("=");
		for(int i = 0; i < 2; i++)
		{
			if(sides[i].contains("("))
			{
				if(sides[i].contains("(+-"))
				{
					sides[i]=sides[i].replace("(+-", "(-");
				}
				sides[i] = Distribute(sides[i]);
			}
		}
		String answer = Solve(sides);
		return answer;
	}
	 public static String Distribute(String string) 
		{
			String multiplier = "";
			String multiplicand = "";
			String temp = "";
			boolean x = false;
			boolean part2 = false;
			String [] parts = string.split("[+]");
			for(int i = 0; i < parts.length; i++)
			{
				x = false;
				if(parts[i].contains("("))
				{
					for(int j = 0; j < parts[i].length(); j++)
					{
						if(parts[i].charAt(j)=='(')
						{
							part2 = true;
						}
						else if(parts[i].charAt(j)!='x' && part2==false)
						{	
							multiplier += parts[i].charAt(j);
						}
						else if (parts[i].charAt(j)!='x' && part2==true)
						{	
							multiplicand += parts[i].charAt(j);
						}
						else if(parts[i].charAt(j)=='x')
						{	
							x = true;
						}
					}
					if(x==true)
					{
						if(multiplicand.equals(""))
						{
							parts[i] = multiplier+"x";
						}
						else if(multiplicand.equals("-"))
						{	
							parts[i] = (Integer.parseInt(multiplier))*-1+"x";
						}
						else
						{
							parts[i] = (Integer.parseInt(multiplier))*(Integer.parseInt(multiplicand))+"x";
						}
					}
					else
					{
						parts[i] = (Integer.parseInt(multiplier))*(Integer.parseInt(multiplicand))+"";
					}
				}
				if(parts[i].contains(")"))
				{
					multiplicand = "";
					for(int j = 0; j < parts[i].length(); j++)
					{
						if(parts[i].charAt(j)==')')
						{
							break;
						}
						else if(parts[i].charAt(j)!='x')
						{
							multiplicand += parts[i].charAt(j);
						}
						else if(parts[i].charAt(j)=='x')
						{
							x = true;
						}
					}
					if(x==true)
					{
						if(multiplicand.equals(""))
						{
							parts[i] = multiplier+"x";
						}
						else if(multiplicand.equals("-"))
						{
							parts[i] = (Integer.parseInt(multiplier))*-1+"x";
						}
						else
						{
							parts[i] = (Integer.parseInt(multiplier))*(Integer.parseInt(multiplicand))+"x";
						}
					}
					else
					{
						parts[i] = (Integer.parseInt(multiplier))*(Integer.parseInt(multiplicand))+"";
					}
				}
			}
			for(int i = 0; i < parts.length; i++)
			{
				if(i==(parts.length-1))
				{
					temp += parts[i];
				}
				else
				{
					temp += parts[i]+"+";
				}
			}
			for(int i=0; i<parts.length; i++)
			{
				System.out.println(parts[i]);
			}
			System.out.println(temp);
			return temp;
		}
	public static String Solve(String [] sides)
	{
		String [] left = sides[0].split("[+]");
		String [] right = sides[1].split("[+]");
		
		int leftCoefficient = 0;
		int leftTerm = 0;
		int rightCoefficient = 0;
		int rightTerm = 0;
		for(int i = 0; i < left.length; i++)
		{
			if(left[i].contains("x"))
			{
				System.out.println(left[i]);
				String temp = left[i].replace("x","");
				if(temp.equals("-")||temp.equals(""))
				{
					temp = temp+"1";
				}	
				leftCoefficient += Integer.parseInt(temp);
			}
			else if(!left[i].equals("+"))
			{
				if(!left[i].equals(""))
				{
					leftTerm += Integer.parseInt(left[i]);
				}
			}
		}
		for(int i = 0; i < right.length; i++)
		{
			if(right[i].contains("x"))
			{
				System.out.println(right[i]);
				String temp = right[i].replace("x","");
				if(temp.equals("-")||temp.equals(""))
				{
					temp = temp+"1";
				}	
				rightCoefficient += Integer.parseInt(temp);
			}
			else if(!right[i].equals("+"))
			{
				if(!right[i].equals(""))
				{
					rightTerm += Integer.parseInt(right[i]);
				}
			}
		}
		System.out.println(rightTerm);
		System.out.println(leftTerm);
		System.out.println(rightCoefficient);
		System.out.println(leftCoefficient);
		String x = (rightTerm-leftTerm) + "/" + (leftCoefficient-rightCoefficient);
		System.out.println(x);
		return x;
	}
}
