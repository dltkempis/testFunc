package sdeng;

import java.io.File;

public class FileIOUtil {
	
	
	public static void main(String [] args)
	{
		String folderStr = "D:/downloads/ÈÎÎñ×é_20150331_2108";
		File folder = new File(folderStr);
		
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            continue;
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	    }
		
		
	}

}
