package com.npb.dbapi.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npb.dbapi.dao.DbUserAppDetailsDao;
import com.npb.dbapi.domain.core.DbUserAppDetails;
import com.npb.dbapi.interfaces.services.IDbUserAppDetailsService;
/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for UserAppDetailsService
 *         </p>
 *  
 * **/
@Service
@Transactional
public class DbUserAppDetailsService implements IDbUserAppDetailsService {
	
	@Autowired
	private DbUserAppDetailsDao userappdetailsdao;
	
	@Transactional
	@Override
	public DbUserAppDetails create(DbUserAppDetails userappdetails){
	    userappdetails=this.userappdetailsdao.create(userappdetails);	
		return userappdetails;
	}
	@Transactional
	@Override
	public DbUserAppDetails search(long id){
	    DbUserAppDetails userappdetails=this.userappdetailsdao.search(id);	
		return userappdetails;
	}
	@Transactional
	@Override
	public void delete(long id) {
		this.userappdetailsdao.delete(id);
	}
	@Transactional
	@Override
	public DbUserAppDetails update(DbUserAppDetails userappdetails) {
		return this.userappdetailsdao.update(userappdetails);
	
	}
	
	
}
