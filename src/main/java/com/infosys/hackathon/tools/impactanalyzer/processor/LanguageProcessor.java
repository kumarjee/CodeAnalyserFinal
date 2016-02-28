package com.infosys.hackathon.tools.impactanalyzer.processor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class LanguageProcessor implements JavaDelegate{
	
	static Set<String> nounPhrases = null;
	static Set<String> adjectivePhrases = null;
	static Set<String> verbPhrases = null;
	
	@Override
	public void execute(DelegateExecution iAExecution) throws Exception {
		if(iAExecution != null && iAExecution.getVariable("bizReq") != null){
			String strBizReq = iAExecution.getVariable("bizReq").toString();
			new LanguageProcessor().parserAction(strBizReq);
			System.out.println("List of Noun Parse : " + nounPhrases);
			System.out.println("List of Verb Parse : " + verbPhrases);
			
			iAExecution.setVariable("nouns", nounPhrases);
			iAExecution.setVariable("verbs", verbPhrases);
			
		}
		
	}
		
	public void getNounPhrases(Parse p) {
		
		if (p.getType().equals("NN") || p.getType().equals("NNS")
				|| p.getType().equals("NNP") || p.getType().equals("NNPS")) {
			nounPhrases.add(p.getCoveredText());
		}

		if (p.getType().equals("JJ") || p.getType().equals("JJR")
				|| p.getType().equals("JJS")) {
			adjectivePhrases.add(p.getCoveredText());
		}

		if (p.getType().equals("VB") || p.getType().equals("VBP")
				|| p.getType().equals("VBG") || p.getType().equals("VBD")
				|| p.getType().equals("VBN")) {
			verbPhrases.add(p.getCoveredText());
		}

		for (Parse child : p.getChildren()) {
			getNounPhrases(child);
		}
		
	}

	public void parserAction(String text) throws Exception {

		nounPhrases = new HashSet<>();
		adjectivePhrases = new HashSet<>();
		verbPhrases = new HashSet<>();
		InputStream is = null;
		try{
		 is = new FileInputStream("C:/Users/Administrator/workspace/Hackathon/ImpactAnalyzer/en-parser-chunking.bin");
		ParserModel model = new ParserModel(is);
		Parser parser = ParserFactory.create(model);
		Parse topParses[] = ParserTool.parseLine(text, parser, 1);
		for (Parse p : topParses) {
			// p.show();
			getNounPhrases(p);
		}
		} finally {
		    if (is != null) {
		        is.close();
		    }
		}
	}	

}
