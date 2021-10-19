import java.util.ArrayList;
import java.util.List;

public class DB {
    private static DB instance = null;

    private final char[] data = new char[100];

    public DB() {
        if (instance != null) {
            throw new RuntimeException("Cannot use new on singleton class");
        }
    }

    public static IConnection getConnection () {
        return Connection.getInstance();

    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }

        return instance;
    }

    private static class Connection implements IConnection {
        private static final int MAX_CONNECTIONS = 3;

        private static final List<Connection> connections = new ArrayList<>();
        private static int i = 0;

        private final DB db = DB.getInstance();

        static {
            while (connections.size() < MAX_CONNECTIONS) {
                connections.add(new Connection());
            }
        }

        public static IConnection getInstance() {
            return connections.get(i++ % MAX_CONNECTIONS);
        }

        public char get(int index) {
            return db.data[index];
        }

        public void set(int index, char c) {
            db.data[index] = c;
        }

        public int length() {
            return db.data.length;
        }
    }
}
