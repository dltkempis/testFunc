package gpdeng.puzzle;

import java.util.ArrayList;
import java.util.HashSet;

public class MovingTotal {
	boolean isUpdateNeed = false;
	int addArraySize = 0;
	ArrayList<Integer> linkedList = new ArrayList<Integer>();

	HashSet<Integer> totalList = new HashSet<Integer>();

	/**
	 * Adds/appends list of integers at the end of internal list.
	 */
	public void append(int[] list)
	{
		if (list.length < 1)
			return;
		if (list.length > 0)
			isUpdateNeed = true;
		addArraySize += list.length;
		for (int i : list)
		{
			linkedList.add(i);

		}
	}

	/**
	 * Returns boolean representing if any three consecutive integers in the
	 * internal list have given total.
	 */
	public boolean contains(int total)
	{

		boolean rs = false;
		if (linkedList.size() < 3)
			return rs;
		if (isUpdateNeed)
		{
			// totalList.clear();
			Integer sum = 0;
			int start = 0;
			if (linkedList.size() > 3)
			{
				if (addArraySize == 1)
					start = 1;
				else if (addArraySize == 2)
					start = 2;
				else
				{
					start = 3;
				}
			}
			for (int i = start; i < linkedList.size(); i++)
			{
				if ((i + 2) < linkedList.size())
				{
					sum = linkedList.get(i) + linkedList.get(i + 1)
							+ linkedList.get(i + 2);
					totalList.add(sum);
				}
			}
			isUpdateNeed = false;
			addArraySize = 0;
		}
		if (totalList.contains(total))
			rs = true;

		return rs;

	}

	public static void main(String[] args)
	{
		MovingTotal movingTotal = new MovingTotal();

		movingTotal.append(new int[] { 1, 2, 3 });

		System.out.println(movingTotal.contains(6));
		System.out.println(movingTotal.contains(9));

		movingTotal.append(new int[] { 4 });

		System.out.println(movingTotal.contains(9));
	}
}