package modele;

public abstract class TableWithId {
	
	private Integer id;
	

	/**
	 * @param id
	 */
	public TableWithId(Integer id) {
		this.id = id;
	}

	
	public Integer getId() {
		return id;
	}
	
	protected void setId(Integer id) {
		this.id = id;
	}

}
