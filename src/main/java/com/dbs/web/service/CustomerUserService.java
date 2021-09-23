package com.dbs.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customeruser;
import com.dbs.web.repository.CustomerUserRepository;

@Service
public class CustomerUserService {

	@Autowired
	private CustomerUserRepository repo;

	public Customeruser getUserbyID(int id) {
		try {
			Optional<Customeruser> custuser = this.repo.findById(id);

			return custuser.orElseThrow(()->{

				return new EntityNotFoundException("Customer with user "+id + " does not exist");
			});

		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}
	
	public List<Customeruser> getCustomerUsers(){
		List<Customeruser> customerusers=new ArrayList<Customeruser>();
		this.repo.findAll().forEach(cust->customerusers.add(cust));
		return customerusers;
	}

}
