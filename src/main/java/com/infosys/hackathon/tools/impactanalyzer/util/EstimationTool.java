package com.infosys.hackathon.tools.impactanalyzer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EstimationTool {
	
	public String getComplexity(File file){
		
		int lineCount = 0; 
		if(lineCount < 20){
			return "simple";
		}else if (lineCount >= 20 && lineCount < 50){
			return "medium";
		}else {
			return "complex";
		}
		
	}
	
	private int countLines(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();		
		return lines;
	}
	
}
