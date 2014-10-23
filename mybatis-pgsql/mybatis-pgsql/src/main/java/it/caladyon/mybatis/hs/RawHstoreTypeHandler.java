/**
 * 
 */
package it.caladyon.mybatis.hs;

import java.util.HashMap;
import java.util.Map;

import org.postgresql.util.HStoreConverter;

/**
 * <p>
 * Mybatis Type Handler per Postgres HStore.
 * <p>
 * Requires:
 * <ul>
 * <li>Postgres driver 9.3 or later ({@link HStoreConverter} class 
 * is used by {@link BaseHstoreTypeHandler}).
 * </ul>
 * 
 * @author Luciano Boschi
 * @since 0.1
 *
 */
public abstract class RawHstoreTypeHandler<K, V> extends BaseHstoreTypeHandler<K, V> {

	@Override
	protected final Map<K, V> fromString(String hs) {
		Map<K, V> rv = new HashMap<K, V>();
		if (hs != null && hs.length() > 0) {
			@SuppressWarnings("unchecked")
			Map<String, String> rawMap = HStoreConverter.fromString(hs);
			for (Map.Entry<String, String> entry : rawMap.entrySet()) {
				rv.put(toK(entry.getKey()), toV(entry.getValue()));
			}
		}
		return rv;
	}
	
	/**
	 * Transform a String key (generated by {@link HStoreConverter#fromString(String)})
	 * in a K-type key.
	 * 
	 * @param k
	 * @return
	 */
	protected abstract K toK(String k);

	/**
	 * Transform a String value (generated by {@link HStoreConverter#fromString(String)})
	 * in a V-type value.
	 * 
	 * @param v
	 * @return
	 */
	protected abstract V toV(String v);

}