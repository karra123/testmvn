package com.testmvn;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.*;
import java.nio.file.Paths;
import java.util.regex.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileSearch {
	
	public static Logger logger = LoggerFactory.getLogger(FileSearch.class);

//1 - Search Folder
//2 - Search Option File / Folder / Both
//3 - regex pattern
//4 - timeout in seconds

    public static void main(String[] args) {
		logger.debug("Starting ...");
		
		if (!checkArgs(args)) {
			printUsage();
			logger.debug("Stopping ...");
			return;
		}
		findInFolder(args[0], args[1], args[2]);
		logger.debug("Stopping ...");
	}
	
	public static boolean checkArgs(String [] args){
		if (args==null || args.length!=4) return false;
		else return true;
	}
	
	public static void printUsage(){
		System.out.println("Usage: java FileSearch Folder Option Regex Timeout");
	}
	
	public static void printArgs(String [] args){
		for (String arg : args){
			System.out.println(arg);
		}
	}
	
	public static void findInFolder(String folderPath, String option, String regex){
		try {
			File folder = new File(folderPath);
			if (option.equalsIgnoreCase("FILE")) {
				if (folder.isFile()) {
					if (Pattern.compile(regex).matcher(folderPath).matches()){
						System.out.println(folderPath);
					}
				}
				else if (folder.isDirectory()) {
					File[] files = folder.listFiles();
					for (File file : files) {
						findInFolder(file.getName(), option, regex);
					}
				}
			}
			else if (option.equalsIgnoreCase("FOLDER")) {
				if (folder.isDirectory()) {
					if (Pattern.compile(regex).matcher(folderPath).matches()){
						System.out.println(folderPath);
					}
					File[] files = folder.listFiles();
					for (File file : files) {
						findInFolder(file.getName(), option, regex);
					}
				}
			}
			else if (option.equalsIgnoreCase("BOTH")) {
				if (Pattern.compile(regex).matcher(folderPath).matches()){
					System.out.println(folderPath);
				}
				if (folder.isDirectory()) {
					File[] files = folder.listFiles();
					for (File file : files) {
						findInFolder(file.getName(), option, regex);
					}
				}
			}
		}
		catch (Exception e){
			
		}
	}
}
