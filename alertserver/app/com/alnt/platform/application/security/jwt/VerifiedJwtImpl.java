package com.alnt.platform.application.security.jwt;

import java.util.Date;

import com.auth0.jwt.interfaces.DecodedJWT;

import play.libs.Json;

public class VerifiedJwtImpl implements VerifiedJwt {
    private String header;
    private String payload;
    private String issuer;
    private Long userId;
    private String username;
    private String tenant;
    private Date expiresAt;

    public VerifiedJwtImpl(DecodedJWT decodedJWT) {
        this.header = decodedJWT.getHeader();
        this.payload = decodedJWT.getPayload();
        this.issuer = decodedJWT.getIssuer();
        this.expiresAt = decodedJWT.getExpiresAt();
        this.userId = decodedJWT.getClaim("user_id").asLong();
        this.username = decodedJWT.getClaim("user_name").asString();
        this.tenant = decodedJWT.getClaim("tenant").asString();
    }

    public String getUsername() {
		return username;
	}
    
    public String getTenant() {
		return tenant;
	}

	@Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public Date getExpiresAt() {
        return expiresAt;
    }
    
    @Override
    public String toString() {
        return Json.toJson(this).toString();
    }

    @Override
	public Long getUserId() {
		return userId;
	}
    
    
}