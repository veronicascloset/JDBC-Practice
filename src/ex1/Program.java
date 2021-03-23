package ex1;

//9강. 데이터 입력하기와 PreparedStatement

import java.sql.*;

public class Program {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

String title ="Test3";
String content = "hahaha3";
String files = "";
int id = 256;

        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String sql = "DELETE NOTICE WHERE ID = ?";

Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, "newlec","100910");
      //  Statement st = con.createStatement();

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        int result = st.executeUpdate();
        System.out.println(result);




 //       ResultSet rs = st.executeQuery(sql);



/*        while(rs.next()) {
            int id = rs.getInt("ID");
            String writerId = rs.getString("WRITER_ID");
            Date regDate  = rs.getDate("REGDATE");
            String content = rs.getString("CONTENT");
            int hit = rs.getInt("hit");
            String title = rs.getString("TITLE");

            if (hit>=10){
                System.out.println(title);

            }


        }*/


        st.close();
        st.close();
        con.close();
;


    }
}
