package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.VenditaProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaProdottoRepository extends JpaRepository<VenditaProdotto, Integer> {

	List<VenditaProdotto> findByVenditaId(Integer id);
	
	@Query(value ="select sum(vp.quantita) as totale,p.nome, v.data_vendita \r\n"
			+ "from prodotto p \r\n"
			+ "join vendita_prodotto vp on p.id = vp.prodotto_id \r\n"
			+ "join vendita v on vp.vendita_id = v.id \r\n"
			+ "group by p.id \r\n"
			+ "order by totale \r\n"
			+ "limit 5 ", 
			  nativeQuery = true)
	public List<VenditaProdotto> getTop5();

}
