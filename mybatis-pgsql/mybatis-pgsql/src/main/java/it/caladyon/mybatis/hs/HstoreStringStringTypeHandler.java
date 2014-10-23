/**
 * 
 */
package it.caladyon.mybatis.hs;

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
 *
 */
public class HstoreStringStringTypeHandler extends BaseHstoreTypeHandler<String, String> {

	@Override
	@SuppressWarnings("unchecked")
	protected Map<String, String> fromString(String hstring) {
		Map<String, String> rv;
		if (hstring != null && hstring.length() > 0) {
			rv = HStoreConverter.fromString(hstring);
		} else {
			rv = null;
		}
		return rv;
	}
	
}
