package com.shiva.ehcache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class ListCacheManager {
	
	private static ListCacheManager uniqInstance;
	private static Cache newCache;
	private static CacheManager manager;
	
	private ListCacheManager() {
		URL url = this.getClass().getResource("/conf/ehcache.xml");
		System.out.println("URL" + url);
		manager =  new CacheManager(url);
		ListCacheManager.newCache = manager.getCache("newCache");
	}
	
	public static synchronized Cache getCache(){
		  if (uniqInstance == null) {
		      uniqInstance = new ListCacheManager();
		    }
		return ListCacheManager.newCache;
	}
	
	public static void setCacheManager(){
		manager.shutdown();
	}

}
