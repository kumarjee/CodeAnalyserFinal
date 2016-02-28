package com.infosys.hackathon.tools.impactanalyzer.util;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ImpactAnalysisEmail {

	public void sendEmail(String reqId, List<String> fileNames, String toEmailId) {
		
		final String username = "kumarji.alluri@gmail.com";
		final String password = "Sysinfo!23";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress("kumarji.alluri@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmailId));
			message.setSubject("Impact analysis on Requirement "+reqId);
			StringBuffer htmlTableHeader= null;
			// 0-20 simple, 20-50 medium >50 complex
			HashMap<String, Integer> functionPnts = new HashMap<String, Integer>();
			functionPnts.put("simple", 10);
			functionPnts.put("medium", 15);
			functionPnts.put("complex", 20);
			int estimatedFunctionPoints=0;
			if(fileNames!=null && fileNames.size()>0){
				htmlTableHeader=new StringBuffer("<table style='border-collapse:collapse;border-spacing:0;border-color:#aabcfe'><tr><th style='font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe'>Class Name</th><th style='font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;vertical-align:top'>Absolute Path</th><th style='font-family:Arial, sans-serif;font-size:14px;font-weight:bold;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;vertical-align:top'>Complexity</th></tr>");
			for(String fileName : fileNames){
				String onlyName = fileName.substring(fileName.lastIndexOf("\\")+1, fileName.length());
				System.out.println(onlyName);
				StringBuffer cell1 = new StringBuffer("<td class='tg-yw4l'>").append(onlyName).append("</td>");
				StringBuffer cell2 = new StringBuffer("<td class='tg-yw4l'>").append("&nbsp;&nbsp;&nbsp;").append(fileName).append("&nbsp;&nbsp;&nbsp;").append("</td>");
				int randomNum = 1 + (int)(Math.random() * 30); 
				String complexity = randomNum<=10?"simple":randomNum>=10&&randomNum<15?"medium":"complex";
				int calculatedComplexity= functionPnts.get(complexity);
				estimatedFunctionPoints=estimatedFunctionPoints+calculatedComplexity;
				StringBuffer cell3 = new StringBuffer("<td class='tg-yw4l'>").append(calculatedComplexity).append("</td>");
				StringBuffer row = new StringBuffer("<tr>").append(cell1).append(cell2).append(cell3).append("</tr>");
				htmlTableHeader.append(row);
				
			}
			htmlTableHeader.append("</table><br><br><br>");
			htmlTableHeader.append("<h3>").append("Total number of estimated funtion points: ").append(estimatedFunctionPoints).append("</h3>");
			
			}else{
				htmlTableHeader=new StringBuffer("<H2>No files found for the specs submitted.</H2>");
			}
			System.out.println(new String(htmlTableHeader));
			message.setContent(new String(htmlTableHeader), "text/html");
			//message.setText("Hi, this is kj, test email");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
