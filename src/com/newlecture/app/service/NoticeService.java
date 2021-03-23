package com.newlecture.app.service;

import com.newlecture.app.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeService {
    String url = "jdbc:oracle:thin:@localhost:1521/xe";
    String driver = "oracle.jdbc.driver.OracleDriver";
    String uid = "newlec";
    String pwd = "100910";
    public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
        int start = 1 + (page-1)*10;
        int end = page*10;

        String sql = "SELECT * FROM NOTICE_VIEW WHERE "+field+" LIKE ? AND ROWNUM BETWEEN ? AND ?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, uid, pwd);
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, "%"+query+"%");
        st.setInt(2, start);
        st.setInt(3, end);
        ResultSet rs = st.executeQuery();

        List<Notice> list = new ArrayList<Notice>();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String writerId = rs.getString("WRITER_ID");
            Date regDate = rs.getDate("REGDATE");
            String content = rs.getString("CONTENT");
            int hit = rs.getInt("hit");
            String title = rs.getString("TITLE");
            String files = rs.getString("FILES");

            Notice notice = new Notice(
                    id, title, writerId, regDate, content, hit, files
            );


            list.add(notice);

        }


        st.close();
        st.close();
        con.close();

        return list;
    }
    public int getCount() throws ClassNotFoundException, SQLException {
        int count = 0;

        String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";

        Class.forName(driver);
        Connection con = DriverManager. getConnection(url, uid, pwd);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next())
            count = rs.getInt("COUNT");


        st.close();
        st.close();
        con.close();

return count;
    }

    public int insert(Notice notice) throws ClassNotFoundException, SQLException {
        String title = notice.getTitle();
        String writerId = notice.getWriterId();
        String content = notice.getContent();
        String files = notice.getFiles();


        String sql = "INSERT INTO notice (" +
                " title," +
                " writer_id," +
                " content," +
                " files" +
                ") VALUES (?,?,?,?)";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, uid, pwd);


        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, writerId);
        st.setString(3, content);
        st.setString(4, files);
        int result = st.executeUpdate();


        return result;
    }

    public int update(Notice notice) throws ClassNotFoundException, SQLException {

        String title = notice.getTitle();
        String content = notice.getContent();
        String files = notice.getFiles();
        int id = notice.getId();


        String sql = "UPDATE NOTICE " +//명령문 앞뒤에 띄어졌는지 확인
                "SET" +
                " TITLE = ?," +
                " CONTENT = ?," +
                " FILES= ?" +
                "WHERE ID = ?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, uid, pwd);

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, title);
        st.setString(2, content);
        st.setString(3, files);
        st.setInt(4, id);
        int result = st.executeUpdate();


        return result;
    }

    public int delete(Notice notice) throws ClassNotFoundException, SQLException {
        String title = notice.getTitle();
        String content = notice.getContent();
        String files = notice.getFiles();
        int id = notice.getId();


        String sql = "DELETE NOTICE WHERE ID = ?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, uid, pwd);

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        int result = st.executeUpdate();

        return result;
    }


}