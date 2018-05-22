package sdeng;

import org.apache.commons.lang.StringUtils;

public class CommonUtils
{

	public static boolean isStrInValues(String a, String[] strings)
	{
		boolean rs = false;
		for (String s : strings)
		{
			if (StringUtils.equals(a, s))
			{
				rs = true;
				break;

			}
		}
		return rs;
	}
}
