package com.npb.dbapi.interfaces.services;

import com.npb.dbapi.domain.core.DbUserAppDetails;
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
public interface IDbUserAppDetailsService {
	public DbUserAppDetails create(DbUserAppDetails userappdetails);

	public DbUserAppDetails search(long id);

	public void delete(long id);

	public DbUserAppDetails update(DbUserAppDetails userappdetails);
}
