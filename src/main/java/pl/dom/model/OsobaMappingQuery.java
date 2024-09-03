package pl.dom.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class OsobaMappingQuery  extends MappingSqlQuery<Osoba> {

	public OsobaMappingQuery(DataSource ds) {
		super(ds, "select id, imie, nazwisko, numer from osoba where id = ?");
		declareParameter(new SqlParameter("id", Types.INTEGER));
		compile();
	}
	
	@Override
	protected Osoba mapRow(ResultSet rs, int rowNum) throws SQLException {
		Osoba osoba = new Osoba();
		osoba.setId(rs.getInt("id"));
		osoba.setImie(rs.getString("imie"));
		osoba.setNazwisko(rs.getString("nazwisko"));
		osoba.setNumer(rs.getInt("numer"));
		
		return osoba;
	}

	
	
}
