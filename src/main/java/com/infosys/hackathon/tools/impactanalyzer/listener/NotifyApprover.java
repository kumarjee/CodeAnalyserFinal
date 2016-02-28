package com.infosys.hackathon.tools.impactanalyzer.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.infosys.hackathon.tools.impactanalyzer.util.ImpactAnalysisEmail;

public class NotifyApprover implements TaskListener {

	@Override
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		
		new ImpactAnalysisEmail().sendEmail(arg0.getVariable("reqId").toString(),(List<String>) arg0.getVariable("classFiles"),"kumarji.alluri@gmail.com");
		
	}

}
