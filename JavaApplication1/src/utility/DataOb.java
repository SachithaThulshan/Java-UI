/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author chamidu
 */
public final class DataOb {

    //private static final String FILELOC = System.getenv("APPDATA") + "\\mypos\\Pencil.ft";
    static Connection con = null;
    //private static final FileHandle FH = new FileHandle(FILELOC);
//final static  String URL = "jdbc:mysql://192.168.1.3:3306/";
    static String URL = "jdbc:mysql://localhost:3306/";
    final static String DB = "my119";
    final static String DRIVER = "com.mysql.jdbc.Driver";
//   final static String USER = "root";
//
//   final static String PWORD = "123";

    final static String USER = "root";

    final static String PWORD = "19991104";
    // final static String PWORD = "ws@1979";

    public DataOb() {
        // super();
//        URL = FH.readFile("Unable to Find file");
    }

    public static String getPWORD() {
        return PWORD;
    }

    public static String getUser() {
        return USER;
    }

    public final static Connection dbConnects() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        // Class.forName("com.mysql.jdbc.Driver").newInstance();
        // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection(URL + DB, USER, PWORD);

        return con;
    }

    public final static void setAutocommit(boolean b) throws SQLException {
        con.setAutoCommit(b);
    }

    public final static void commit() {
        try {
            con.commit();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public final static void rollback() {
        try {
            con.rollback();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public final static void closeDB() throws Exception {
        if (con != null) {
            con.close();
            con = null;
        }
    }

    public final static String insertData(String table_name, String arrProperty[], String arrVal[]) throws Exception {
        if (arrVal.length != arrProperty.length) {
            throw new Exception("field count not equal value count");

        }
        String flist = "";
        String vlist = "";
        int i = 0;
        while (i < arrProperty.length) {
            flist += arrProperty[i];
            vlist += "'" + arrVal[i] + "'";
            if (i != arrProperty.length - 1) {
                flist += ",";
                vlist += ",";
            }
            i = i + 1;
        }
        String sql = "INSERT INTO " + table_name + " (" + flist + ") VALUES (" + vlist + ")";
        System.out.println(sql);
        Statement st = con.createStatement();
        int val = st.executeUpdate(sql);
        System.out.println(val + " row affected");
        return val + " row affected";

    }

    public final static String insertIgnorData(String table_name, String arrProperty[], String arrVal[]) throws Exception {
        if (arrVal.length != arrProperty.length) {
            throw new Exception("field count not equal value count");

        }
        String flist = "";
        String vlist = "";
        int i = 0;
        while (i < arrProperty.length) {
            flist += arrProperty[i];
            vlist += "'" + arrVal[i] + "'";
            if (i != arrProperty.length - 1) {
                flist += ",";
                vlist += ",";
            }
            i = i + 1;
        }
        String sql = "INSERT ignore INTO  " + table_name + " (" + flist + ") VALUES (" + vlist + ")";
        System.out.println(sql);
        Statement st = con.createStatement();
        int val = st.executeUpdate(sql);
        System.out.println(val + " row affected");
        return val + " row affected";

    }

    public final static ResultSet selectManual(String sql) throws SQLException {
        System.out.println(sql);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        return res;
    }

    public final static String insertManual(String sql) throws SQLException, Exception {
        System.out.println(sql);
        Statement st = con.createStatement();
        int val = st.executeUpdate(sql);
        System.out.println(val + " row affected");
        return val + " row affected";
    }

    public final static int dropData(String tName, String field, String value, String and) throws SQLException {
        String sql = "DELETE FROM " + tName + " where " + field + "='" + value + "' ";
        if (!and.isEmpty()) {
            sql += and;
        }
        System.out.println(sql);
        Statement stmt = con.createStatement();
        int row = stmt.executeUpdate(sql);
        return row;
    }

    public final static String updatePass(String tName, String skey, String value, String pField, String pVal) throws SQLException, Exception {
        String flist = pField;
        String vlist = "md5('" + pVal + "')";
        String sql = "UPDATE " + tName + " SET " + flist + "=" + vlist + " WHERE " + skey + "=\'" + value + "\'";
        System.out.println(sql);
        Statement st = con.createStatement();
        int val = st.executeUpdate(sql);
        return val + " row affected";
    }

    public final static int updateData(String table_name, String skey, String value, String arrField[], String arrVal[], String and) throws SQLException, Exception {
        String flist = "";
        String vlist;
        int i = 0;
        while (i < arrField.length) {
            vlist = "'" + arrVal[i] + "'";
            flist += arrField[i] + "=" + vlist;
            if (i != arrField.length - 1) {
                flist += ",";
            }
            i = i + 1;
        }
        String sql = "UPDATE " + table_name + " SET " + flist + " WHERE " + skey + "=\'" + value + "\'";
        if (and != null) {
            sql += and;
        }
        System.out.println(sql);
        Statement st = con.createStatement();
        int val = st.executeUpdate(sql);
        return val;
    }

    public final static ResultSet viewAll(String tName) throws SQLException, Exception {
        String sql = "SELECT * FROM " + tName;
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
         System.out.println(sql);
        return res;
    }

    public final static ResultSet viewSelected(String tableName, String field, String value) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + field + " = \'" + value + "'";
        System.out.println(sql);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        System.out.println(sql);
        return res;

    }

    public final static ResultSet viewLike(String tableName, String field, String value) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + field + " like \'" + value + "'";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        System.out.println(sql);
        return res;
    }

    public final static ResultSet select(String table, String key, String value, String and) throws SQLException {
        String sql;
        if (key.isEmpty()) {
            sql = "SELECT * FROM " + table;
        } else {
            sql = "SELECT * FROM " + table + " WHERE " + key + "='" + value + "'";
        }
        if (!and.isEmpty()) {
            sql += and;
        }
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        System.out.println(sql);
        return res;
    }

    public final static ResultSet selectedField(String table, String[] arrProperty, String key, String value, String and) throws SQLException {
        String sql;
        String flist = "";
        int i = 0;
        while (i < arrProperty.length) {
            flist += arrProperty[i];
            if (i != arrProperty.length - 1) {
                flist += ",";
            }
            i = i + 1;
        }
        if (key.isEmpty()) {
            sql = "SELECT " + flist + " FROM " + table;
        } else {
            sql = "SELECT " + flist + " FROM " + table + " WHERE " + key + "='" + value + "'";
        }

        if (!and.isEmpty()) {
            sql += and;
        }
        System.out.println(sql);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        return res;
    }

    public final static ResultSet selectDistinct(String table, String[] arrProperty, String key, String value, String and) throws SQLException {
        String sql;
        String flist = "";
        int i = 0;
        while (i < arrProperty.length) {
            flist += arrProperty[i];
            if (i != arrProperty.length - 1) {
                flist += ",";
            }
            i = i + 1;
        }
        if (key.isEmpty()) {
            sql = "SELECT distinct " + flist + " FROM " + table;
        } else {
            sql = "SELECT distinct " + flist + " FROM " + table + " WHERE " + key + "='" + value + "'";
        }
        if (!and.isEmpty()) {
            sql += and;
        }
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        System.out.println(sql);
        return res;
    }

    public final static ResultSet jointable(String tName1, String tname2, String[] arrField, String onkey1, String onval1, String onkey2, String onval2, String wkey, String wval, String andw) throws SQLException {
        String flist = "";
        int i = 0;
        while (i < arrField.length) {
            flist += arrField[i];
            if (i != arrField.length - 1) {
                flist += ",";
            }
            i = i + 1;
        }
        String query;
        if (onkey2.isEmpty()) {
            query = "SELECT " + flist + " FROM " + tName1 + " join " + tname2 + " on " + tName1 + "." + onkey1 + " = " + tname2 + "." + onval1 + " where " + wkey + "='" + wval + "' ";
        } else {
            query = "SELECT " + flist + " FROM " + tName1 + " join " + tname2 + " on " + tName1 + "." + onkey1 + " = " + tname2 + "." + onval1 + " and " + tName1 + "." + onkey2 + " = " + tname2 + "." + onval2 + " where " + wkey + "='" + wval + "' ";
        }
        if (!andw.isEmpty()) {
            query += andw;
        }
        System.out.println(query);
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }
}
