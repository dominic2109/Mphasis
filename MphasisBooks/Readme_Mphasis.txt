###### Mphasis Books Web Service ######

PRE-REQUISITE:

Please change the below configuration properties in /MphasisBooks/src/main/resources/application.properties file before executing the code:

a) spring.datasource.username
b) spring.datasource.password
c) server.port

====================================================================

NOTES:

a) A Command Line Runner Implementation is done (in com.mphasis.books package) which will create the db name 'mphasis', 
   table with name 'books' and will dump the data in books table from bookAPI given in the problem.
   
   WARNING!! - If a database with name 'mphasis' is already present with already present table 'books', then this table will be dropped while execution.
               So please take care of that.
   
   IMPORTANT!! - #Please note that if you change tha db name in the application.properties, please change in spring.datasource.url also.
   
                 #If you wish to change the table name, please change the table name in BookEntity.java also in com.mphasis.books.entity package.
				 
				 #If the db is not present on the first run then an error will appear "Unknown database 'mphasis'" but the connection will be established
				  once the DB is created by the command line runner.
				  
				 #A flag 'create.db.flag' is present in application.properties file, true -> a new table will be created and data will be dumped
				  Please keep it as true, if set to false, it is assumed that table is already created and data is already there in the database.

b) Appropriate exception handling id done using @RestControllerAdvice.

c) Appropriate logging is done using Logging and AOP (@Aspect).

====================================================================

IMPLEMENTATION

Below functionalities are implemented in the uploaded package:

[Minimum Requirement]
1. Data is fetched from bookAPI provide in the problem (https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json) and dumped into db 'mphasis'.

2. Using the following GET request "http://localhost:<portOnWhichAppRuns>/" will fetch all the books stored in the database.
   
   2.1 If no books are present in database following exception message will appear "OOPS! No book(s) were found during the search".

[Plus Point]
1. Using the following GET request "http://localhost:{portOnWhichAppRuns}/{bookID}" will fetch the book for following bookID.
   
   1.1 If no books are present in database following exception message will appear "OOPS! No book(s) were found during the search".
   1.2 If bookID given in GET request is invalid (non numeric) then following exception message will appear Invalid bookID format, 
       Please enter a valid bookID (must be a number without decimal values)

[Advanced]
1. Using the following GET request "http://localhost:<portOnWhichAppRuns>/search/{term}" will fetch the books whose title contains the term.

	1.1 If no books found for following term in database following exception message will appear "OOPS! No book(s) were found during the search".
	
	Assumption : Here it is assumed that term could appear anywhere in the title and need not be a separate word in the title.
	e.g (Just an example, may not be in data) if the term is "Bill" then title "Bill and his adventures" and "Billion Truth" both are valid searches.