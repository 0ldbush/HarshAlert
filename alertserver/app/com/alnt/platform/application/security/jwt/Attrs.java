package com.alnt.platform.application.security.jwt;

import play.libs.typedmap.TypedKey;

public class Attrs {
    public static final TypedKey<VerifiedJwt> VERIFIED_JWT = TypedKey.<VerifiedJwt>create("verifiedJwt");
}