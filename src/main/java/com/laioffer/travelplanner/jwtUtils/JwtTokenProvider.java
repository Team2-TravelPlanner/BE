package com.laioffer.travelplanner.jwtUtils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static String jwtSecret = "RandomSecretKey";

    private static final String jwtTokenPrefix = "Bearer";


    private static final String jwtHeaderString = "Authorization";


    private static final Integer jwtExpirationInMs = 86400000;

    @Override
    public String toString() {
        return "JwtTokenProvider{" +
                "jwtSecret='" + jwtSecret + '\'' +
                ", jwtTokenPrefix='" + jwtTokenPrefix + '\'' +
                ", jwtHeaderString='" + jwtHeaderString + '\'' +
                ", jwtExpirationInMs=" + jwtExpirationInMs +
                '}';
    }

    public String generateToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());

        return Jwts.builder()
                .claim("roles", authorities)
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Authentication getAuthentication(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return null;
        }

        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch(Exception e) {
            return null;
        }

        String username = claims.getSubject();
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_")? role:"ROLE_"+role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return username!=null ? new UsernamePasswordAuthenticationToken(username, null, authorities):null;
    }

    public boolean validateToken(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return false;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtHeaderString);
        if(bearerToken!=null && bearerToken.startsWith(jwtTokenPrefix)){
            return bearerToken.substring(7, bearerToken.length()).equals("") ? null : bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
