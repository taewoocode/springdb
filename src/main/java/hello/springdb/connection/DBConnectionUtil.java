package hello.springdb.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.springdb.connection.ConnectionConst.*;

@Slf4j//log
public class DBConnectionUtil {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            log.info( "get connection={}, class={}", connection, connection.getClass() );
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException( e );
        }
        //check -> runtimeException
    }
}
