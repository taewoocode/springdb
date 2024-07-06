package hello.springdb.repository;

import hello.springdb.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;
@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member( "memberV0", 10000 );
        repository.save( member );

        Member findMember = repository.findById( member.getMemberId() );
        log.info( "findMember={}", findMember );
        assertThat( findMember ).isEqualTo( member );

        //update 10000 -> 20000
        repository.update( member.getMemberId(), 20000 );
        Member updateMember = repository.findById( member.getMemberId() );
        assertThat( updateMember ).isEqualTo( member );

    }
}