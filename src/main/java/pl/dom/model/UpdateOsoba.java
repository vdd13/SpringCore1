package pl.dom.model;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateOsoba extends SqlUpdate{

	public UpdateOsoba(DataSource ds) {
		setDataSource(ds);
		setSql("update osoba set numer =? where id = ?");
		declareParameter(new SqlParameter("numer", Types.NUMERIC));
		declareParameter(new SqlParameter("id", Types.NUMERIC));
		compile();
	}
	
	public int execute(int num, int id) {
		return update(num, id);
	}
}
