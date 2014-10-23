/**
 * 
 */
package it.caladyon.mybatis.hs;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.HStoreConverter;
import org.postgresql.util.PGobject;

/**
 * Base for other HStore implementations.
 * 
 * @param <K>	Type for the hstore keys.
 * @param <V>	Type for the hstore values.
 * 
 * @author Luciano Boschi
 * @since 0.1
 */
public abstract class BaseHstoreTypeHandler<K, V> extends BaseTypeHandler<Map<K, V>> {

	@Override
	public final void setNonNullParameter(PreparedStatement ps, int i, Map<K, V> parameter, JdbcType jdbcType)
			throws SQLException {
		PGobject hstore = new PGobject();
		hstore.setType("hstore");
		hstore.setValue(HStoreConverter.toString(parameter));
		ps.setObject(i, hstore);
	}

	@Override
	public final Map<K, V> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return fromString(rs.getString(columnName));
	}

	@Override
	public final Map<K, V> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return fromString(rs.getString(columnIndex));
	}

	@Override
	public final Map<K, V> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return fromString(cs.getString(columnIndex));
	}

	protected abstract Map<K, V> fromString(String string);

}
