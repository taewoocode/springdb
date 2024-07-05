package hello.springdb.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;

@Slf4j //log4j
public class DBConnectionUtilTest {

    @Test
    void connection() { //null이 아니면 테스트 성공
        Connection connection = DBConnectionUtil.getConnection();
        assertThat( connection ).isNotNull();
    }
}
