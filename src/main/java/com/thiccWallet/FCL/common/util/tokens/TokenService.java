package com.thiccWallet.FCL.common.util.tokens;


import com.thiccWallet.FCL.common.exception.InvalidRequestException;
import com.thiccWallet.FCL.endpoints.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    @Autowired
    public TokenService(TokenGenerator tokenGenerator, TokenValidator tokenValidator) {
        this.tokenGenerator = tokenGenerator;
        this.tokenValidator = tokenValidator;
    }

    public String generateToken(User subject) {
        return tokenGenerator.createToken(subject);
    }

    public boolean isTokenValid(String token) {

        if (token == null || token.trim().equals("")) {
            return false;
        }

        token = token.replaceAll("Bearer ", "");

        return tokenValidator.parseToken(token)
                .isPresent();
    }

    public Principal extractTokenDetails(String tokenHeader) {

        if (tokenHeader == null || tokenHeader.trim().equals("")) {
            throw new InvalidRequestException("No authentication token found on request!");
        }

        String token = tokenHeader.replaceAll("Bearer ", "");

        return tokenValidator.parseToken(token)
                .orElseThrow(InvalidTokenException::new);

    }

}

