import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }


        try {
        	
        	/*create table*/
        	Statement stmt =  connection.createStatement();
        	
        	stmt.executeUpdate("create table director"
					+ "("
					+ "director_id varchar(8),"
					+ "director_name varchar(30),"
					+ "date_of_birth varchar(10),"
					+ "date_of_death varchar(10),"
					+ "primary key (director_id)"
					+ ");");
        	stmt.executeUpdate("create table actor"
					+ "("
					+ "actor_id varchar(8),"
					+ "actor_name varchar(30),"
					+ "date_of_birth varchar(10),"
					+ "date_of_death varchar(10),"
					+ "gender varchar(6),"
					+ "primary key (actor_id)"
					+ ");");
        	stmt.executeUpdate("create table movie"
					+ "("
					+ "movie_id varchar(8),"
					+ "movie_name varchar(30),"
					+ "release_year int,"
					+ "release_month int,"
					+ "release_date int,"
					+ "publisher_name varchar(30),"
					+ "avg_rate numeric(3,2) default 0,"
					+ "primary key (movie_id)"
					+ ");");
        	
        	stmt.executeUpdate("create table award"
					+ "("
					+ "award_id serial,"
					+ "award_name varchar(30) unique,"
					+ "primary key (award_id)"
					+ ");");
        	stmt.executeUpdate("create table genre"
					+ "("
					+ "genre_name varchar(30),"
					+ "primary key (genre_name)"
					+ ");");
        	stmt.executeUpdate("create table movie_genre"
					+ "("
					+ "movie_id varchar(8),"
					+ "genre_name varchar(30),"
					+ "primary key (movie_id,genre_name),"
					+ "foreign key(movie_id) references movie "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(genre_name) references genre "
					+ "on delete set null "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table movie_obtain"
					+ "("
					+ "movie_id varchar(8),"
					+ "award_id int,"
					+ "year int,"
					+ "primary key (movie_id,award_id),"
					+ "foreign key(movie_id) references movie "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(award_id) references award "
					+ "on delete set null "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table actor_obtain"
					+ "("
					+ "actor_id varchar(8),"
					+ "award_id int,"
					+ "year int,"
					+ "primary key (actor_id,award_id),"
					+ "foreign key(actor_id) references actor "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(award_id) references award "
					+ "on delete set null "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table director_obtain"
					+ "("
					+ "director_id varchar(8),"
					+ "award_id int,"
					+ "year int,"
					+ "primary key (director_id,award_id),"
					+ "foreign key(director_id) references director "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(award_id) references award "
					+ "on delete set null "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table casting"
					+ "("
					+ "movie_id varchar(8),"
					+ "actor_id varchar(8),"
					+ "role varchar(30),"
					+ "primary key (movie_id,actor_id),"
					+ "foreign key(movie_id) references movie "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(actor_id) references actor "
					+ "on delete cascade "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table make"
					+ "("
					+ "movie_id varchar(8),"
					+ "director_id varchar(8),"
					+ "primary key (movie_id),"
					+ "foreign key(movie_id) references movie "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(director_id) references director "
					+ "on delete cascade "
					+ "on update cascade"
					+ ");");
        	stmt.executeUpdate("create table customer"
        			+ "("
					+ "customer_id varchar(8),"
					+ "customer_name varchar(30),"
					+ "date_of_birth varchar(10),"
					+ "gender varchar(6),"
					+ "primary key (customer_id)"
					+ ");");
        	
        	stmt.executeUpdate("create table customer_rate"
					+ "("
					+ "customer_id varchar(8),"
					+ "movie_id varchar(8),"
					+ "rate int,"
					+ "primary key (customer_id,movie_id),"
					+ "foreign key(movie_id) references movie "
					+ "on delete cascade "
					+ "on update cascade,"
					+ "foreign key(customer_id) references customer "
					+ "on delete cascade "
					+ "on update cascade"
					+ ");");
        	
        	System.out.println("Table created!");
        	
        	/*clear table*/
			/*stmt.executeUpdate("delete from director;" + 
					"delete from actor;" + 
					"delete from customer;" + 
					"delete from movie;" + 
					"delete from customer_rate;" + 
					"delete from genre;" + 
					"delete from movie_genre;" + 
					"delete from award;" + 
					"delete from casting;" + 
					"delete from make;" + 
					"delete from director_obtain;" + 
					"delete from movie_obtain;" + 
					"delete from actor_obtain;");*/
        	
        	/*insert initial date*/ 
			/*actor*/
			PreparedStatement psmt = connection.prepareStatement("insert into actor values(?,?,?,?,?);");
			
			psmt.setString(1, "1");
			psmt.setString(2, "Johnny Depp");
			psmt.setString(3, "1963.6.9");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "Winona Ryder");
			psmt.setString(3, "1971.10.29");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Female");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "Mia Wasikowska");
			psmt.setString(3, "1989.10.14");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Female");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "Christian Bale");
			psmt.setString(3, "1974.1.30");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "Heath Ledger");
			psmt.setString(3, "1979.4.4");
			psmt.setString(4, "2008.1.22");
			psmt.setString(5, "Male");
			psmt.executeUpdate();

			psmt.setString(1, "6");
			psmt.setString(2, "Jesse Eisenberg");
			psmt.setString(3, "1983.10.5");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
        	
			psmt.setString(1, "7");
			psmt.setString(2, "Justin Timberlake");
			psmt.setString(3, "1981.1.31");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
			
			psmt.setString(1, "8");
			psmt.setString(2, "Fionn Whitehead");
			psmt.setString(3, "1997.7.18");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
        	
			psmt.setString(1, "9");
			psmt.setString(2, "Tom Hardy");
			psmt.setString(3, "1977.9.15");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.setString(5, "Male");
			psmt.executeUpdate();
        	
			/*director*/
			psmt = connection.prepareStatement("insert into director values(?,?,?,?);");
        	
			psmt.setString(1, "1");
			psmt.setString(2, "Tim Burton");
			psmt.setString(3, "1958.8.25");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "David Fincher");
			psmt.setString(3, "1962.8.28");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "Christopher Nolan");
			psmt.setString(3, "19570.7.30");
			psmt.setNull(4, java.sql.Types.NULL);
			psmt.executeUpdate();
			
			/*movie*/
			psmt = connection.prepareStatement("insert into movie values(?,?,?,?,?,?);");
			
			psmt.setString(1, "1");
			psmt.setString(2, "Edward Scissorhands");
			psmt.setInt(3, 1991);
			psmt.setInt(4, 6);
			psmt.setInt(5, 29);
			psmt.setString(6, "20th Century Fox Presents");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "Alice In Wonderland");
			psmt.setInt(3, 2010);
			psmt.setInt(4, 3);
			psmt.setInt(5, 4);
			psmt.setString(6, "Korea Sony Pictures");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "The Social Network");
			psmt.setInt(3, 2010);
			psmt.setInt(4,11);
			psmt.setInt(5, 18);
			psmt.setString(6, "Korea Sony Pictures");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "The Dark Knight");
			psmt.setInt(3, 2008);
			psmt.setInt(4, 8);
			psmt.setInt(5, 6);
			psmt.setString(6, "Warner Brothers Korea");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "Dunkirk");
			psmt.setInt(3, 2017);
			psmt.setInt(4, 7);
			psmt.setInt(5, 13);
			psmt.setString(6, "Warner Brothers Korea");
			psmt.executeUpdate();
			
			/*genre*/
			
			psmt = connection.prepareStatement("insert into genre values(?);");
			
			psmt.setString(1, "Fantasy");
			psmt.executeUpdate();
			
			psmt.setString(1, "Romance");
			psmt.executeUpdate();
			
			psmt.setString(1, "Adventure");
			psmt.executeUpdate();
			
			psmt.setString(1, "Family");
			psmt.executeUpdate();
			
			psmt.setString(1, "Drama");
			psmt.executeUpdate();
			
			psmt.setString(1, "Action");
			psmt.executeUpdate();
			
			psmt.setString(1, "Mystery");
			psmt.executeUpdate();
			
			psmt.setString(1, "Thriller");
			psmt.executeUpdate();
			
			psmt.setString(1, "War");
			psmt.executeUpdate();
			
			/*movie_genre*/
			
			psmt = connection.prepareStatement("insert into movie_genre values(?,?);");
			
			psmt.setString(1,"1");
			psmt.setString(2,"Fantasy");
			psmt.executeUpdate();
			
			psmt.setString(1,"1");
			psmt.setString(2,"Romance");
			psmt.executeUpdate();
			
			psmt.setString(1,"2");
			psmt.setString(2,"Fantasy");
			psmt.executeUpdate();
			
			psmt.setString(1,"2");
			psmt.setString(2,"Adventure");
			psmt.executeUpdate();
			
			psmt.setString(1,"2");
			psmt.setString(2,"Family");
			psmt.executeUpdate();
			
			psmt.setString(1,"3");
			psmt.setString(2,"Drama");
			psmt.executeUpdate();
			
			psmt.setString(1,"4");
			psmt.setString(2,"Action");
			psmt.executeUpdate();
			
			psmt.setString(1,"4");
			psmt.setString(2,"Drama");
			psmt.executeUpdate();
			
			psmt.setString(1,"4");
			psmt.setString(2,"Mystery");
			psmt.executeUpdate();
			
			psmt.setString(1,"4");
			psmt.setString(2,"Thriller");
			psmt.executeUpdate();
			
			psmt.setString(1,"5");
			psmt.setString(2,"Action");
			psmt.executeUpdate();
			
			psmt.setString(1,"5");
			psmt.setString(2,"Drama");
			psmt.executeUpdate();
			
			psmt.setString(1,"5");
			psmt.setString(2,"Thriller");
			psmt.executeUpdate();
			
			psmt.setString(1,"5");
			psmt.setString(2,"War");
			psmt.executeUpdate();
			
			/*casting*/
			
			psmt = connection.prepareStatement("insert into casting values(?,?,?);");
			
			psmt.setString(1, "1");
			psmt.setString(2, "1");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "1");
			psmt.setString(2, "2");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "1");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "3");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "6");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "7");
			psmt.setString(3, "Supporting actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "4");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "5");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "8");
			psmt.setString(3, "Main actor");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "9");
			psmt.setString(3, "Supporting actor");
			psmt.executeUpdate();
			
			/*make*/
			
			psmt = connection.prepareStatement("insert into make values(?,?);");
			
			psmt.setString(1, "1");
			psmt.setString(2, "1");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "1");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "2");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "3");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "3");
			psmt.executeUpdate();
			
			/*customer*/
			
			psmt = connection.prepareStatement("insert into customer values(?,?,?,?);");
			
			psmt.setString(1, "1");
			psmt.setString(2, "Ethan");
			psmt.setString(3, "1997.11.14");
			psmt.setString(4, "Male");
			psmt.executeUpdate();
			
			psmt.setString(1, "2");
			psmt.setString(2, "John");
			psmt.setString(3, "1978.1.23");
			psmt.setString(4, "Male");
			psmt.executeUpdate();
			
			psmt.setString(1, "3");
			psmt.setString(2, "Hayden");
			psmt.setString(3, "1980.5.4");
			psmt.setString(4, "Female");
			psmt.executeUpdate();
			
			psmt.setString(1, "4");
			psmt.setString(2, "Jill");
			psmt.setString(3, "1981.4.17");
			psmt.setString(4, "Female");
			psmt.executeUpdate();
			
			psmt.setString(1, "5");
			psmt.setString(2, "bell");
			psmt.setString(3, "1990.4.17");
			psmt.setString(4, "Female");
			psmt.executeUpdate();
			
			System.out.println("Initial data inserted!");
			
			System.out.println("\n\n\n\n\n");
			
			
			/*2-1*/
			
			System.out.println("Statement : Winona Ryder won the “Best supporting actor” award in 1994");
			System.out.println("Translated SQl : SELECT actorID FROM actor WHERE actorName='Winona Ryder'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best supporting actor')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best supporting actor'");
			System.out.println("Translateed SQl : INSERT INTO actorObtain VALUES (2, 1, 1994)");
			
			String name_of_award = "Best supporting actor";
			String table_name="award";
			String attr_name1 = "awardID";
			String attr_name2 = "awardName";
			int award_id = 0;
			String award_name;
			boolean already_exist=false;
			int select_award_id=1;
			String select_actor_id="1";
			String select_director_id="1";
			String select_movie_id="1";
			
			ResultSet rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select actor_id from actor where actor_name='Winona Ryder'");
			while(rs.next())select_actor_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into actor_obtain values(?,?,?)");
			psmt.setString(1, select_actor_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,1994);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from actor_obtain");
			table_name="actor_obtain";
			attr_name1="actor_id";
			attr_name2="award_id";
			String attr_name3="year";
			String actor_id;
			int year;
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				actor_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+actor_id+"                   "+award_id+"                   "+year);
			}
			
			System.out.println("\n\n\n\n\n");
			
			
			/*2-2*/
			System.out.println("Statement : Tom Hardy won the “Best supporting actor” award in 2018");
			System.out.println("Translated SQl : SELECT actorID FROM actor WHERE actorName='Tom Hardy'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best supporting actor')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best supporting actor'");
			System.out.println("Translateed SQl : INSERT INTO actorObtain VALUES (9, 1, 2018)");
			
			name_of_award = "Best supporting actor";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select actor_id from actor where actor_name='Tom Hardy'");
			while(rs.next())select_actor_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into actor_obtain values(?,?,?)");
			psmt.setString(1, select_actor_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2018);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from actor_obtain");
			table_name="actor_obtain";
			attr_name1="actor_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				actor_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+actor_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-3*/
			
			System.out.println("Statement : Heath Ledger won the “Best villain actor” award in 2009");
			System.out.println("Translated SQl : SELECT actorID FROM actor WHERE actorName='Heath Ledger'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best villain actor')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best villain actor'");
			System.out.println("Translateed SQl : INSERT INTO actorObtain VALUES (5, 2, 2009)");
			
			name_of_award = "Best villain actor";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select actor_id from actor where actor_name='Heath Ledger'");
			while(rs.next())select_actor_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into actor_obtain values(?,?,?)");
			psmt.setString(1, select_actor_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2009);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from actor_obtain");
			table_name="actor_obtain";
			attr_name1="actor_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				actor_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+actor_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-4*/
			
			System.out.println("Statement : Johnny Depp won the “Best main actor” award in 2011");
			System.out.println("Translated SQl : SELECT actorID FROM actor WHERE actorName='Johnny Depp'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best main actor')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best main actor'");
			System.out.println("Translateed SQl : INSERT INTO actorObtain VALUES (1, 3, 2011)");
			
			name_of_award = "Best main actor";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select actor_id from actor where actor_name='Johnny Depp'");
			while(rs.next())select_actor_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into actor_obtain values(?,?,?)");
			psmt.setString(1, select_actor_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2011);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from actor_obtain");
			table_name="actor_obtain";
			attr_name1="actor_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				actor_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+actor_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-5*/
			
			System.out.println("Statement : Edward Scissorhands won the “Best fantasy movie” award in 1991");
			System.out.println("Translated SQl : SELECT movieID FROM movie WHERE movieName='Edward Scissorhands'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best fantasy movie')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best fantasy movie'");
			System.out.println("Translateed SQl : INSERT INTO movieObtain VALUES (1, 4, 1991)");
			
			name_of_award = "Best fantasy movie";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select movie_id from movie where movie_name='Edward Scissorhands'");
			while(rs.next())select_movie_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into movie_obtain values(?,?,?)");
			psmt.setString(1, select_movie_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,1991);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from movie_obtain");
			table_name="movie_obtain";
			attr_name1="movie_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			String movie_id;
			while(rs.next()) {
				movie_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+movie_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-6*/
			
			System.out.println("Statement : Alice In Wonderland won the “Best fantasy movie” award in 2011");
			System.out.println("Translated SQl : SELECT movieID FROM movie WHERE movieName='Alice In Wonderland'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best fantasy movie')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best fantasy movie'");
			System.out.println("Translateed SQl : INSERT INTO movieObtain VALUES (2, 4, 2011)");
			
			name_of_award = "Best fantasy movie";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select movie_id from movie where movie_name='Alice In Wonderland'");
			while(rs.next())select_movie_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into movie_obtain values(?,?,?)");
			psmt.setString(1, select_movie_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2011);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from movie_obtain");
			table_name="movie_obtain";
			attr_name1="movie_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				movie_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+movie_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-7*/
			
			System.out.println("Statement : The Dark Knight won the “Best picture” award in 2009");
			System.out.println("Translated SQl : SELECT movieID FROM movie WHERE movieName='The Dark Knight'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best picture')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best picture'");
			System.out.println("Translateed SQl : INSERT INTO movieObtain VALUES (4, 5, 2009)");
			
			name_of_award = "Best picture";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select movie_id from movie where movie_name='The Dark Knight'");
			while(rs.next())select_movie_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into movie_obtain values(?,?,?)");
			psmt.setString(1, select_movie_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2009);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from movie_obtain");
			table_name="movie_obtain";
			attr_name1="movie_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				movie_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+movie_id+"                   "+award_id+"                   "+year);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*2-8*/
			
			System.out.println("Statement : Christopher Nolan won the “Best director” award in 2018");
			System.out.println("Translated SQl : SELECT directorID FROM director WHERE directorName='Christopher Nolan'");
			System.out.println("Translated SQl : INSERT IGNORE INTO award (awardName) VALUES ('Best director')");
			System.out.println("Translateed SQl : SELECT awardID FROM award WHERE awardName='Best director'");
			System.out.println("Translateed SQl : INSERT INTO movieObtain VALUES (3, 6, 2018)");
			
			name_of_award = "Best director";
			rs = stmt.executeQuery("select * from award");
			table_name="award";
			attr_name1 = "awardID";
			attr_name2 = "awardName";
			already_exist=false;
			
			rs = stmt.executeQuery("select * from award");
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"            "+attr_name2);
			while(rs.next()) {
				award_id = rs.getInt(1);
				award_name = rs.getString(2);
				if(award_name.equals(name_of_award)) {
					already_exist=true;
					select_award_id=award_id;
				}
				System.out.println("              "+award_id+"                  "+award_name);
			}
			
			if(!already_exist) {
				psmt = connection.prepareStatement("insert into award(award_name) values(?) on conflict (award_name) do nothing"); 
				psmt.setString(1, name_of_award);
				psmt.executeUpdate();
				System.out.println("              "+(award_id+1)+"                  "+name_of_award);
				select_award_id=award_id+1;
			}
			
			rs = stmt.executeQuery("select director_id from director where director_name='Christopher Nolan'");
			while(rs.next())select_director_id=rs.getString(1);
			
			
			psmt = connection.prepareStatement("insert into director_obtain values(?,?,?)");
			psmt.setString(1, select_director_id);
			psmt.setInt(2, select_award_id);
			psmt.setInt(3,2018);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from director_obtain");
			table_name="director_obtain";
			attr_name1="director_id";
			attr_name2="award_id";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			String director_id;
			while(rs.next()) {
				director_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+director_id+"                   "+award_id+"                   "+year);
			}
			
			
			System.out.println("\n\n\n\n\n");
			
			
			/*3-1*/
			
			System.out.println("Statement : Ethan rates 5 to “Dunkirk”");
			System.out.println("Translated SQl : SELECT customerID FROM customer WHERE customerName='Ethan'");
			System.out.println("Translated SQl : SELECT movieID FROM movie WHERE movieName='Dunkrik'");
			System.out.println("Translateed SQl : INSERT INTO customerRate VALUES (1, 5, 5)");
			System.out.println("Translateed SQl : UPDATE INTO movie SET avgRate =(SELECT avg(rate) FROM customerRate where movie_id = '5') where movie_id = '5'");
			
			String select_customer_id="1";
			String customer_id;
			rs = stmt.executeQuery("select customer_id from customer where customer_name='Ethan'");
			while(rs.next()) {
				select_customer_id=rs.getString(1);
			}
			rs = stmt.executeQuery("select movie_id from movie where movie_name='Dunkrik'");
			while(rs.next()) {
				select_movie_id=rs.getString(1);
			}
			
			psmt = connection.prepareStatement("insert into customer_rate values(?,?,?)");
			psmt.setString(1, select_customer_id);
			psmt.setString(2, select_movie_id);
			psmt.setInt(3,5);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
			int rate;
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                   "+movie_id+"                   "+rate);
			}
			
			psmt = connection.prepareStatement("update movie set avg_rate = (select avg(rate) from customer_rate where movie_id = ?) where movie_id = ?;");
			psmt.setString(1,select_movie_id);
			psmt.setString(2, select_movie_id);
			psmt.executeUpdate();
			
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			String attr_name4="release_month";
			String attr_name5="release_date";
			String attr_name6="publisher_name";
			String attr_name7="avg_rate";
			String movie_name;
			int release_year;
			int release_month;
			int release_date;
			String publisher_name;
			float avg_rate; 
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*3-2*/
			
			System.out.println("Statement : Bell rates 5 to the movies whose director is “Tim Burton”");
			System.out.println("Translated SQl : SELECT customerID FROM customer WHERE customerName='Bell'");
			System.out.println("Translated SQl : SELECT movieID FROM make natural join director WHERE director_name='Tim Burton'");
			System.out.println("Translateed SQl : INSERT INTO customerRate VALUES (?, ?, ?)");
			System.out.println("Translateed SQl : UPDATE INTO movie SET avgRate =(SELECT avg(rate) FROM customerRate where movie_id = '?') where movie_id = '?'");
			
			rs = stmt.executeQuery("select customer_id from customer where customer_name='Bell'");
			while(rs.next()) {
				select_customer_id=rs.getString(1);
			}
			rs = stmt.executeQuery("select movie_id from director , (make natural join movie) as T where T.director_id=director.director_id and director_name='Tim Burton'");
			while(rs.next()) {
				select_movie_id=rs.getString(1);
				psmt = connection.prepareStatement("insert into customer_rate values(?,?,?)");
				psmt.setString(1, select_customer_id);
				psmt.setString(2, select_movie_id);
				psmt.setInt(3,5);
				psmt.executeUpdate();
				psmt = connection.prepareStatement("update movie set avg_rate = (select avg(rate) from customer_rate where movie_id = ?) where movie_id = ?;");
				psmt.setString(1,select_movie_id);
				psmt.setString(2, select_movie_id);
				psmt.executeUpdate();
			}
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                   "+movie_id+"                   "+rate);
			}
			
			
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			attr_name4="release_month";
			attr_name5="release_date";
			attr_name6="publisher_name";
			attr_name7="avg_rate";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			System.out.println("\n\n\n\n\n");
			/*3-3*/
			System.out.println("Statement : Jill rates 4 to the movies whose main actor is female");
			System.out.println("Translated SQl : SELECT customerID FROM customer WHERE customerName='Jill'");
			System.out.println("Translated SQl : SELECT movieID FROM casting natural join actor WHERE role='main actor' and gender='Female'");
			System.out.println("Translateed SQl : INSERT INTO customerRate VALUES (?, ?, ?)");
			System.out.println("Translateed SQl : UPDATE INTO movie SET avgRate =(SELECT avg(rate) FROM customerRate where movie_id = '?') where movie_id = '?'");
			
			rs = stmt.executeQuery("select customer_id from customer where customer_name='Jill'");
			while(rs.next()) {
				select_customer_id=rs.getString(1);
			}
			rs = stmt.executeQuery("select movie_id from casting natural join actor where role='Main actor' and gender='Female'");
			while(rs.next()) {
				select_movie_id=rs.getString(1);
				psmt = connection.prepareStatement("insert into customer_rate values(?,?,?)");
				psmt.setString(1, select_customer_id);
				psmt.setString(2, select_movie_id);
				psmt.setInt(3,4);
				psmt.executeUpdate();
				psmt = connection.prepareStatement("update movie set avg_rate = (select avg(rate) from customer_rate where movie_id = ?) where movie_id = ?;");
				psmt.setString(1,select_movie_id);
				psmt.setString(2, select_movie_id);
				psmt.executeUpdate();
			}
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                   "+movie_id+"                   "+rate);
			}
			
			
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			attr_name4="release_month";
			attr_name5="release_date";
			attr_name6="publisher_name";
			attr_name7="avg_rate";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			System.out.println("\n\n\n\n\n");
			/*3-4*/
			
			System.out.println("Statement : Hayden rates 4 to the fantasy movies");
			System.out.println("Translated SQl : SELECT customerID FROM customer WHERE customerName='Hayden'");
			System.out.println("Translated SQl : SELECT movieID FROM movie natural join movieGenre WHERE genreName='Fantasy'");
			System.out.println("Translateed SQl : INSERT INTO customerRate VALUES (?, ?, ?)");
			System.out.println("Translateed SQl : UPDATE INTO movie SET avgRate =(SELECT avg(rate) FROM customerRate where movie_id = '?') where movie_id = '?'");
			
			rs = stmt.executeQuery("select customer_id from customer where customer_name='Hayden'");
			while(rs.next()) {
				select_customer_id=rs.getString(1);
			}
			rs = stmt.executeQuery("select movie_id from movie natural join movie_genre where genre_name='Fantasy'");
			while(rs.next()) {
				select_movie_id=rs.getString(1);
				psmt = connection.prepareStatement("insert into customer_rate values(?,?,?)");
				psmt.setString(1, select_customer_id);
				psmt.setString(2, select_movie_id);
				psmt.setInt(3,4);
				psmt.executeUpdate();
				psmt = connection.prepareStatement("update movie set avg_rate = (select avg(rate) from customer_rate where movie_id = ?) where movie_id = ?;");
				psmt.setString(1,select_movie_id);
				psmt.setString(2, select_movie_id);
				psmt.executeUpdate();
			}
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                   "+movie_id+"                   "+rate);
			}
			
			
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			attr_name4="release_month";
			attr_name5="release_date";
			attr_name6="publisher_name";
			attr_name7="avg_rate";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			System.out.println("\n\n\n\n\n");
			/*3-5*/
			
			System.out.println("Statement : John rates 5 to the movies whose director won the “Best director” award");
			System.out.println("Translated SQl : SELECT customerID FROM customer WHERE customerName='John'");
			System.out.println("Translated SQl : SELECT movieID FROM make natural join director_obtain natural join award	WHERE award_name='Best director'");
			System.out.println("Translateed SQl : INSERT INTO customerRate VALUES (?, ?, ?)");
			System.out.println("Translateed SQl : UPDATE INTO movie SET avgRate =(SELECT avg(rate) FROM customerRate where movie_id = '?') where movie_id = '?'");
			
			rs = stmt.executeQuery("select customer_id from customer where customer_name='John'");
			while(rs.next()) {
				select_customer_id=rs.getString(1);
			}
			rs = stmt.executeQuery("select movie_id from make natural join director_obtain natural join award where award_name='Best director'");
			while(rs.next()) {
				select_movie_id=rs.getString(1);
				psmt = connection.prepareStatement("insert into customer_rate values(?,?,?)");
				psmt.setString(1, select_customer_id);
				psmt.setString(2, select_movie_id);
				psmt.setInt(3,4);
				psmt.executeUpdate();
				psmt = connection.prepareStatement("update movie set avg_rate = (select avg(rate) from customer_rate where movie_id = ?) where movie_id = ?;");
				psmt.setString(1,select_movie_id);
				psmt.setString(2, select_movie_id);
				psmt.executeUpdate();
			}
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                   "+movie_id+"                   "+rate);
			}
			
			
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			attr_name4="release_month";
			attr_name5="release_date";
			attr_name6="publisher_name";
			attr_name7="avg_rate";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			System.out.println("\n\n\n\n\n");
			/*4*/
			
			System.out.println("Statement : Select the names of the movies whose actor are dead");
			System.out.println("Translated SQl : SELECT movieName FROM movie natural join casting natural join actor WHERE date_of_death is not null");
			rs = stmt.executeQuery("select movie_name "
					+ "from movie natural join casting natural join actor "
					+ "where date_of_death is not null ");
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			System.out.println("\n\n\n\n\n");
			
			/*5*/
			System.out.println("Statement : Select the names of the directors who cast the same actor more than once");
			System.out.println("Translated SQl : SELECT directorName FROM (SELECT directorID FROM casting natural join make GROUP BY directID,actorID"
					+ "HAVING count(movieID))as T natural join director ");
			rs = stmt.executeQuery("select director_name "
					+ "from (select director_id "
					+ "from make natural join casting "
					+ "group by director_id,actor_id "
					+ "having count(movie_id)>1"
					+ ") as T natural join director");
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			System.out.println("\n\n\n\n\n");
			
			/*6*/
			
			System.out.println("Statement : Select the names of the movies and the genres, where movies have the common genre");
			System.out.println("Translated SQl : Select movie_name, genre_name FROM movie natural join(SELECT movie_id, genre_name FROM movie_genre WHERE genre_name in (SELECT genre_name FROM movie_genre GROUP BY genre_name HAVING count(genre_name)>1))as A)");
			rs = stmt.executeQuery("select movie_name, genre_name "
					+ "from movie natural join("
					+ "select movie_id, genre_name "
					+ "from movie_genre "
					+ "where genre_name in ("
					+ "select genre_name "
					+ "from movie_genre "
					+ "group by genre_name "
					+ "having count(genre_name)>1))as A");
			while(rs.next()){
				System.out.println(rs.getString(1)+"   "+rs.getString(2));
			}
			System.out.println("\n\n\n\n\n");
			
			/*7*/
			System.out.println("Statement : Delete the movies whose director or actor did not get any award and delete data from related tables");
			System.out.println("Translated SQl : DELETE FROM movie "
					+ "WHERE movie_id not in (SELECT movie_id from director_obtain) "
					+ "and movie not in(SELECT movie_id FROM actor_obtain)");
			stmt.executeUpdate("delete from movie where movie_id not in (select movie_id from director_obtain)"
					+ "and movie_id not in (select movie_id from actor_obtain) ");
			rs = stmt.executeQuery("select * from movie");
			table_name="movie";
			attr_name1="movie_id";
			attr_name2="movie_name";
			attr_name3="release_year";
			attr_name4="release_month";
			attr_name5="release_date";
			attr_name6="publisher_name";
			attr_name7="avg_rate";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"            "+attr_name3+"            "+attr_name4+"            "+attr_name5+"            "+attr_name6+"            "+attr_name7);
			while(rs.next()) {
				movie_id=rs.getString(1);
				movie_name=rs.getString(2);
				release_year=rs.getInt(3);
				release_month=rs.getInt(4);
				release_date=rs.getInt(5);
				publisher_name=rs.getString(6);
				avg_rate=rs.getFloat(7);
				System.out.println("              "+movie_id+"                "+movie_name+"       "+release_year+"                   "+release_month+"                   "+release_date+"                   "+publisher_name+"                   "+avg_rate);
			}
			
			rs = stmt.executeQuery("select * from movie_genre");
			table_name="movie_genre";
			attr_name1="movie_id";
			attr_name2="genre_name";
			
			String genre_name;
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2);
			while(rs.next()) {
				movie_id=rs.getString(1);
				genre_name=rs.getString(2);
				System.out.println("              "+movie_id+"                "+genre_name);
			}
			
			rs = stmt.executeQuery("select * from movie_obtain");
			table_name="movie_obtain";
			attr_name1="movie_id";
			attr_name2="award_id";
			attr_name3="year";
			
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"         "+attr_name3);
			while(rs.next()) {
				movie_id=rs.getString(1);
				award_id=rs.getInt(2);
				year=rs.getInt(3);
				System.out.println("              "+movie_id+"                "+award_id+"                "+year);
			}
			
			rs = stmt.executeQuery("select * from casting");
			table_name="casting";
			attr_name1="movie_id";
			attr_name2="actor_id";
			attr_name3="role";
			
			String role;
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"         "+attr_name3);
			while(rs.next()) {
				movie_id=rs.getString(1);
				actor_id=rs.getString(2);
				role=rs.getString(3);
				System.out.println("              "+movie_id+"                "+actor_id+"                "+role);
			}
			
			rs = stmt.executeQuery("select * from make");
			table_name="make";
			attr_name1="movie_id";
			attr_name2="director_id";
		
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2);
			while(rs.next()) {
				movie_id=rs.getString(1);
				director_id=rs.getString(2);
				System.out.println("              "+movie_id+"                "+director_id);
			}

			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
		
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"         "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                "+movie_id+"                "+rate);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*8*/
			System.out.println("Statement : . Delete all customers and delete data from related tables");
			System.out.println("Translated SQl : DELETE FROM customer");
			stmt.executeUpdate("delete from customer");
			
			rs = stmt.executeQuery("select * from customer");
			table_name="customer";
			attr_name1="customer_id";
			attr_name2="customer_name";
			attr_name3="date_of_birth";
			attr_name4="gender";
		
			String customer_name;
			String date_of_birth;
			String gender;
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"         "+attr_name3+"         "+attr_name4);
			while(rs.next()) {
				customer_id=rs.getString(1);
				customer_name=rs.getString(2);
				date_of_birth=rs.getString(3);
				gender=rs.getString(4);
				System.out.println("              "+customer_id+"                "+customer_name+"                "+date_of_birth+"                "+gender);
			}
			
			rs = stmt.executeQuery("select * from customer_rate");
			table_name="customer_rate";
			attr_name1="customer_id";
			attr_name2="movie_id";
			attr_name3="rate";
		
			System.out.println("---------<"+table_name+">-------");
			System.out.println("              "+attr_name1+"         "+attr_name2+"         "+attr_name3);
			while(rs.next()) {
				customer_id=rs.getString(1);
				movie_id=rs.getString(2);
				rate=rs.getInt(3);
				System.out.println("              "+customer_id+"                "+movie_id+"                "+rate);
			}
			System.out.println("\n\n\n\n\n");
			
			
			/*9*/
			System.out.println("Statement : Delete all tables and data.");
			System.out.println("Translated SQL : drop table actor_obtain");
			System.out.println("Translated SQL : drop table movie_obtain");
			System.out.println("Translated SQL : drop table director_obtain");
			System.out.println("Translated SQL : drop table make");
			System.out.println("Translated SQL : drop table casting");
			System.out.println("Translated SQL : drop table award");
			System.out.println("Translated SQL : drop table movie_genre");
			System.out.println("Translated SQL : drop table genre");
			System.out.println("Translated SQL : drop table customer_rate");
			System.out.println("Translated SQL : drop table movie");
			System.out.println("Translated SQL : drop table customer");
			System.out.println("Translated SQL : drop table actor");
			System.out.println("Translated SQL : drop table director");
			System.out.println("Delete Tables and Data");
			
			stmt.executeUpdate("drop table actor_obtain;" + 
					"drop table movie_obtain;" + 
					"drop table director_obtain;" + 
					"drop table make;" + 
					"drop table casting;" + 
					"drop table award;" + 
					"drop table movie_genre;" + 
					"drop table genre;" + 
					"drop table customer_rate;" + 
					"drop table movie;" + 
					"drop table customer;" + 
					"drop table actor;" + 
					"drop table director;");
			
			
			connection.close();
        } catch(SQLException e) {
			e.printStackTrace();
		}
    }
}