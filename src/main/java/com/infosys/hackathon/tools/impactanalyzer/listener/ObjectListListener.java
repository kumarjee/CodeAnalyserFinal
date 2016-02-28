package com.infosys.hackathon.tools.impactanalyzer.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

import com.infosys.hackathon.tools.impactanalyzer.util.ImpactAnalysisEmail;

public class ObjectListListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		//new ImpactAnalysisEmail().sendEmail(arg0.getVariable("reqId").toString(),(List<String>) arg0.getVariable("classFiles"));
		
	}

}
