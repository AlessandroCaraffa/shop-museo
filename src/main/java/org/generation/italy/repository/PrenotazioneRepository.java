package org.generation.italy.repository;

import org.generation.italy.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
	
	
	
//	@Query("select sum(p2.quantita * p.prezzo)  \r\n"
//	+ "from percorso p \r\n"
//	+ "join visita v \r\n"
//	+ "on v.percorso_id = p.id \r\n"
//	+ "join prenotazione p2 \r\n"
//	+ "on p2.visita_id =v.id ")
//	public  Integer getTotalePrenotazioni();
	
	@Query(value ="select sum(p2.quantita * p.prezzo)  \r\n"
			+ "from visita v\r\n"
			+ "join  percorso p \r\n"
			+ "on v.percorso_id = p.id \r\n"
			+ "join prenotazione p2 \r\n"
			+ "on p2.visita_id =v.id \r\n"
			+ "where MONTH(v.`data`) = MONTH(now())  and YEAR(v.`data`) = YEAR(now())", 
			  nativeQuery = true)
	public  Integer getTotalePrenotazioni();

}
