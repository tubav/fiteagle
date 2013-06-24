package org.fiteagle.delivery.rest.fiteagle;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.fiteagle.interactors.api.CertificateManagerBoundary;
import org.fiteagle.interactors.api.ConfigurationManagerBoundary;
import org.fiteagle.interactors.api.UserManagerBoundary;
import org.fiteagle.interactors.certificates.CertificateManager;
import org.fiteagle.interactors.configuration.ConfigurationManager;
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
            bind(UserManagerBoundary.class).to(UserManager.class).in(Scopes.SINGLETON);
            
            bind(ResourceDefinitionPresenter.class).in(Scopes.SINGLETON);

            bind(ConfigurationPresenter.class).in(Scopes.SINGLETON);
            bind(ConfigurationManagerBoundary.class).to(ConfigurationManager.class).in(Scopes.SINGLETON);           
            
            bind(CertificatePresenter.class).in(Scopes.SINGLETON);
            bind(CertificateManagerBoundary.class).to(CertificateManager.class).in(Scopes.SINGLETON);
            
            bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
            
            filter("/api/v1/user/*").through(new AuthenticationFilter());
            
            serve("/api/*").with(GuiceContainer.class);
         }
      });
      
   }
}