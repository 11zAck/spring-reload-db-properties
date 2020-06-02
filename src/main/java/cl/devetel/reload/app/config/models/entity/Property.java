package cl.devetel.reload.app.config.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dvt__tp_params_web")
public class Property {

	@Id
	private String propiedad;
	private String valor;

	public String getPropiedad() {
		return propiedad;
	}

	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Property [propiedad=" + propiedad + ", valor=" + valor + "]";
	}

}
