package com.npb.dbapi.interfaces.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IDbAuthDao {

	void datastore(MultipartFile file, String userString) throws IOException;


}
