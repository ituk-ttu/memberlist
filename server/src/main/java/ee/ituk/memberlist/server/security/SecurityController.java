package ee.ituk.memberlist.server.security;

import ee.ituk.memberlist.server.config.JwtConfig;
import ee.ituk.memberlist.server.config.WebClientUrl;
import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.security.jwt.TokenExtractor;
import ee.ituk.memberlist.server.security.jwt.UserContext;
import ee.ituk.memberlist.server.security.jwt.token.GenericToken;
import ee.ituk.memberlist.server.security.jwt.token.JwtTokenFactory;
import ee.ituk.memberlist.server.security.verification.LoginVerification;
import ee.ituk.memberlist.server.security.verification.VerificationCreationRequest;
import ee.ituk.memberlist.server.security.verification.VerificationRequest;
import ee.ituk.memberlist.server.security.verification.VerificationService;
import ee.ituk.memberlist.server.user.User;
import ee.ituk.memberlist.server.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class SecurityController {

    @Resource
    private JwtTokenFactory jwtTokenFactory;

    @Resource
    private VerificationService verificationService;

    @Resource
    private UserService userService;

    @Resource
    private JwtConfig jwtConfig;

    @PostMapping("verify")
    public String verify(@RequestBody VerificationRequest verificationRequest, HttpServletResponse response) {
        Optional<User> user = userService.findByEmail(verificationRequest.getEmail());
        Optional<LoginVerification> loginVerification = verificationService.get(verificationRequest.getEmail());
        if (user.isPresent() &&
                loginVerification.isPresent() &&
                loginVerification.get().getKey().equals(verificationRequest.getKey()) &&
                !verificationService.isExpired(loginVerification.get())) {
            Member member = user.get().getMember();
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenFactory.createAccessToken(new UserContext(user.get().getId(), member.getName(), Collections.singletonList(new SimpleGrantedAuthority(member.getStatus().toString())))).getToken());
            return jwtTokenFactory.createRefreshToken(new UserContext(user.get().getId(), user.get().getMember().getName(), Collections.emptyList())).getToken();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
    }

    @PostMapping
    public void getLink(@RequestBody VerificationCreationRequest verificationCreationRequest, @WebClientUrl String webClientUrl, HttpServletResponse response) {
        verificationService.createIfNotExist(verificationCreationRequest.getEmail()).ifPresent(verification -> {
            verificationService.sendMagicLink(verificationCreationRequest.getEmail(), verification, webClientUrl);
        });
    }

    @PostMapping("refresh")
    public String refresh(@RequestBody GenericToken refreshToken, HttpServletResponse response) {
        Claims claims = refreshToken.parseClaims(jwtConfig.getKey()).getBody();
        User user = userService.getUserById(Long.parseLong(claims.getSubject()));
        if (claims.get("refresh", Boolean.class) && user != null) {
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenFactory.createAccessToken(new UserContext(user.getId(), user.getMember().getName(),  Collections.singletonList(new SimpleGrantedAuthority(user.getMember().getStatus().toString())))).getToken());
            return jwtTokenFactory.createRefreshToken(new UserContext(user.getId(), user.getMember().getName(), Collections.emptyList())).getToken();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
    }
}
