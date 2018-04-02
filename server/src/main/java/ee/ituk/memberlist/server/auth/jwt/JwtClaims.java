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
    public static final String MEMBER = "member";

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

    public JwtClaims setAdmin(boolean isAdmin) {
        setValue(ADMIN, isAdmin);
        return this;
    }

    public JwtClaims setMember(Member member) {
        setValue(MEMBER, member);
        return this;
    }

    public Member getMember() {
        return objectMapper.convertValue(get(MEMBER, Object.class), Member.class);
    }

    public boolean isAdmin() {
        return Optional.of(get(ADMIN, Boolean.class)).orElse(false);
    }
}
