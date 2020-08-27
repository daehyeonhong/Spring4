package org.zerock.persistence;

import static org.junit.Assert.fail;
import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;
import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe", user = "BOOK_EX", password = "1";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			log.info(connection);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}