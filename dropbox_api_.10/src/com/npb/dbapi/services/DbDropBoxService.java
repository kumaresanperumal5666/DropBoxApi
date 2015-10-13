package com.npb.dbapi.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.npb.dbapi.dao.DbDropBoxDao;
import com.npb.dbapi.interfaces.services.IDbDropBoxService;
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
@Service
@Transactional
public class DbDropBoxService implements IDbDropBoxService{

	@Autowired
	private DbDropBoxDao dropboxdao;

	@Transactional
	@Override
	public void fileUpload(HttpServletRequest request) throws DbxException, IOException, ServletException {
		this.dropboxdao.fileUpload(request);
	}
	
	@Transactional
	@Override
	public void createFolder(String folderName) throws DbxException {
		this.dropboxdao.createFolder(folderName);
	}
	
	@Transactional
	@Override
	public byte[] downloadFile(String fileName, HttpServletResponse response) throws DbxException, IOException {
		return this.dropboxdao.downloadFile(fileName,response);
	}
	@Transactional
	@Override
	public void deleteFile(String fileName, HttpServletResponse response) throws DbxException {
		this.dropboxdao.deleteFile(fileName,response);
		
	}

	@Transactional
	@Override
	public String listDropBoxFiles(String folderPath) throws DbxException, JsonProcessingException {
		return this.dropboxdao.listDropBoxFiles(folderPath);
	}
}
