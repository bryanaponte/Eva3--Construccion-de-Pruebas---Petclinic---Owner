package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	//@Test
	public void testFindById() {

		String NAME = "George";
		long ID = 1;
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		logger.info("" + owner);

		assertEquals(NAME, owner.getFirstname());

	}

	/**
	 * 
	 */
	//@Test
	public void testFindByFirstname() {

		String FIND_NAME = "Betty";
		int SIZE_EXPECTED = 2;

		List<Owner> owners = ownerService.findByFirstname(FIND_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
	}


	/**
	 * 
	 */
	//@Test
	public void testFindByLastname() {

		String LAST_NAME = "Escobita";
		int SIZE_EXPECTED = 8;

		List<Owner> owners = ownerService.findByLastname(LAST_NAME);

		assertEquals(SIZE_EXPECTED, owners.size());
	}
	
	
	/**
	 * 
	 */
	//@Test
	public void testFindByCity() {

		String CITY = "Madison";
		int SIZE_EXPECTED = 9;

		List<Owner> owners = ownerService.findByCity(CITY);

		assertEquals(SIZE_EXPECTED, owners.size());
	}

	/**
	 * 
	 */
	@Test
	public void testCreateOwnerandFound() {

		String OWNER_FIRSTNAME = "Maria";
		String OWNER_LASTNAME = "Escobito";
		String OWNER_ADDRESS = "345 Maple St.";
		String OWNER_CITY = "Madison";
		String OWNER_TELEPHONE = "6085557683";		

		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME,
				OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
		
		owner = ownerService.create(owner);
		
		try {
			Owner ownerCreatedFound = ownerService.findById(owner.getId());
			logger.info("Owner id: "+ ownerCreatedFound.getId() + " owner created.");
		}catch (OwnerNotFoundException e) {
			logger.info("Owner no creado");
		}
		
		
		Iterable<Owner> ownersFound = ownerService.findAll();
		
		
		while(ownersFound.iterator().hasNext()) {
			try {
				Owner ownerInListFound = ownerService.findById(owner.getId());
				logger.info("Owner: "+ ownerInListFound.getId() + "si existe en la bd.");
				break;
			}catch (OwnerNotFoundException e) {
				logger.info("Owner no existe en la bd");
			}
		}
				
	}
	
}