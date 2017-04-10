package platform.cache.referenceCache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;


public class ReferenceCache {
	private WeakHashMap<Object,Reference<?>> wkMap ;
	private ReferenceQueue queue ;
	
	private static ReferenceCache cacheManager=null;
	
	private ReferenceCache() {
		wkMap = new WeakHashMap<Object, Reference<?>>();
		queue = new ReferenceQueue<Object>();
	}
	public static ReferenceCache getInstance(){
		if(null==cacheManager){
			cacheManager = new ReferenceCache();
		}
		return cacheManager;
	}
	
	public<T,K extends IReferenceCacheKey> void cache(T t,IReferenceCacheKey key){
		KeyWeakReference<T> wr = new KeyWeakReference<T>(t,(ReferenceQueue<T>)queue,key);
		wkMap.put(wr.get_key(), wr);
	}
	public <T>T getCache(Object key){
		clean();
		KeyWeakReference<?> keyWkRef=(KeyWeakReference<?>) wkMap.get(key);
		if(null!=keyWkRef){
			return (T) keyWkRef.get();
		}
		return null;
	}

	public int size(){
		clean();
		return wkMap.size();
	}
	private void clean(){
		KeyWeakReference<?> r ;
		while(null!=(r=(KeyWeakReference<?>) queue.poll())){
			System.out.println("清除"+r.get_key());
			wkMap.remove(r.get_key());
			r=null;
		}
	}
	
	private class KeyWeakReference<T> extends WeakReference<T>{
		private IReferenceCacheKey iReferenceCacheKey; 
		private Object _key;
		public KeyWeakReference(T referent,ReferenceQueue<T> queue,IReferenceCacheKey key) {
			super(referent,queue);
			try {
				iReferenceCacheKey=key;
			} catch (Exception e) {
				e.printStackTrace();
			}
			_key = iReferenceCacheKey.createkey(referent);
		}
		public Object get_key() {
			return _key;
		}
	}
}
