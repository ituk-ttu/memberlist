package ee.ituk.memberlist.server.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ituk.memberlist.server.member.Member;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class JwtClaims extends DefaultClaims {

    public static final String ADMIN = "admin";
    public static final String CLAIMS_ID = "claimsId";
    public static final String USER = "user";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtClaims() {
        initClaimsId();
    }

    public JwtClaims(Map<String, Object> map) {
        super(map);
        initClaimsId();
    }

    private void initClaimsId() {
        setValue(CLAIMS_ID, UUID.randomUUID().toString());
    }

    public JwtClaims setUser(JwtUser user) {
        setValue(USER, user);
        return this;
    }

    public JwtClaims setUser(Member member) {
        setValue(USER, new JwtUser(member));
        return this;
    }

    public Member getUser() {
        return objectMapper.convertValue(get(USER, Object.class), Member.class);
    }
}
