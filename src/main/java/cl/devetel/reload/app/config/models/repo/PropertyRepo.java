package cl.devetel.reload.app.config.models.repo;

import org.springframework.data.repository.CrudRepository;

import cl.devetel.reload.app.config.models.entity.Property;

public interface PropertyRepo extends CrudRepository<Property, String>{

}
