package com.npb.dbapi.interfaces.dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public interface IDbDropBoxDao {

//ArrayList<String> listDropBoxFiles(String folderPath) throws DbxException;

void deleteFile(String fileName, HttpServletResponse response)
		throws DbxException;

byte[] downloadFile(String fileName, HttpServletResponse response)
		throws DbxException, IOException;

void createFolder(String folderName) throws DbxException;

void fileUpload(HttpServletRequest request) throws DbxException, IOException,
		ServletException;

String listDropBoxFiles(String folderPath) throws DbxException, JsonProcessingException;

}
