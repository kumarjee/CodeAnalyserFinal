package com.infosys.hackathon.tools.impactanalyzer.processor;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import com.infosys.hackathon.tools.impactanalyzer.util.EmailUtil;

public class EmailProcessor implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		
		new EmailUtil().sendEmail();
		
		System.out.println("Sent email successfully!!!");

	}

}
