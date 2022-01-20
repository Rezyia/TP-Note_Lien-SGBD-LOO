package app;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modele.*;

public class BDD {
	
	
	/**
	 * Drop les tables si elles existent déjà puis les recrée et insert des exemples dans la BDD.
	 */
	public BDD() {
		dbTuplesInit();
	}
	
	
	/**
	 * Insert les tuples dans les tables de la BDD
	 * @throws SQLException
	 */
	public void dbTuplesInit() {
		List<Etudiant> EtutoPersist = new ArrayList<>();
		List<Enseignant> EsgatoPersist = new ArrayList<>();
		List<Enseignement> EsgetoPersist = new ArrayList<>();
		List<Bourse> BoutoPersist = new ArrayList<>();
		List<Candidature> CandtoPersist = new ArrayList<>();
		List<Plan> PlatoPersist = new ArrayList<>();
		
		List<List> ListsToPersist = new ArrayList<List>() {{
			add(EtutoPersist);
			add(EsgatoPersist);
			add(EsgetoPersist);
			add(BoutoPersist);
			add(CandtoPersist);
			add(PlatoPersist);
			}
		};
		
		EtutoPersist.add(new Etudiant("Boulanger", "Léo", 15.66));
		EtutoPersist.add(new Etudiant("Boisseau", "Théo", 20.00));
		
		EsgatoPersist.add(new Enseignant("Conte", "Donatello"));
		EsgatoPersist.add(new Enseignant("Ragot", "Nicolas"));
		EsgatoPersist.add(new Enseignant("Monmarché", "Nicolas"));
		EsgatoPersist.add(new Enseignant("Motoaki", "Tanigo"));
		
		EsgetoPersist.add(new Enseignement("Informatique", 4, 2000));
		EsgetoPersist.add(new Enseignement("Électronique et génie électrique", 2, 1900));
		EsgetoPersist.add(new Enseignement("Génie de l aménagement et de l environnement", 1, 1800));
		EsgetoPersist.add(new Enseignement("Mécanique et conception des systèmes", 3, 2000));
		
		BoutoPersist.add(new Bourse(EsgatoPersist.get(1), "Montréal", 29));
		BoutoPersist.add(new Bourse(EsgatoPersist.get(2), "Akita", 42));
		
		CandtoPersist.add(new Candidature(EtutoPersist.get(0), BoutoPersist.get(0), EsgatoPersist.get(1), EsgatoPersist.get(3), null, null));
		CandtoPersist.add(new Candidature(EtutoPersist.get(0), BoutoPersist.get(1), EsgatoPersist.get(1), EsgatoPersist.get(3), null, null));
		CandtoPersist.add(new Candidature(EtutoPersist.get(1), BoutoPersist.get(1), null, null, null, null));
		
		PlatoPersist.add(new Plan(CandtoPersist.get(0), EsgetoPersist.get(0)));
		PlatoPersist.add(new Plan(CandtoPersist.get(0), EsgetoPersist.get(3)));
		PlatoPersist.add(new Plan(CandtoPersist.get(1), EsgetoPersist.get(0)));
		PlatoPersist.add(new Plan(CandtoPersist.get(1), EsgetoPersist.get(1)));
		PlatoPersist.add(new Plan(CandtoPersist.get(1), EsgetoPersist.get(2)));
		PlatoPersist.add(new Plan(CandtoPersist.get(1), EsgetoPersist.get(3)));
		
		try {
			App.em.getTransaction().begin();
			for (List toPersist : ListsToPersist) {
				for (Object obj : toPersist) {
					App.em.persist(obj);
				}
			}
			App.em.getTransaction().commit();
		} catch (Exception e) {
			App.em.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
}