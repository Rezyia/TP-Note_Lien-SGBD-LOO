package modele;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TableNumero {
	
	@Id
	private Integer numero;
	

	/**
	 * @param numero
	 */
	public TableNumero(Integer numero) {
		this.numero = numero;
	}

	
	public Integer getNumero() {
		return numero;
	}
	
	protected void setNumero(Integer numero) {
		this.numero = numero;
	}

}
