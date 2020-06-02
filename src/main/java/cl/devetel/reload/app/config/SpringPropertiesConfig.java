package cl.devetel.reload.app.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import cl.devetel.reload.app.config.models.repo.PropertyRepo;

@Configuration
@PropertySource(value = { "classpath:application.properties" }, ignoreResourceNotFound = true)
public class SpringPropertiesConfig {

	private static final Logger log = LoggerFactory.getLogger(SpringPropertiesConfig.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private PropertyRepo repo;
	
	@PostConstruct
	public void init() {
		MutablePropertySources propertySources = ((ConfigurableEnvironment) env).getPropertySources();

        try {
        	Properties dbProps =  new Properties();
        	repo.findAll().forEach( p -> dbProps.setProperty(p.getPropiedad(), p.getValor()) );
            PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", dbProps);
            propertySources.addFirst(dbPropertySource);
        } catch (Exception e) {
            log.error("Error during database properties setup", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
}
