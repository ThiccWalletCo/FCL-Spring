package com.thiccWallet.FCL.common.util.tokens;

import com.thiccWallet.FCL.endpoints.leagues.League;
import com.thiccWallet.FCL.endpoints.wallets.Wallet;
import com.thiccWallet.FCL.session.login.dtos.responses.PrincipalResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    private final JwtConfig jwtConfig;

    @Autowired
    public TokenGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(PrincipalResponse subject) {

        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setSubject(subject.getUsername())
                .setIssuer("fcl")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return "Bearer " + tokenBuilder.compact();

    }

    public String createAdvancedToken(PrincipalResponse subject, String leagueId, String walletId) {
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setSubject(subject.getUsername())
                .claim("WalletId", walletId)
                .claim("LeagueId", leagueId)
                .setIssuer("fcl")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return "Bearer " + tokenBuilder.compact();
    }


}

