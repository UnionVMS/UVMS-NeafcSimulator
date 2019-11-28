package se.havochvatten.uvms.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
public class MyRequestFilter  implements ContainerRequestFilter {

    private final Logger LOG = LoggerFactory.getLogger(MyRequestFilter.class);

    @Context
    SecurityContext sec;

    @Override
    public void filter(ContainerRequestContext requestContext)  {

        LOG.info("reached requestfilter.  method: " + requestContext.getMethod() + " authScheme : " + sec.getAuthenticationScheme());

    }
}
