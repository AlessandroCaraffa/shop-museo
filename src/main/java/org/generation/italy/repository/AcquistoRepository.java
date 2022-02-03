package org.generation.italy.repository;

import org.generation.italy.model.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
	@Query(value ="select coalesce(sum(ap.quantita* ap.prezzo_acquisto),0)\r\n"
			+ "from acquisto_prodotto ap \r\n"
			+ "join acquisto a \r\n"
			+ "on ap.acquisto_id = a.id \r\n"
			+ "where a.data_acquisto > date_sub(CURDATE() ,interval 1 month)", 
			  nativeQuery = true)
	public  Integer getTotaleAcquisti();

}
