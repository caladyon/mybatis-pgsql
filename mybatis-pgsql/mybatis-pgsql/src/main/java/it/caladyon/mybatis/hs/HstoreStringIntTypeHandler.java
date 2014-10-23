/**
 * 
 */
package it.caladyon.mybatis.hs;

/**
 * Manages a String-Integer mapping.
 * 
 * @author Luciano Boschi
 * @since 0.1
 *
 */
public class HstoreStringIntTypeHandler extends RawHstoreTypeHandler<String, Integer> {

	@Override
	protected String toK(String k) {
		return k;
	}

	@Override
	protected Integer toV(String v) throws NumberFormatException {
		return Integer.valueOf(v);
	}

}
