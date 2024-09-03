package pl.dom.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository // hibernate configuracja sesionFactory psuje inicjalizacje beana
@DependsOn("dataSource")
//@Transactional
public class OsobaService {

	@Autowired
	OsobaRepo osobarepo; // blokowane przez dodanie konfiguracji hibernate w xml 
	
	private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate namedJdbc;
	private SimpleJdbcInsert simpleJdbcInsert;
	
//	public OsobaService(DataSource dataSource) {   //ustawiamy DS poprzez constructor albo setDataSource
//		this.jdbc = new JdbcTemplate(dataSource);
//	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
//		  this.jdbc = new JdbcTemplate(dataSource);
//		  this.namedJdbc = new NamedParameterJdbcTemplate(dataSource);
		
		DriverManagerDataSource ds =  new DriverManagerDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUrl("jdbc:postgresql://localhost:5432/spring");
		ds.setUsername("postgres");
		ds.setPassword("a");
		this.jdbc = new JdbcTemplate(ds);
		this.namedJdbc = new NamedParameterJdbcTemplate(ds);
		this.simpleJdbcInsert = new SimpleJdbcInsert(ds).withTableName("osoba"); 
	}

	
	
	public void runQueries() {
		countRows();
		countRowsByName();
		nazwiskoOfId();
		pobierzOsobe();
		pobierzOsoby();
		updateInsertOsoby();
		updateDanychOsoby();
		
		pobierzOsoby();
		updateDeleteOsobe();
		pobierzOsoby();
		
//		executeTest();
		
		namedCount();
		namedCountByObject();
		System.out.println(" queryAsList * " + queryList());
		testBatchUpdate();
		System.out.println(batchUpdateNamedByObjects());
		pobierzOsoby();
		
		batchUpdatePlaceholdersByobject();
		pobierzOsoby();
		
		addSimpleJdbc();  // numer  id nadawany recznie powoduje blad przy powtornym zapisie
		pobierzOsoby();
		updateDeleteOsobe2();
		
		addSimpleJdbcByObject();
		pobierzOsoby();
		updateDeleteOsobe2();
		
		addSimpleJdbcByMap();
		pobierzOsoby();
		updateDeleteOsobe2();
		
		mappingSqlQuery(jdbc.getDataSource());
		sqlUpdateExample(jdbc.getDataSource());
		
//		addSimpleJdbcGenereatedKey();
	}
	
	public void sqlUpdateExample(DataSource ds) {
		UpdateOsoba updateOso = new UpdateOsoba(ds);
		int i = updateOso.execute(77777, 13); //zmieni wartosci numer na 77777 dla id =13
		System.out.println("Liczba zmodyfikowanych wierzy = " + i);
	}
	
	public void mappingSqlQuery(DataSource ds) {
		OsobaMappingQuery osobaMappingQuery = new OsobaMappingQuery(ds);
		Osoba os = osobaMappingQuery.findObject(2);
		
		System.out.println("Osoba z mappingSqlQuery " + os);
	}
	
//	public void addSimpleJdbcGenereatedKey() {  //wymaga kolumny w DB z id  GenereatedID czy cos takiego
//		Osoba os = new Osoba();
////		os.setId(13);
//		os.setImie("hohoho");
//		os.setNazwisko("hihihih");
//		os.setNumer(321312);
//		
//		Map<String, Object> params = new HashMap<String, Object>(3);
//		params.put("imie", os.getImie());
//		params.put("nazwisko", os.getNazwisko());
//		params.put("numer", os.getNumer());
//		Number id = simpleJdbc.executeAndReturnKey(params);
//		System.out.println("Key from DB " + id);
//	}
	
	public void addSimpleJdbcByMap() {
		Osoba os = new Osoba();
		os.setId(143);
		os.setImie("hohoho");
		os.setNazwisko("hihihih");
		os.setNumer(321312);
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", os.getId())
				.addValue("imie", os.getImie())
				.addValue("nazwisko", os.getNazwisko())
				.addValue("numer", os.getNumer());
		simpleJdbcInsert.execute(params);
	}
	
	
	public void addSimpleJdbcByObject() {
		Osoba os = new Osoba();
		os.setId(143);
		os.setImie("hohoho");
		os.setNazwisko("hihihih");
		os.setNumer(321312);
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(os);
		simpleJdbcInsert.execute(params);
	}
	
	public void addSimpleJdbc() {
		Osoba os = new Osoba();
		os.setId(143);
		os.setImie("hohoho");
		os.setNazwisko("hihihih");
		os.setNumer(321312);
		
		Map<String, Object> params = new HashMap<String, Object>(3);
		params.put("id", os.getId());
//		params.put("imie", os.getImie());
		params.put("nazwisko", os.getNazwisko());
		params.put("numer", os.getNumer());
		simpleJdbcInsert.execute(params);
	}
	
	
	public int[] batchUpdatePlaceholdersByobject() {
		Osoba o1 = new Osoba();
		o1.setId(32);
		o1.setImie("Adrian2222");
		o1.setNazwisko("Walcer1");
		o1.setNumer(321312);
		List<Object[]> batch = new ArrayList<Object[]>();
		for(Osoba osoba : List.of(o1)) {
			Object[] values  = new Object[] {
					osoba.getImie(), osoba.getId()
			};
			batch.add(values);
		}
		
		return jdbc.batchUpdate(
				"update osoba set imie = ? where id = ?",
				batch
				);
				
	}
	
	
	public int[] batchUpdateNamedByObjects() {
			Osoba o1 = new Osoba();
			o1.setId(32);
			o1.setImie("Adrian1111");
			o1.setNazwisko("Walcer1");
			o1.setNumer(321312);
		
		return namedJdbc.batchUpdate(
				"update osoba set imie = :imie where id = :id",
				SqlParameterSourceUtils.createBatch(List.of(o1)));
	}
	
	public void testBatchUpdate() {
		Osoba o1 = new Osoba();
		o1.setId(33);
		o1.setImie("Adrian1");
		o1.setNazwisko("Walcer1");
		o1.setNumer(321312);
		
		Osoba o2 = new Osoba();
		o2.setId(34);
		o2.setImie("Adrian2");
		o2.setNazwisko("Walcer2");
		o2.setNumer(321312);
		
		System.out.println(" lista batchUpdate = " + batchUpdate(List.of(o1,o2)));
	}
	
	
	public int[] batchUpdate(List<Osoba> osoby) {
		return jdbc.batchUpdate(
				"update osoba set imie = ? where imie is null",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						Osoba nowy = osoby.get(i);
						ps.setString(1, nowy.getImie());
					}
					
					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return osoby.size();
					}
				}
				);
	}
	
	
	
//	public void generatedKey() { //error?!
//		String sql = "insert into osoba (imie) values (?)";
//		String imie = "john";
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		jdbc.update(connection -> {
//			PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
//			ps.setString(1, imie);
//		}, keyHolder);
//	}
	
	
	public List<Map<String, Object>> queryList() {
		return jdbc.queryForList("select * from osoba");
	}
	
	public void namedCountByObject() {
		Osoba nowa = new Osoba();
		nowa.setImie("adam");
		nowa.setNazwisko("jakos");
		String sql = "select count(*) from osoba where imie = :imie"; // wyszuka po property imie z beana
		SqlParameterSource params = new BeanPropertySqlParameterSource(nowa);
		
		int i = namedJdbc.queryForObject(sql, params, Integer.class);
		System.out.println("Liczba osob wyszukanych poprzez zadany bean-obiekt adam " + i);
	}
	
	public void namedCount() {
		String sql = "select count(*) from osoba where imie = :imie";
//		SqlParameterSource namedParams = new MapSqlParameterSource("imie", "adam");
		Map<String, String> namedParams = Collections.singletonMap("imie", "Genowef"); 
		int i = namedJdbc.queryForObject(sql, namedParams, Integer.class);
		System.out.println("Liczba adamow w bazie = " + i);
	}
	
	public void executeTest() { //robi wszystko w DB - tworzy SQL, DDL, wywoluje procedury, etc ale bez argumwntow ?
		jdbc.execute("create table mytable (id integer, name varchar(100))");
	}
	
	public void updateDeleteOsobe() {
		jdbc.update("delete from osoba where id = ?", 111);
	}
	
	public void updateDeleteOsobe2() {
		jdbc.update("delete from osoba where id = ?", 143);
	}
	
	public void updateDanychOsoby() {
		jdbc.update("update osoba set imie = ? where id = ?", "Genowef", 1 );
	}
	
	public void updateInsertOsoby() {
		jdbc.update("insert into osoba (id, imie, nazwisko, numer) values(?,?,?,?)", 111, "anna", "nwoak", 77777 );
	}
	
	public void pobierzOsoby() {
		List<Osoba> osobyLista = jdbc.query("select id, imie, nazwisko, numer from osoba order by id asc", 
				(resultSet, rowNum) -> {
					Osoba nowa = new Osoba();
					nowa.setId(resultSet.getInt("id"));
					nowa.setImie(resultSet.getString("imie"));
					nowa.setNazwisko(resultSet.getString("nazwisko"));
					nowa.setNumer(resultSet.getInt("numer"));
					return nowa;
				}
					);
		System.out.println(osobyLista);
	}
	
	public void pobierzOsobe() {
		Osoba oso =  (Osoba) jdbc.queryForObject("select id, imie, nazwisko, numer from osoba where id = ? ",
				(resultSet, rowNum) -> {
					Osoba nowa = new Osoba();
					nowa.setId(resultSet.getInt("id"));
					nowa.setImie(resultSet.getString("imie"));
					nowa.setNazwisko(resultSet.getString("nazwisko"));
					nowa.setNumer(resultSet.getInt("numer"));
					return nowa;
				}
				, 57);
		System.out.println("Osoba " + oso);
	}
	
	public void nazwiskoOfId() {
		String nazwisko = jdbc.queryForObject("select nazwisko from osoba where id = ?", String.class, 57);
		System.out.println("Nazwisko osoby o id = 57 - " + nazwisko );
	}
	
	public void countRows() {
		int i = jdbc.queryForObject("select count(*) from osoba", Integer.class);
		System.out.println("Liczba wierszy = " + i );
	}
	
	public void countRowsByName() {
		int i = jdbc.queryForObject("select count(*) from osoba where imie = ? ", Integer.class, "adam");
		System.out.println("Liczba wierszy z imieniem adam " + i);
	}
	
	public OsobaRepo getOsobarepo() {
		return osobarepo;
	}

	public void setOsobarepo(OsobaRepo osobarepo) {
		this.osobarepo = osobarepo;
	}
	
	@Transactional(readOnly = false)
	public void createOsob(Osoba osoba) throws UnsupportedOperationException{
//		 throw new UnsupportedOperationException();
		osobarepo.save(osoba);
	}
	
	
}
