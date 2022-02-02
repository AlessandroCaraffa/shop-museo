package org.generation.italy.repository;

import java.util.List;


import org.generation.italy.model.VenditaProdotto;
import org.generation.italy.model.VenditaProdottoForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaProdottoRepository extends JpaRepository<VenditaProdotto, Integer> {

	List<VenditaProdotto> findByVenditaId(Integer id);
	
	List<VenditaProdotto> findByProdottoId(Integer id);
	
//	@Query(value ="select sum(vp.quantita),vp.prodotto_id\r\n"
//			+ "from vendita_prodotto vp \r\n"
//			+ "group by vp.prodotto_id \r\n"
//			+ "order by sum(vp.quantita)\r\n"
//			+ "limit 2", 
//			  nativeQuery = true)
//	public List<VenditaProdottoForm> getTop5();

}
