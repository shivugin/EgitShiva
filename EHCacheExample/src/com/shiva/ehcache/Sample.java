package com.shiva.ehcache;

import java.util.Map;
import java.util.Random;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class Sample {
	
	 private String  listname;
	 
	 private Map<String, String> list;
	 
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	//Create a CacheManager instance using defaults.
        // CacheManager manager = new CacheManager();
        
        //Create a Cache and add it to the CacheManager, then use it.
        /*
         * @param name                the name of the cache. Note that "default" is a reserved name for the defaultCache.
         * @param maxElementsInMemory the maximum number of elements in memory, before they are evicted
         * @param overflowToDisk      whether to use the disk store
         * @param eternal             whether the elements in the cache are eternal, i.e. never expire
         * @param timeToLiveSeconds   the default amount of time to live for an element from its creation date
         * @param timeToIdleSeconds   the default amount of time to live for an element from its last accessed or modified date
         */
        // Cache memoryOnlyCache = new Cache("CacheName", 5000, true, false, 7, 2);
        // manager.addCache(memoryOnlyCache);
        // Cache testCache = manager.getCache("CacheName");
        
        Cache testCache = ListCacheManager.getCache();
        if (testCache == null) {
        	System.out.println("testCache is NUll");
        	System.exit(0);
        	
        }
 
        for (int i = 0; i < 10; i++) {
            //Queries are coming
            Random random = new Random();
            String key = "Key" + i ; //String.valueOf((int) (random.nextFloat() * 10f));
            String value = "Value" + String.valueOf(random.nextFloat() * 10f);
            System.out.println(i + ". Quering " + key);
            if (testCache.get(key) == null) {
                System.out.print("Cache is not hit! ");
                testCache.put(new Element(key, value));
                System.out.print(key + " " + value + " has been put into cache.");
            } else {
                System.out.print("Cache is hit! ");
                Element cachedResult = testCache.get(key);
                System.out.print("The value is " + cachedResult.getObjectValue());
            }
            System.out.println();
            System.out.println();
        }
        System.out.println(testCache.getStatistics().getCacheMisses() + "/10 missing");
        ListCacheManager.setCacheManager();
    }

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public Map<String, String> getList() {
		
		Cache testCache = ListCacheManager.getCache();
        if (testCache == null) {
        	System.out.println("testCache is NUll");
        	System.exit(0);
        }
		
    
		return list;
	}

	public void setList(Map<String, String> list) {
		this.list = list;
	}
	
}
