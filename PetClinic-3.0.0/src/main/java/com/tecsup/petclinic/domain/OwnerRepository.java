package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>{
	

		List<Owner> findByFirstname(String name);


		List<Owner> findByLastname(String lastname);

 
		List<Owner> findByCity(String city);
}
