package gpdeng.puzzle;

import java.util.concurrent.Callable;

interface IBird {
	Egg lay();
}

class Chicken implements IBird, Callable {
	public Chicken()
	{
	}

	public static void main(String[] args) throws Exception
	{
		Chicken chicken = new Chicken();
		System.out.println(chicken instanceof IBird);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Egg lay()
	{
		// TODO Auto-generated method stub
		return new Egg(this);
	}

	@Override
	public Object call() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}
}

class Egg {
	int hatchCount = 1;
	String eggType = "";

	public Egg(Callable<IBird> createBird)
	{
		eggType = createBird.getClass().getName();

	}

	public IBird hatch() throws Exception
	{
		hatchCount--;
		if (hatchCount < 0)
			throw new IllegalStateException("already hatched");
		return new Chicken();
	}
}
