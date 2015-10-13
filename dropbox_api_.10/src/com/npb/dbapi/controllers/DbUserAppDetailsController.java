
package com.npb.dbapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npb.dbapi.domain.core.DbUserAppDetails;
import com.npb.dbapi.services.DbUserAppDetailsService;
/**
 * @author Kumaresan Perumal
 *         <p>
 *         Created Date: 23-August-2015
 * 
 *         Purpose of this class is used to handle the CRUD operation methods
 *         for UserAppDetails
 *         </p>
 *  
 * **/
@Controller
@RequestMapping("/userapp/")
public class DbUserAppDetailsController{
	
	@Autowired
	private DbUserAppDetailsService userappdetailsservice;
	
	@RequestMapping(method = RequestMethod.POST, value = "/create/", headers = "Accept=application/json")
	@ResponseBody
	public DbUserAppDetails create(@RequestBody DbUserAppDetails userappdetails){
		System.out.println("userdetails data: "+userappdetails.toString());
		System.out.println("username"+userappdetails.getUsername());
		System.out.println("app key"+userappdetails.getApp_key());
		System.out.println("secret key"+userappdetails.getApp_secret_key());
		System.out.println("access token"+userappdetails.getAccess_token());

		userappdetails=this.userappdetailsservice.create(userappdetails);
		return userappdetails;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search/", headers = "Accept=application/json")
	@ResponseBody
	public DbUserAppDetails search(@RequestParam("id") long id){
		DbUserAppDetails userappdetails=this.userappdetailsservice.search(id);
		return userappdetails;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete/", headers = "Accept=application/json")
	@ResponseBody
	public void delete(@RequestParam("id") long id){
		this.userappdetailsservice.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update/", headers = "Accept=application/json")
	@ResponseBody
	public DbUserAppDetails update(@RequestBody DbUserAppDetails userappdetails){
		System.out.println("userdetails data: "+userappdetails);
		System.out.println("username"+userappdetails.getUsername());
		System.out.println("app key"+userappdetails.getApp_key());
		System.out.println("secret key"+userappdetails.getApp_secret_key());
		System.out.println("access token"+userappdetails.getAccess_token());

		userappdetails=this.userappdetailsservice.update(userappdetails);
		return userappdetails;
	}
	
}