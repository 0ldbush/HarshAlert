package com.alnt.platform.application.filters;

import java.util.List;

import javax.inject.Inject;

import com.alnt.platform.application.security.jwt.JwtFilter;

import play.Environment;
import play.api.http.EnabledFilters;
import play.filters.cors.CORSFilter;
import play.filters.csrf.CSRFFilter;
import play.http.HttpFilters;
import play.mvc.EssentialFilter;

public class Filters implements HttpFilters {
    private final Environment env;
    private final EssentialFilter jwtFilter;
    private final EnabledFilters enabledFilters;
    private final CORSFilter corsFilter;
    private final CSRFFilter csfrFilter;
//    private final DeadboltRouteModifierTagsFilter deadbolt;

    @Inject
    public Filters(Environment env, JwtFilter jwtFilter, EnabledFilters enabledFilters, CSRFFilter csfrFilter, CORSFilter corsFilter) {//, DeadboltRouteModifierTagsFilter deadbolt) {
        this.env = env;
        this.jwtFilter = jwtFilter;
        this.enabledFilters = enabledFilters;
        this.corsFilter = corsFilter;
        this.csfrFilter = csfrFilter;
//        this.deadbolt = deadbolt;
    }

    @Override
    public List<EssentialFilter> getFilters() {
        List<EssentialFilter> zeFilters = enabledFilters.asJava().getFilters();
        zeFilters.add(corsFilter.asJava());
//        zeFilters.add(csfrFilter.asJava());
        zeFilters.add(jwtFilter);
//        zeFilters.add(deadbolt);
        return zeFilters;
    }
}
