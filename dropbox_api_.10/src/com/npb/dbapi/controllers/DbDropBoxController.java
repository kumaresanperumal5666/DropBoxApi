package com.npb.dbapi.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.npb.dbapi.services.DbDropBoxService;

/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for DropBox
 *         </p>
 *  
 * **/
@Controller
@RequestMapping("/dropbox/")
public class DbDropBoxController {
	@Autowired
	private DbDropBoxService dropboxservice;
	
	@RequestMapping(value = "/continueFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String continueFileUpload(HttpServletRequest request,HttpServletResponse response) throws IOException, DbxException,
			FileUploadException, ServletException {
		String str=request.getParameter("t");
		System.err.println("tegdtyuttggfdgfdsgfhjdsf"+str);
		
	this.dropboxservice.fileUpload(request);			
	return null;
	}
	
	@RequestMapping(value = "/createFolder/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
    public void createFolder(@RequestParam("folderName") String folderName) throws DbxException {
	this.dropboxservice.createFolder(folderName);	
	}

	@RequestMapping(value = "/downloadFileFromDropbox/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public byte[] downloadFile(String fileName,HttpServletResponse response) throws DbxException, IOException {
		return this.dropboxservice.downloadFile(fileName,response);
	}
	
	@RequestMapping(value = "/deleteFileFromDropbox/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public void deleteFile(String fileName,HttpServletResponse response) throws DbxException, IOException {
		this.dropboxservice.deleteFile(fileName,response);
		
	}
	
	/***  TO list out all the files from the dropbox cloud
	 * @throws JsonProcessingException */
	@RequestMapping(value = "/getListOfFiles/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public String listDropboxFiles(@RequestParam("folderPath") String folderPath) throws DbxException, JsonProcessingException {
		System.err.println("we entered in ");
		return this.dropboxservice.listDropBoxFiles(folderPath);
		

	}

}
