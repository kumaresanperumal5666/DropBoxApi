package com.npb.dbapi.interfaces.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IDbAuthService {
	void datastore(MultipartFile file, String userString) throws IOException;

}
