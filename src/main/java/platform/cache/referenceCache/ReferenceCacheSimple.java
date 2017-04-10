package platform.cache.referenceCache;

import java.util.WeakHashMap;


public class ReferenceCacheSimple {
	private WeakHashMap<String,Object> wkMap ;
	
	private static ReferenceCacheSimple cacheManager=null;
	private ReferenceCacheSimple() {
		wkMap = new WeakHashMap<String,Object> ();
	}
	public static ReferenceCacheSimple getInstance(){
		if(null==cacheManager){
			cacheManager = new ReferenceCacheSimple();
		}
		return cacheManager;
	}
	
	public<T> void cache(String key ,T t){
		wkMap.put(key, t);
	}
	public <T>T getCache(String key){
		return (T) wkMap.get(key);
	}
	public int size(){
		return wkMap.size();
	}
}
