package com.npb.dbapi.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import com.npb.dbapi.dao.FileUpload;
import com.npb.dbapi.services.DbAuthService;

@Controller("AuthenticationController")
@RequestMapping("/login")
public class DbAuthenticationController {
	
	@Autowired
	private DbAuthService service;
	
	public long size_of_the_file;

	@RequestMapping(value = "/continueFileUpload", method = RequestMethod.POST)
	@ResponseBody
	
	public String continueFileUpload(@RequestParam("path") String path,
			@RequestParam("file") MultipartFile file)
			throws IOException, DbxException,FileUploadException, ServletException {
		
			System.err.println("$$ tester $$"+path);
			System.err.println("$$ tester $$"+file.getSize());
		
					service.datastore(file,path);
					//Get your app key and secret from the Dropbox developers
					// website.
					final String APP_KEY = "f4r9qg3m6enhdc6";
					final String APP_SECRET = "yyub7jy6do33265";
					DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
					DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
					DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
					String accessToken = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
					DbxClient client = new DbxClient(config, accessToken);
					InputStream fileContent=file.getInputStream();
					String fileName=file.getOriginalFilename();
					System.out.println("file name is : "+fileName);
					long length_of_the_file=file.getSize();
					System.out.println("Size of the file inside is "+ length_of_the_file);
					//DbxEntry.File uploadedFile = client.uploadFile("/"+"geppetto_test@test.com/"+fileName, DbxWriteMode.add(), length_of_the_file,fileContent);
					  
					DbxEntry.File uploadedFile = client.uploadFile("/"+path+"/"+""+fileName, DbxWriteMode.add(), length_of_the_file,fileContent);
				  
					System.out.println("Uploaded: " + uploadedFile.toString());
					DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
					System.out.println("Files in the root path:");
					for (DbxEntry child : listing.children) {
						System.out.println("	" + child.name + ": "+ child.toString());
			}
				
		
	return null;
}
	
	@RequestMapping(value = "/createFolder/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
    public void createFolder(@RequestParam("folderName") String folderName) throws DbxException {
		final String APP_KEY = "f4r9qg3m6enhdc6";
		final String APP_SECRET = "yyub7jy6do33265";
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
		DbxClient client = new DbxClient(config, accessToken);
		client.createFolder("/" + folderName);
	}

	/*@RequestMapping(value = "/downloadFileFromDropbox/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public byte[] download_file(String fileName,HttpServletResponse response) throws DbxException, IOException {
		final String APP_KEY = "f4r9qg3m6enhdc6";
		final String APP_SECRET = "yyub7jy6do33265";
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
		DbxClient client = new DbxClient(config, accessToken);
		//FileOutputStream outputStream  = new FileOutputStream("F:/"+fileName);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
		DbxEntry.File downloadedFile = client.getFile("/" + fileName,null, outputStream);
		System.out.println("Metadata: " + downloadedFile.toString());
		return outputStream.toByteArray();
	}
	*/
	@RequestMapping(value = "/deleteFileFromDropbox/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public void file_file(String fileName,HttpServletResponse response) throws DbxException, IOException {
		final String APP_KEY = "f4r9qg3m6enhdc6";
		final String APP_SECRET = "yyub7jy6do33265";
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
		DbxClient client = new DbxClient(config, accessToken);
		client.delete("/"+fileName);
	}
	
	/***  TO list out all the files from the dropbox cloud*/
	@RequestMapping(value = "/getListOfFiles/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public ArrayList<String> listDropboxFolders(@RequestParam("folderPath") String folderPath) throws DbxException {
		final String APP_KEY = "f4r9qg3m6enhdc6";
		final String APP_SECRET = "yyub7jy6do33265";
		DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		String accessToken = "xCC9JBh5vCAAAAAAAAAAFkVsFKW-88SUMGNnv3YbGpcbtI7khzp80kJKBMchJX_0";
		DbxClient client = new DbxClient(config, accessToken);
		DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
		System.out.println("Files List:");
		//My code to store list of files
		ArrayList<String> fileList=new ArrayList<String>();
		for (DbxEntry child : listing.children) {
			//File child;
			if(child.isFolder()){
				fileList.add(child.name);
				System.out.println("file is theere"+child.name);
			}else{
				System.out.println("file name **"+child.name);
			}
			//fileList.add(child.); ///
			//System.out.println("file name **"+child.name);
			//System.out.println("	" + child.name + ": " + child.toString());
		}
		return fileList;	
	}
	
	@RequestMapping(value = "/downloadFileFromDropbox/", method = RequestMethod.GET,headers = "Accept=application/json")
	@ResponseBody
	public Blob[] downloadtest_file(String fileName,HttpServletResponse response) throws  IOException {
		FileUpload fileipload =service.download();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
			//DbxEntry.File downloadedFile = client.getFile("/" + fileName,null, outputStream);
			//System.out.println("Metadata: " + downloadedFile.toString());
			fileipload.getFile();
			return fileipload.getFile();
		}
		
	@RequestMapping(value = "/loginSuccess/", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> success() {
		System.out.println("Login success controller called");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", "success");
		return response;
	}

	@RequestMapping(value = "/loginFailure/", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> failure() {
		System.out.println("Login failure controller called");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", "failed");
		return response;
	}

	@RequestMapping(value = "/accessDenied/", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> accessDenied() {
		System.out.println("Access denied controller called");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", "Access Denied");
		return response;
	}

	@RequestMapping(value = "/noUserPrincipalFound/", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> sessionNotFound() {
		System.out.println("No user principal controller called");
		System.out.println("No principal found. Redirect the page to login");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", "No User principal Found. Redirect to login");
		return response;
	}
	

}
