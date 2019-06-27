package com.mansur.list;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvConsolidator {
	
	public static void main(String[] args) throws Exception {
		List<String> dcList = Arrays.asList(new String[] {"ADC", "AM2", "AMS15AD1a", "AUH1", "CGY3", "CH3", "CP1", "FRA8", "IAD35", "IAD36", "JPF", "LD5C", "LGW14", "LLG", "MEL11", "MU1", "PP1", "QTS", "SG2", "SY10", "SY3", "TR3"});
		String zipHome = "C:\\Users\\manskhan.ORADEV\\Desktop\\Kranthi Gautam TDE fix Script\\CR10797395\\CR10797395\\";
		String outputFile = "C:\\Users\\manskhan.ORADEV\\Desktop\\Kranthi Gautam TDE fix Script\\consolidated.csv";
		
		List<String> allDirs = new ArrayList<String>();

		List<String> dcZipHomeDirs = new ArrayList<String>();
		for(String dc : dcList) {
			dcZipHomeDirs.add(zipHome + dc);
			allDirs.addAll(getListOfDir(zipHome + dc));
		}
		//System.out.println(dcZipHomeDirs);

		//printList(allDirs);

		List<Path> csvPaths = new ArrayList<Path>();
		for(String dir : allDirs) {
			String file = getFile(dir);
			//System.out.println("File: " + file);
			csvPaths.add(Paths.get(file));
		}
		List<String> mergedLines = getMergedLines(csvPaths);
//		for(String line: mergedLines) {
//			System.out.println(line);
//		}
		
		writeToFile(outputFile, mergedLines);
	}
	
	private static void writeToFile(String outputFile, List<String> lines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
		for(String line: lines) {
			writer.write(line);
		    writer.newLine();
		}
		writer.close();

	}
	
	private static void printList(List<String> list){
		for(String li: list) {
			System.out.println(li);
		}
	}
	
	private static List<String> getListOfDir(String pathToDir) throws IOException {
		List<String> dirList = new ArrayList<String>();
		File file = new File(pathToDir);
		File[] directories = file.listFiles(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		//System.out.println(Arrays.toString(directories));
		for(File dir : directories) {
			dirList.add(dir.getCanonicalPath());
			//dirList.add(dir.getName());
		}

		return dirList;
	}
	
	private static String getFile(String dir) throws IOException {
		File parentDir = new File(dir);
		String[] files = parentDir.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isFile();
			  }
			});
		File csvFile = null;
		if(files != null) {
			csvFile = new File(parentDir, files[0]);
		}
		return csvFile == null ? null: csvFile.getCanonicalPath();
	}
	
	private static List<String> getMergedLines(List<Path> paths) throws IOException {
	    List<String> mergedLines = new ArrayList<> ();
	    for (Path p : paths){
	        List<String> lines = Files.readAllLines(p, Charset.forName("UTF-8"));
	        if (!lines.isEmpty()) {
	            if (mergedLines.isEmpty()) {
	                mergedLines.add(lines.get(0)); //add header only once
	            }
	            mergedLines.addAll(lines.subList(1, lines.size()));
	        }
	    }
	    return mergedLines;
	}
	
	

}
