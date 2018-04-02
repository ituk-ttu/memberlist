package ee.ituk.memberlist.server.auth;

import ee.ituk.memberlist.server.auth.jwt.JwtClaims;
import ee.ituk.memberlist.server.auth.jwt.JwtClaimsHolder;
import ee.ituk.memberlist.server.auth.jwt.JwtService;
import ee.ituk.memberlist.server.auth.verification.Verification;
import ee.ituk.memberlist.server.auth.verification.VerificationAlreadyExistsException;
import ee.ituk.memberlist.server.auth.verification.VerificationService;
import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {



    @Autowired
    private JwtService jwtService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private VerificationService verificationService;

    @GetMapping("/{email:.+}")
    public void getLoginEmail(@PathVariable String email) {
        if (memberService.findByEmail(email).isPresent()) {
            try {
                Verification verification = verificationService.create(email);
                System.out.println("Created: " + verification.getKey() + " " + verification.getCreatedAt());
            } catch (VerificationAlreadyExistsException ignored) {}
        }
    }

    @GetMapping("/{email}/{key}")
    public void getJwt(@PathVariable String email, @PathVariable String key, HttpServletResponse response) {
        Optional<Member> member = memberService.findByEmail(email);
        if (verificationService.verify(email, key) && member.isPresent()) {
            JwtClaims jwtClaims = new JwtClaims().setUser(member.get());
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.createFromClaims(jwtClaims));
        } else {
            response.setStatus(401);
        }
    }

    @GetMapping("/refresh")
    public void refreshToken(JwtClaimsHolder jwtClaimsHolder, HttpServletResponse response) {
        JwtClaims jwtClaims = jwtClaimsHolder.get();
        if (jwtClaims == null) {
            response.setStatus(401);
        } else {
            Member member = memberService.getMemberById(jwtClaims.getUser().getId());
            if (member != null) {
                JwtClaims newJwtClaims = new JwtClaims().setUser(member);
                response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.createFromClaims(newJwtClaims));
            } else {
                response.setStatus(401);
            }
        }
    }


}
