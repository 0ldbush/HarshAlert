package com.alnt.platform.application.security.jwt;

import java.util.Date;

public interface VerifiedJwt {
    String getHeader();
    String getPayload();
    String getIssuer();
    Date getExpiresAt();
    Long getUserId();
    String getUsername();
    String getTenant();
}