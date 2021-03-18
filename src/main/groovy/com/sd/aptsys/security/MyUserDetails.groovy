package com.sd.aptsys.security

import com.sd.aptsys.entity.User
import groovy.transform.CompileStatic
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

@CompileStatic
class MyUserDetails implements UserDetails {

    private static String secret = "_r3435634#@\$%\$%^\$^";
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60

    private static final Logger log = LoggerFactory.getLogger(MyUserDetails.class);

    Long id
    String username, password, token
    Collection<? extends GrantedAuthority> authorities;

    boolean accountNonExpired = true
    boolean accountNonLocked = true
    boolean credentialsNonExpired = true
    boolean enabled = true

    MyUserDetails(Long id, String username, String token, List<GrantedAuthority> grantedAuthorities) {
        this.id = id
        this.username = username
        this.token = token
        this.authorities = grantedAuthorities
    }

    MyUserDetails() {}


    static MyUserDetails fromToken(String token, User user) {
        Claims claims = getAllClaimsFromToken(token)
        MyUserDetails myUserDetails = new MyUserDetails()

        if (user.banned || user.locked) {
            myUserDetails.enabled = false
            log.info("user is either banned or locked...")
        }

        Long id = Long.valueOf(claims.get("id").toString())
        myUserDetails.setId(id)

        String username = claims.get("sub").toString()
        myUserDetails.setUsername(username)

        myUserDetails.setToken(token)

        List<Map<Integer, String>> roles = (List<Map<Integer, String>>) claims.get("roles")

        String[] authorities = new String[roles.size()]
        int idx = 0
        for (Map<Integer, String> roleMap : roles) {
            String roleDesc = String.valueOf(roleMap.get("role"))
            authorities[idx++] = roleDesc;
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(authorities);
        myUserDetails.setAuthorities(grantedAuthorities);

        return myUserDetails;
    }

    //for retrieveing any information from token we will need the secret key
    static Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //generate token for user
    static String generateToken(MyUserDetails user) {
        Map<String, Object> claims = new HashMap<>()
        claims.put("roles", user.authorities)
        claims.put("id", user.getId())
        return doGenerateToken(claims, user.getUsername())
    }

    String generateToken() {
        Map<String, Object> claims = new HashMap<>()
        claims.put("roles", this.authorities)
        claims.put("id", this.id)
        return doGenerateToken(claims, this.username)
    }

}
