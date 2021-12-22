package com.thiccWallet.FCL.common.util.tokens;

import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenValidator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenValidator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Optional<PrincipalResponse> parseToken(String token) {

        System.out.println(">>>>>>>>>>>>>>>SHOULD BE STRIPPED>>>>>>>>>>>>>>>>>>>>" + token);

        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return Optional.of(new PrincipalResponse(claims.getId(), claims.getSubject()));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage()); // TODO replace with something better
        }

    }
}
