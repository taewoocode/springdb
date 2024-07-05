package hello.springdb.domain;

import lombok.Data;

@Data
public class Member {

    private String memberId;
    private int money;

    //class에서 기본생성자가 아닌 다른 생성자가 생성되어 있을 경우 기본생성자를 명시해줘야함
    public Member() {
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
