package ee.ituk.memberlist.server.security;

import ee.ituk.memberlist.server.config.WebClientUrl;
import ee.ituk.memberlist.server.security.jwt.UserContext;
import ee.ituk.memberlist.server.security.jwt.token.JwtTokenFactory;
import ee.ituk.memberlist.server.security.verification.VerificationCreationRequest;
import ee.ituk.memberlist.server.security.verification.VerificationRequest;
import ee.ituk.memberlist.server.security.verification.VerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RestController
@RequestMapping("auth")
public class SecurityController {

    @Resource
    private JwtTokenFactory jwtTokenFactory;

    @Resource
    private VerificationService verificationService;

    @PostMapping("verify")
    public String verify(@RequestBody VerificationRequest verificationRequest) {
        return jwtTokenFactory.createAccessJwtToken(new UserContext((long) 1, "Chuck Norris", Collections.emptyList())).getToken();
    }

    @PostMapping
    public void getLink(@RequestBody VerificationCreationRequest verificationCreationRequest, @WebClientUrl String webClientUrl, HttpServletResponse response) {
        verificationService.createIfNotExist(verificationCreationRequest.getEmail()).ifPresent(verification -> {
            verificationService.sendMagicLink(verificationCreationRequest.getEmail(), verification, webClientUrl);
        });
    }
}
