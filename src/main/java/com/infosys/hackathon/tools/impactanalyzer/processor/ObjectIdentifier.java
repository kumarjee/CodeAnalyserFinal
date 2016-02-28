package com.infosys.hackathon.tools.impactanalyzer.processor;

import java.util.List;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.infosys.hackathon.tools.impactanalyzer.util.CheckoutUtil;
import com.infosys.hackathon.tools.impactanalyzer.util.ClassNameIdentifier;
import com.infosys.hackathon.tools.impactanalyzer.util.ImpactAnalysisEmail;

public class ObjectIdentifier implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Inside execute of Object Identifier");
		System.out.println("code repo url: " + arg0.getVariable("codeRepoRef"));
		if(arg0.getVariable("codeRepoRef") != null){		
			try{
			new CheckoutUtil().getCodefromGIT(arg0.getVariable("codeRepoRef").toString());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(arg0.getVariable("nouns") != null){
			try{
				arg0.setVariable("classFiles", new ClassNameIdentifier().getFileNames((Set<String>)arg0.getVariable("nouns")));
				new ImpactAnalysisEmail().sendEmail(arg0.getVariable("reqId").toString(),(List<String>) arg0.getVariable("classFiles"),"sitaram.karancheti48@gmail.com");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}

}
