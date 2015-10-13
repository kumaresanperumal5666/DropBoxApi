package com.npb.dbapi.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.npb.dbapi.domain.core.DbMyFolder;
import com.npb.dbapi.domain.core.DbUserAppDetails;
import com.npb.dbapi.interfaces.dao.IDbDropBoxDao;

/***
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for DropBox
 *         </p>
 * **/

@Repository
@Transactional
public class DbDropBoxDao implements IDbDropBoxDao {

	@Autowired
	private DbUserAppDetailsDao userdetailsdao;

	public long size_of_the_file;

	@Transactional
	@Override
	public void fileUpload(HttpServletRequest request) throws DbxException,
			IOException, ServletException {

		/** He should pass userid with it. then i can get the userappdetails */
		DbUserAppDetails userdetails = userdetailsdao.search(1);
		String app_key = userdetails.getApp_key();
		String app_secret_key = userdetails.getApp_secret_key();
		String access_token = userdetails.getAccess_token();

		System.out.println("We entered in the sea");
		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
				} else {
					System.out.println("!!! file information is ");
					String fieldName = item.getFieldName();
					String fileName = FilenameUtils.getName(item.getName());
					long length_of_the_file = item.getSize();
					InputStream fileContent = item.getInputStream();

					// Get your app key and secret from the Dropbox developers
					// final String APP_KEY = "f4r9qg3m6enhdc6";
					// final String APP_SECRET = "yyub7jy6do33265";
					final String APP_KEY = app_key;
					final String APP_SECRET = app_secret_key;
					DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
					DbxRequestConfig config = new DbxRequestConfig(
							"JavaTutorial/1.0", Locale.getDefault().toString());
					DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(
							config, appInfo);
					// String accessToken =
					// "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
					String accessToken = access_token;
					DbxClient client = new DbxClient(config, accessToken);

					System.out.println("Size of the file inside is "
							+ size_of_the_file);
					DbxEntry.File uploadedFile = client.uploadFile("/"
							+ fileName, DbxWriteMode.add(), length_of_the_file,
							fileContent);
					System.out.println("Uploaded: " + uploadedFile.toString());

					DbxEntry.WithChildren listing = client
							.getMetadataWithChildren("/");
					System.out.println("Files in the root path:");
					for (DbxEntry child : listing.children) {
						System.out.println("	" + child.name + ": "
								+ child.toString());
					}
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}
	}

	@Transactional
	@Override
	public void createFolder(String folderName) throws DbxException {
		/** He should pass userid with it. then i can get the userappdetails */
		DbUserAppDetails userdetails = userdetailsdao.search(1);
		String app_key = userdetails.getApp_key();
		String app_secret_key = userdetails.getApp_secret_key();
		String access_token = userdetails.getAccess_token();
		/****/
		final String APP_KEY = app_key;
		final String APP_SECRET = app_secret_key;
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = access_token;
		DbxClient client = new DbxClient(config, accessToken);
		client.createFolder("/" + folderName);
	}

	public static void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
				} else {
					System.out.println("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public byte[] downloadFile(String fileName, HttpServletResponse response)
			throws DbxException, IOException {
		/** He should pass userid with it. then i can get the userappdetails */
		DbUserAppDetails userdetails = userdetailsdao.search(1);
		String app_key = userdetails.getApp_key();
		String app_secret_key = userdetails.getApp_secret_key();
		String access_token = userdetails.getAccess_token();

		final String APP_KEY = app_key;
		final String APP_SECRET = app_secret_key;
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = access_token;
		DbxClient client = new DbxClient(config, accessToken);
		// FileOutputStream outputStream = new FileOutputStream("F:/"+fileName);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		DbxEntry.File downloadedFile = client.getFile("/" + fileName, null,
				outputStream);
		System.out.println("Metadata: " + downloadedFile.toString());
		return outputStream.toByteArray();
	}

	@Transactional
	@Override
	public void deleteFile(String fileName, HttpServletResponse response)
			throws DbxException {
		/** He should pass userid with it. then i can get the userappdetails */
		DbUserAppDetails userdetails = userdetailsdao.search(1);
		String app_key = userdetails.getApp_key();
		String app_secret_key = userdetails.getApp_secret_key();
		String access_token = userdetails.getAccess_token();

		final String APP_KEY = app_key;
		final String APP_SECRET = app_secret_key;
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = access_token;
		DbxClient client = new DbxClient(config, accessToken);
		client.delete("/" + fileName);
	}

	@Transactional
	@Override
	public String listDropBoxFiles(String folderPath) throws DbxException, JsonProcessingException {
		/** He should pass userid with it. then i can get the userappdetails */
		DbUserAppDetails userdetails = userdetailsdao.search(1);
		String app_key = userdetails.getApp_key();
		String app_secret_key = userdetails.getApp_secret_key();
		String access_token = userdetails.getAccess_token();
		
//		String app_key = "f4r9qg3m6enhdc6";
//		String app_secret_key = "yyub7jy6do33265";
//		String access_token = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
		 
		
		final String APP_KEY = app_key;
		final String APP_SECRET = app_secret_key;
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
				Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = access_token;
		DbxClient client = new DbxClient(config, accessToken);
		DbxEntry.WithChildren listing = client.getMetadataWithChildren(folderPath);

		List<DbMyFolder> list = new ArrayList<DbMyFolder>();
		
		for (DbxEntry child : listing.children) {
			if (child.isFolder()) {
				DbMyFolder object = new DbMyFolder();
				object.setTitle(child.name);
				list.add(object);
				System.out.println("directory:" + child.name);
			} else {
				DbMyFolder object = new DbMyFolder();
				object.setTitle(child.name);
				object.setFile(true);
				list.add(object);
				System.out.println("file:" + child.name);
			}
		}
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(list);
		System.err.println("$$ Converted JSON Tree $$"+json);
		return json;
	}
}
