package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Bank;

public interface BankRepository extends CrudRepository<Bank, String >{

}
