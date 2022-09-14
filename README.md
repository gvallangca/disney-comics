# Title: disney-comics
An API written in GraphQL in Spring Boot which returns a list of comic books.

# Overview
I chose to use GraphQL in this use case because of the flexibility to filter the requested attributes which are only necessary for the client. Unlike REST, it has fixed response. It can over or under fetch attributes.
GraphQL also helped me to develop less endpoints. Unlike in REST, I may need to create different endpoints for different filter requirements or I may create plenty of nested conditional statements. 
With the use of GraphQL Query, it makes the development quite imperative compared to Spring MVC, it would take you.
At the database persistence, I prefered to use JPA  because it's quick and easy to use but if in case I would need a more granular requirement, I may opt choose native JDBC or Spring JDBC.

# Pre-requisites
1. <a href="https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html">JDK 11</a>
2. <a href="https://dev.mysql.com/downloads/installer/">MySQL</a>
3. <a href="https://dev.mysql.com/downloads/workbench/">MySQL Workbench</a>
4. <a href="https://maven.apache.org/download.cgi">Maven</a>
5. <a href="https://git-scm.com/download/win">GIT</a>
6. <a href="https://chrome.google.com/webstore/detail/altair-graphql-client/flnheeellpciglgpaodhkhmapeljopja?hl=en">Altair plugin for Chrome browser</a>
7. <a href="https://www.postman.com/downloads/">Postman</a>

# Installation
1. Execute clone command in git bash: 
      **git clone https://github.com/gvallangca/disney-comics.git**
2. Open **disney-comics/disney_db_dump.sql** and execute this in MySQL Workbench to create the database and load the records. 
  Alternatively, you can try the mysql cmd client and execute this command:<br>&emsp;&emsp;
  <b>mysql -u <user> <new_database> < disney_db_dump.sql</b>
3. In your current git bash, execute maven command:
      <b>mvn clean package</b>
4. Run the application through maven:
      **mvn spring-boot:run**
      
# Usage
1. Open <b>Postman</b> and create a POST request:<br>
      URL: **http://localhost:8080/login<br>**
      Headers:<br>&emsp;&emsp;
        **Content-Type: application/json<br>**
      Body:<br>&emsp;&emsp;
        **{<br>&emsp;&emsp;&emsp;
            "username": "linda",<br>&emsp;&emsp;&emsp;
            "password": "password"<br>&emsp;&emsp;
        }<br>**&emsp;&emsp;

2. Copy the Bearer token from the response header
  ![image](https://user-images.githubusercontent.com/13655665/190052936-c906ce87-0846-4558-8f94-4b1b40ad8757.png)
3. Open Chrome and open Altair plugin. Click the headers icon from the left. Define a header key <b>Authorization</b> and paste the copied Bearer token to its value. Click <b>Save</b> button.
  ![image](https://user-images.githubusercontent.com/13655665/190053306-93a065f9-629a-4e88-ae18-1744e32825c4.png)
4. In the URL above, define a POST method and the URL is http://localhost:8080/comics. Then click the Refresh button.
  ![image](https://user-images.githubusercontent.com/13655665/190054294-1948c736-ed1d-41f6-9ba7-70c92201fd3a.png)
5. In the query in the <b>Query</b> section:<br>&emsp;&emsp;&emsp;
	query ($creator_id : Long, $hero_names : [String], $series_title : [String]) {<br>&emsp;&emsp;&emsp;&emsp;
		creator(id: $creator_id) {<br>&emsp;&emsp;&emsp;&emsp;&emsp;
			id<br>&emsp;&emsp;&emsp;&emsp;&emsp;
			name<br>&emsp;&emsp;&emsp;&emsp;&emsp;
			heroes(heroFilterNames: $hero_names) {<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				id<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				name<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				seriesList(seriesFilterTitles: $series_title) {<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					title<br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
				}<br>&emsp;&emsp;&emsp;&emsp;&emsp;
			}<br>&emsp;&emsp;&emsp;&emsp;
		}<br>&emsp;&emsp;&emsp;
	}
6. At the bottom left, click <b>Variables</b> and paste the given values below.
  ![image](https://user-images.githubusercontent.com/13655665/190054576-356e25fb-63c2-4d0d-8427-981adb6d8d11.png)<br>&emsp;&emsp;
    {<br>&emsp;&emsp;&emsp;
    "creator_id" : 1,<br>&emsp;&emsp;&emsp;
    "hero_names" : ["Thor"],<br>&emsp;&emsp;&emsp;
    "series_title" : ["ALL"]<br>&emsp;&emsp;
    }
7. Hit the <b>Send Request</b> button and observe the result. You can play the variable values in step 6.
