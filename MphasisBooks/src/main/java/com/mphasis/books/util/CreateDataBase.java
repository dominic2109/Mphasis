package com.mphasis.books.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mphasis.books.entity.BookEntity;
import com.mphasis.books.repository.BookRepository;
/*
 * Class to create DB after application startup
 */
public class CreateDataBase {
	
	private String db_url;
	private String username;
	private String password;
	private String dbName;
	private String tableName;
	
	private static Logger logger = LoggerFactory.getLogger(CreateDataBase.class);
	
	
	
	public CreateDataBase(String db_url, String username, String password, String dbName, String tableName) {
		this.db_url = db_url;
		this.username = username;
		this.password = password;
		this.dbName = dbName;
		this.tableName = tableName;
	}

	/*
	 * Creation of Database with name 'mphasis'.
	 * Logic will first check if the DB with name 'mphasis' is present or not,
	 * if present, it will not create the db, otherwise it will create the DB.
	 * Note : if you change db.Name property application.properties, please do change it in datasource
	 *        url also in application.properties.
	 */
	public int createDatabase() {
		try(Connection conn = DriverManager.getConnection(db_url,username,password);
				Statement stmt = conn.createStatement();
				) {
			ResultSet rs = conn.getMetaData().getCatalogs();
			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals(dbName.toLowerCase())) {
					logger.info("Database: "+dbName+", already exists");
					conn.close();
					return 0;
				}
			}
			String sql = "CREATE DATABASE "+dbName;
			stmt.executeUpdate(sql);
			logger.info("Database "+dbName+" created successfully...");
			conn.close();
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			}
		}
	
	/*
	 * Creation of Table with name 'books'.
	 * Warning : Logic will drop any table with name 'books' if it already exists
	 * Note : if you change table.Name property application.properties, please do change it in
	 * BookEntity.java also in com.mphasis.books.entity.
	 */
	public int createTable() {
		try(Connection conn = DriverManager.getConnection(db_url+dbName,username,password);
				Statement stmt = conn.createStatement();
				) {
			stmt.execute("DROP TABLE IF EXISTS `"+tableName.toLowerCase()+"`;");
			String sql ="CREATE TABLE "+tableName.toLowerCase()+" (\r\n"
					+ "  bookID int NOT NULL,\r\n"
					+ "  title longtext,\r\n"
					+ "  authors text,\r\n"
					+ "  average_rating text,\r\n"
					+ "  isbn text,\r\n"
					+ "  language_code text,\r\n"
					+ "  ratings_count text,\r\n"
					+ "  price int DEFAULT NULL,\r\n"
					+ "  PRIMARY KEY (bookID)\r\n"
					+ ")";
			stmt.executeUpdate(sql);
			logger.info("Table "+tableName.toLowerCase()+" created successfully...");
			conn.close();
			
			return 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
			}
	}
	
	/*
	 * Pushing of data from book api to the db created, it used RestTemplate to consume the
	 * API data and BookRepository to save and flush the data to database.
	 */
	public void pushDataToTableViaJson(String url, BookRepository repo, RestTemplate restTemplate) {
		
		ResponseEntity<List<BookEntity>> responseEntity = 
				restTemplate.exchange(
				    url,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<BookEntity>>() {}
				  );
				List<BookEntity> books = responseEntity.getBody();
				
		repo.saveAllAndFlush(books);
		logger.info("Data saved to DB successfully");
		}
	}
