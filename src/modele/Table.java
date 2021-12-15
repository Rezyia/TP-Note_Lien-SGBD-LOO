package modele;

public abstract class Table {
	
	private Integer id;
	

	/**
	 * @param id
	 */
	public Table(Integer id) {
		this.id = id;
	}

	
	public Integer getId() {
		return id;
	}
	
	protected void setId(Integer id) {
		this.id = id;
	}

}
