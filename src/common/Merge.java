package common;

import java.util.ArrayList;

public class Merge {
	public static void mergeFiles(String iFileDir, String oFileName) throws InterruptedException{
		String oFileDir = oFileName.substring(0, oFileName.lastIndexOf("/"));
		ArrayList<String> fileNameList = common.Read.readDir(iFileDir);
		for(String fileName : fileNameList){
			String iFileName = iFileDir+"/"+fileName;
			ArrayList<String> linesList = common.Read.readFile(iFileName, true, true);
			common.Write.writeToFile(oFileDir, oFileName, linesList, true);
		}
	}
}
