package cl.devetel.reload.app.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.devetel.reload.app.config.models.repo.PropertyRepo;

@RestController
@RequestMapping("/api/v1/properties/")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private PropertyRepo repo;
	
	@Autowired
	private Environment env;
	
	@GetMapping("test/")
	public List<String> getTestProperties(){
		log.info("... Executing test controller ...");
		String custom = env.getProperty("app.properties.custom");
		String random = env.getProperty("app.properties.random");
		return Arrays.asList( custom, random);
	}
	
	@GetMapping("reload/")
	public boolean reloadAllProperties() {
		log.info("... Reload all properties from database ...");
		MutablePropertySources propertySources = ((ConfigurableEnvironment) env).getPropertySources();
        try {
        	Properties dbProps =  new Properties();
        	repo.findAll().forEach( p -> dbProps.setProperty(p.getPropiedad(), p.getValor()) );
            PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", dbProps);
            propertySources.addFirst(dbPropertySource);
            return true;
        } catch (Exception e) {
            log.error("Error during database properties setup", e);
            throw new RuntimeException(e);
        }
	}
}
