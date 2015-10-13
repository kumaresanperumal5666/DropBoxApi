package com.npb.dbapi.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.npb.dbapi.dao.DbAuthDao;
import com.npb.dbapi.dao.FileUpload;
import com.npb.dbapi.interfaces.services.IDbAuthService;

@Service
@Transactional
public class DbAuthService implements IDbAuthService{

	@Autowired
	private DbAuthDao authdao;

	@Override
	@Transactional
	public void datastore(MultipartFile file,String userString) throws IOException {
		authdao.datastore(file,userString);
	}

	public FileUpload download() {
	return authdao.download();
		
	}

	
	}
	


