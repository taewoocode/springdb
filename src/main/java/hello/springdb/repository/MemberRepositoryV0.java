package hello.springdb.repository;

import hello.springdb.connection.DBConnectionUtil;
import hello.springdb.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriverMannger 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id,money) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement( sql );
            con = getConnection();
            pstmt.setString(1, member.getMemberId() );
            pstmt.setInt( 2, member.getMoney() );
            return member;
        } catch (SQLException e) { //예외가 터지면
            log.error( "db error", e ); //log정도 남기고
            throw e; //밖으로 던진다.
        } finally {
            close( con, pstmt, null );

        }

    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement( sql );
            pstmt.setString( 1, memberId );
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId( rs.getString( "member_id" ) );
                member.setMoney( rs.getInt( "money" ) );
                return member;
            } else {
                throw new NoSuchElementException( "member not found memberId=" + memberId );
            }

        } catch (SQLException e) {
            log.error( "db error", e );
            throw e;
        } finally {
            close( con, pstmt, rs );
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close(); //SQLException이 터지면
            } catch (SQLException e) { //예외를 잡는다 대신 -> error의 로그정도만 남김
                log.error( "error", e);
            }
        }

        if (stmt != null) {
            try {
                stmt.close(); //SQLException이 터지면
            } catch (SQLException e) { //예외를 잡는다 대신 -> error의 로그정도만 남김
                log.error( "error", e);
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error( "error", e);
            }
        }
    }

    //재사용을 위해 메서드로 추출(다른곳에 또 쓰기 위함)
    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
