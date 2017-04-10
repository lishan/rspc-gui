package platform.cache.referenceCache;


public class ReferenceCacheKeyImpl extends IReferenceCacheKey {

	@Override
	public <T> Object createkey(T t) {
		return "1" ;
	}

}
