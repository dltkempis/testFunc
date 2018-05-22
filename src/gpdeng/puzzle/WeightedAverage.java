package gpdeng.puzzle;

import java.util.Arrays;
import java.util.List;

public class WeightedAverage {
	public static double mean(List<Integer> numbers, List<Integer> weights)
			throws IllegalArgumentException
	{
		if (numbers == null || weights == null)
		{
			throw new IllegalArgumentException(
					"illegal argument.input array is null");
		} else if (numbers.size() != weights.size())
		{
			throw new IllegalArgumentException(
					"illegal argument.input arrays' size are not equal");
		}

		double total = 0;
		double totalWeights = 0;
		for (int i = 0; i < numbers.size(); i++)
		{
			total += (numbers.get(i) * weights.get(i));
			totalWeights += weights.get(i);
		}

		return total / totalWeights;
	}

	public static void main(String[] args)
	{
		List<Integer> a = Arrays.asList(new Integer[] { 3, 6 });
		List<Integer> b = Arrays.asList(new Integer[] { 4, 2 });

		System.out.println(WeightedAverage.mean(a, b));

		a = Arrays.asList(new Integer[] { 3, 1 });
		b = Arrays.asList(new Integer[] { 1, 1 });

		System.out.println(WeightedAverage.mean(a, b));
	}

}
