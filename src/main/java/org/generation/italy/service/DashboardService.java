package org.generation.italy.service;

import org.generation.italy.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	
	@Autowired
	private DashboardRepository repository;

}
