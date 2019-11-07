package se.havochvatten.uvms.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class MyResponseFilter implements ContainerResponseFilter {

    private final Logger LOG = LoggerFactory.getLogger(MyResponseFilter.class);

    @Context
    SecurityContext sec;


    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        LOG.info("reached responsefilter.  method: " + requestContext.getMethod() + " authScheme : " + sec.getAuthenticationScheme());

    }
}
