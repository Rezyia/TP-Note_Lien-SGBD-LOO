package dao;

import java.util.Iterator;
import java.util.List;

import modele.TableWithId;

public abstract class ToolBox {

	public static Object getObject(Object liste, Integer id) {
		TableWithId elem = null;
		boolean fin = false;
		
		Iterator<TableWithId> ite = ((List<TableWithId>) liste).iterator();
		while (ite.hasNext() && !fin) {
			elem = ite.next();
			if (elem.getId() == id) fin = true;
		}
		if (fin == false) elem = null;
		
		return elem;
	}
	
}
