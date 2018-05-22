package gpdeng.puzzle;

public class TestFrog {

	public static void main(String[] args)
	{
		// "Deleveled"
		String test = "Deleveled";
		// String a = String.valueOf(test.charAt(0)));
		System.out.println(String.valueOf(test.charAt(3)));

		System.out.println("test " + test + ", ways:" + isPalindrome(test));

		// for(int i=1;i<10;i++)
		// {
		// System.out.println("test "+i +", ways:"+ numberOfWays(i));
		// }
	}

	// A frog only moves forward, but it can move in steps 1 inch long or in
	// jumps 2 inches long.
	// A frog can cover the same distance using different combinations of steps
	// and jumps.
	// Write a function that calculates the number of different combinations a
	// frog can use to cover a given distance.
	// For example, a distance of 3 inches can be covered in three ways:
	// step-step-step, step-jump, and jump-step.
	public static int numberOfWays(int input)
	{
		if (input == 0)
		{
			return 0;
		} else if (input == 1)
		{
			return 1;
		} else if (input == 2)
		{
			return 2;
		} else
		{
			return numberOfWays(input - 1) + numberOfWays(input - 2);
		}

	}

	public static boolean isPalindrome(String input)
	{
		boolean rs = true;
		if (input.length() > 0)
		{
			for (int i = 0; i < input.length() / 2; i++)
			{

				if (!String.valueOf(input.charAt(i)).equalsIgnoreCase(
						String.valueOf(input.charAt(input.length() - i - 1))))
				{
					rs = false;
					break;
				}
			}
		}
		return rs;
		// if(input.length()%2 ==0)
		// {
		// for(int i=0;i<input.length()/2;i++)
		// {
		// System.out.println("index"+i);
		// if( input.charAt(i)!= input.charAt(input.length()-i-1))
		// {
		// System.out.println("index"+i+",left:" +input.charAt(i) +"right:"+
		// input.charAt(input.length()-i));
		// rs =false;
		// break;
		// }
		// }
		//
		// }else {
		// System.out.println();
		// for(int i=0;i<input.length()/2;i++)
		// {
		//
		// if( input.charAt(i)!= input.charAt(input.length()-i-1))
		// {
		// rs =false;
		// break;
		// }
		// }

		// }
		// }

	}
}
