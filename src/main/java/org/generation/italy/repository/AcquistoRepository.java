package org.generation.italy.repository;

import org.generation.italy.model.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
	@Query(value ="select sum(ap.quantita* ap.prezzo_acquisto)\r\n"
			+ "from acquisto_prodotto ap \r\n"
			+ "join acquisto a \r\n"
			+ "on ap.acquisto_id = a.id \r\n"
			+ "where MONTH(a.data_acquisto) = MONTH(now())  and YEAR(a.data_acquisto) = YEAR(now())", 
			  nativeQuery = true)
	public  Integer getTotaleAcquisti();

}
