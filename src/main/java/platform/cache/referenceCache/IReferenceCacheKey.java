package platform.cache.referenceCache;

public abstract class IReferenceCacheKey {

	public abstract <T> Object createkey(T t);
}
