package com.mani.soni;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitilizer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // bootstrap the dispatcherServlet.
        // Generally in spring project, entire application config is fetched into the applicationcontext object.
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ApplicationConfig.class);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("mvc", new DispatcherServlet(context));
        // whenever we register a servlet inside your application we need to tell
//        the container that this servlet need to instantiated and load on priority.
        // -1 is for lazy loading.
        servletRegistration.setLoadOnStartup(1);
//        url pattern for the project
        servletRegistration.addMapping("/");
    }
}
