package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class ConnectionPool {
    private static ConnectionPool cp = new ConnectionPool();
    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private File creds = new File();

    private final String url = creds.getDbUrl();
    private final String user = creds.getDbUsername();
    private final String password = creds.getDbPassword();
    private final int MAX_CONNECTIONS = 18;

    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        }

        connections = new LinkedBlockingQueue<Connection>(MAX_CONNECTIONS);

        try {
            for(int i = 0; i < MAX_CONNECTIONS; ++i) {
                connections.put(DriverManager.getConnection(url,user,password));
            }
        } catch (SQLException e) {
            log.warning("Troubles with database");
        } catch (InterruptedException e) {
            log.warning("Connection was interrupted");
        }
    }

    public static ConnectionPool getConnectionPool() {
        return cp;
    }

    public Connection getConnection() throws InterruptedException, SQLException {
        Connection c = connections.take();
        if (c.isClosed()) {
            c = DriverManager.getConnection(url,user,password);
        }
        System.out.println("URL DB: " + url);
        return c;
    }

    public void releaseConnection(Connection c) throws InterruptedException {
        connections.put(c);
    }
}
