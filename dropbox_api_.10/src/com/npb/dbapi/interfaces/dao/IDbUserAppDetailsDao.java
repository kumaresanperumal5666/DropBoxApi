package com.npb.dbapi.interfaces.dao;

import com.npb.dbapi.domain.core.DbUserAppDetails;
/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for UserAppDetailsDao
 *         </p>
 *  
 * **/
public interface IDbUserAppDetailsDao {
	public DbUserAppDetails create(DbUserAppDetails userappdetails);

	public DbUserAppDetails search(long id);

	void delete(long id);

	public DbUserAppDetails update(DbUserAppDetails userappdetails);
}
