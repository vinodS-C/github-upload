

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FileCountGroupbyMonth {
	
	public static void main(String[] args) throws IOException {
		File folder = new File("D:/Notes");
	    File[] listOfFiles = folder.listFiles();
	    Format format = new SimpleDateFormat("MMM");
	    Map<String,Integer> createdMonthCount=new HashMap<String, Integer>();
	    Map<String,Integer> modifiedMonthCount=new HashMap<String, Integer>();
	    String dateFormat="";
	   	    for (int i = 0; i < listOfFiles.length; i++) {
		    	Path file = listOfFiles[i].toPath();
			    BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
			    dateFormat =format.format(attr.creationTime().to(TimeUnit.MILLISECONDS));
			      if(createdMonthCount.containsKey(dateFormat)) {
			    	  createdMonthCount.put(dateFormat,createdMonthCount.get(dateFormat)+1);
			          }else {
			        	  createdMonthCount.put(dateFormat,1);
			      }
			      dateFormat =format.format(attr.lastModifiedTime().to(TimeUnit.MILLISECONDS));
			      if(modifiedMonthCount.containsKey(dateFormat)) {
			    	  modifiedMonthCount.put(dateFormat,modifiedMonthCount.get(dateFormat)+1);
			          }else {
			        	  modifiedMonthCount.put(dateFormat,1);
			      }
	    }
	   	 System.out.println(" Created Group Date ");
	   	createdMonthCount.forEach((k,v)->System.out.println(" " + k + "  : " + v));
		 System.out.println(" Modified  Group Date");
		 modifiedMonthCount.forEach((k,v)->System.out.println(" " + k + "  : " + v));
	}
	
}

// Out Put 
/*Created Group Date 
Jul  : 7
Oct  : 1
Aug  : 1
Sep  : 1
Modified  Group Date
Jul  : 5
Oct  : 1
Apr  : 2
Jan  : 1
Sep  : 1*/
