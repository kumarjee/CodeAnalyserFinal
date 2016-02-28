package com.infosys.hackathon.tools.impactanalyzer.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class ClassNameIdentifier {

	public List<String>  getFileNames(Set<String> nouns) throws IOException {
	//public static void main(String[] args)throws IOException {	

		File codeBaseDir = new File("C:/testWorkSpace");
		
		List<File> files = (List<File>) FileUtils.listFiles(codeBaseDir,
				new String[] { "java" }, true);
		
		List<File> impactedFiles = new ArrayList<File>();
		List<String> impactedFileNames = new ArrayList<String>();
		files.remove(null);
		for (File file : files) {
			
			String fileCP = file.getCanonicalPath().toString();
			String fileName = fileCP.substring(fileCP.lastIndexOf("\\")+1, fileCP.length());

			if (fileName != null) {
				for (String noun : nouns) {
					if (fileName.toLowerCase().contains(noun.toLowerCase())) {
						impactedFiles.add(file);
						impactedFileNames.add(file.getAbsolutePath());
						System.out.println(file.getAbsolutePath());
					}
				}

			}
		}
		
		return impactedFileNames;
	}
	

}
