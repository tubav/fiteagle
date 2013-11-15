package org.fiteagle.delivery.rest.fiteagle;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.fiteagle.interactors.api.CertificateManagerBoundary;
import org.fiteagle.interactors.api.ConfigurationManagerBoundary;
import org.fiteagle.interactors.api.GroupManagerBoundary;
import org.fiteagle.interactors.api.ResourceMonitoringBoundary;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.fiteagle.interactors.certificates.CertificateManager;
import org.fiteagle.interactors.configuration.ConfigurationManager;
import org.fiteagle.interactors.groupmanagement.GroupManager;
import org.fiteagle.interactors.monitoring.MonitoringManager;
import org.fiteagle.interactors.usermanagement.UserManager;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
 
public class ServletConfig extends GuiceServletContextListener {
  
   @Override
   protected Injector getInjector() {
     
      return Guice.createInjector(new JerseyServletModule() {
        
         @Override
         protected void configureServlets() {            
            
            bind(UserPresenter.class).in(Scopes.SINGLETON);
            bind(UserManagerBoundary.class).toInstance(UserManager.getInstance());
            filter("/api/v1/user/*").through(UserAuthenticationFilter.getInstance());
            filter("/api/v1/user/*").through(UserAuthorizationFilter.getInstance());
            
            bind(ResourceDefinitionPresenter.class).in(Scopes.SINGLETON);

            bind(ConfigurationController.class).in(Scopes.SINGLETON);
            bind(ConfigurationManagerBoundary.class).to(ConfigurationManager.class).in(Scopes.SINGLETON);           
            
            bind(CertificatePresenter.class).in(Scopes.SINGLETON);
            bind(CertificateManagerBoundary.class).to(CertificateManager.class).in(Scopes.SINGLETON);
            
            bind(GroupPresenter.class).in(Scopes.SINGLETON);
            bind(GroupManagerBoundary.class).to(GroupManager.class).in(Scopes.SINGLETON);
            filter("/api/v1/group/*").through(new GroupAuthenticationFilter());
            
            bind(ResourceMonitoringPresenter.class).in(Scopes.SINGLETON);
            bind(ResourceMonitoringBoundary.class).to(MonitoringManager.class).in(Scopes.SINGLETON);

            bind(StatusPresenter.class).in(Scopes.SINGLETON);
            
            
            
            bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        
            
            serve("/api/*").with(GuiceContainer.class);
         }
      });
      
   }
}
