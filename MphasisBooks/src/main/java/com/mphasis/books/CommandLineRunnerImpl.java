package com.mphasis.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mphasis.books.repository.BookRepository;
import com.mphasis.books.util.CreateDataBase;

/*
 * Main method of this class runs immediately after application is loaded.
 * Methods below in the main class are for DB, Table creation and table updation with books api data
 */

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${books.api.url}")
	private String bookApiUrl;
	@Value("${create.db.flag}")
	private boolean dbManageFlag;
	@Value("${db.connectionString.url}")
	private String dbConnectionUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${db.name}")
	private String dbName;
	@Value("${table.name}")
	private String tableName;
	
    @Override
    public void run(String... args) throws Exception {
        
    	CreateDataBase createDB = new CreateDataBase(dbConnectionUrl, 
    			username, password, dbName, tableName);
    	
    	createDB.createDatabase();
    	if(dbManageFlag) {
    		createDB.createTable();
    		createDB.pushDataToTableViaJson(bookApiUrl, bookRepo, restTemplate);
    	}
    }
}
