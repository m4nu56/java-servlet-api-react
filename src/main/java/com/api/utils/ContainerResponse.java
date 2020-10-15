package com.api.utils;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class ContainerResponse implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", true);
        String reqHead = requestContext.getHeaderString("Access-Control-Request-Headers");
        if (null != reqHead) {
            responseContext.getHeaders().add("Access-Control-Allow-Headers", reqHead);
        }
    }

}
