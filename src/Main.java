public class Main {
    public static void main(String[] args) {
        IConnection conn1 = DB.getConnection();
        IConnection conn2 = DB.getConnection();
        IConnection conn3 = DB.getConnection();
        IConnection conn4 = DB.getConnection();

        conn1.set(0, '8');
        assert conn1.get(0) == conn2.get(0);
        assert conn1.get(0) == conn3.get(0);
        assert conn1.get(0) == conn4.get(0);

        assert conn1 == conn4;
        assert conn2 == DB.getConnection();
        assert conn3 == DB.getConnection();

        assert conn1 != conn2;
        assert conn1 != conn3;
        assert conn2 != conn3;

        assert DB.getInstance() == DB.getInstance();
    }
}
