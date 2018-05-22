package gpdeng.puzzle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CropRatio {
	private static double totalWeight = 0;
	// private static HashMap<String, Integer> crops = new HashMap<String,
	// Integer>();
	private HashMap<String, Integer> crops;

	public CropRatio()
	{
		crops = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getCrops()
	{
		return crops;
	}

	public void setCrops(HashMap<String, Integer> crops)
	{
		this.crops = crops;
	}

	public void add(String name, int cropWeight)
	{
		Integer currentCropWeight = crops.get(name);

		if (currentCropWeight == null)
		{
			currentCropWeight = new Integer(cropWeight);
			crops.put(name, currentCropWeight);
		} else
		{
			currentCropWeight = currentCropWeight + cropWeight;
			crops.put(name, currentCropWeight);
		}

		totalWeight += cropWeight;
		// totalWeight++;
	}

	// public double proportion(String name)
	// {
	// System.out.println("proportion");
	// printMap(crops);
	//
	// System.out.println(name);
	// System.out.println(crops.get(name));
	// System.out.println("totalWeight" + totalWeight);
	// double rs = 0.0;
	//
	// // Iterator it = crops.entrySet().iterator();
	// // while (it.hasNext())
	// // {
	// // Map.Entry pair = (Map.Entry) it.next();
	// // System.out.println("key" + pair.getKey());
	// // if (pair.getKey().equals(name))
	// // {
	// // rs = ((Integer) pair.getValue()) / totalWeight;
	// //
	// // return rs;
	// // }
	// // System.out.println(pair.getKey() + " = " + pair.getValue());
	// // it.remove(); // avoids a ConcurrentModificationException
	// // }
	// // return rs;
	//
	// return crops.get(name) / totalWeight;
	// }
	//
	public double proportion(String name)
	{
		System.out.println("proportion");
		printMap(this.crops);
		System.out.println(crops.get(name));

		double rs = 0.0;

		return crops.get(name) / totalWeight;
	}

	public static void printMap(HashMap mp)
	{
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + "=" + pair.getValue());
			// it.remove(); // avoids a ConcurrentModificationException
		}
	}

	@Override
	public String toString()
	{
		return "CropRatio [crops=" + crops + "]";
	}

	public static void main(String[] args)
	{
		CropRatio cropRatio = new CropRatio();

		cropRatio.add("Wheat", 4);
		cropRatio.add("Wheat", 5);
		cropRatio.add("Rice", 1);

		printMap(cropRatio.getCrops());

		// System.out.println("Fraction of wheat: "
		// + cropRatio.proportion("Wheat"));
		try
		{
			System.out.println("Fraction of Rice: "
					+ cropRatio.proportion("Wheat"));
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("second");
		printMap(cropRatio.getCrops());
	}
}
