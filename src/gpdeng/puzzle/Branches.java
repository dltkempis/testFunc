package gpdeng.puzzle;

import java.util.HashSet;

public class Branches {
	public static int count(int[] tree)
	{
		int rs = 0;
		HashSet<Integer> branches = new HashSet<Integer>();
		if (tree.length <= 1)
			return 0;
		// rs =0;
		for (int i = 0; i < tree.length; i++)
		{
			if (tree[i] != -1 && !branches.contains(tree[i]))
			{
				branches.add(new Integer(tree[i]));
			}
		}
		rs = branches.size();

		return rs;
	}

	public static void main(String[] args)
	{
		System.out.println(Branches.count(new int[] { 1, 3, 1, -1, 3 }));
		// System.out.println(Branches.count(new int[] { 1, 2, -1, 2, 3 }));
	}
}