package com.infosys.hackathon.tools.impactanalyzer.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

/**
 * Hello world!
 *
 */
public class CheckoutUtil 
{
    public void getCodefromGIT(String remotePath)
    {
    	//String remotePath = "https://github.com/kumarjee/ImpactAnalyzerTest";
    	
		   String localPath = "C:\\testWorkSpace";
		   File repoDir = new File(localPath);
		   if(repoDir.exists()){
			   try {
				FileUtils.forceDelete(repoDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   repoDir = new File(localPath);
		   repoDir.mkdir();
		   try {
			Git.cloneRepository()
			    .setURI(remotePath)
			    .setDirectory(repoDir)
			    .call();
			
			
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("remotePath: " + remotePath);
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("remotePath: " + remotePath);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("remotePath: " + remotePath);
		}
    }
}
