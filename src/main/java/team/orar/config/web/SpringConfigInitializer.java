package team.orar.config.web;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;
import team.orar.config.spring.MainContextConfig;
import team.orar.config.spring.MvcContextConfig;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class SpringConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {MainContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {MvcContextConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext.setInitParameter("spring.profiles.active", "dev");

        servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        servletContext.setInitParameter("log4jExposeWebAppRoot", "false");
        servletContext.addListener(Log4jConfigListener.class);

        EnumSet<DispatcherType> disps = EnumSet.allOf(DispatcherType.class);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", CharacterEncodingFilter.class);
        encodingFilter.addMappingForUrlPatterns(disps, false, "/*");
        encodingFilter.setInitParameter("encoding", "UTF-8");
    }
}
