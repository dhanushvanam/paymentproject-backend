package com.dbs.web.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.lang.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Transaction;
import com.dbs.web.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private CustomerService custService;
	
	public List<Transaction> getTransactions()
	{
		List<Transaction> transactions = new ArrayList<Transaction>();
		this.transactionRepo.findAll().forEach(trans->transactions.add(trans));
		return transactions;
	}
	
	public boolean insertTransaction(Transaction trans)
	{
//		if(this.transactionRepo.findById(trans.getTransactionId()).isPresent())
//			return false;
		System.out.println("trans insert entered"+ trans);
		try {
			Customer c=trans.getCustomer();
			double transferfees=trans.getInramount()*0.25;
			double totalamount=trans.getInramount()+transferfees;
			System.out.println(c.getAccountholdername()+"\t"+c.isOverdraftflag());
			if(c.isOverdraftflag())
			{
				trans.setTransferfees(transferfees);
				trans.setTransferdate(LocalDate.now());
				c.setClearbalance(c.getClearbalance()-totalamount);
				this.custService.updateCustomer(c);
				this.transactionRepo.save(trans);
			}
			else if(totalamount<=c.getClearbalance())
			{
				trans.setTransferfees(transferfees);
				trans.setTransferdate(LocalDate.now());
				c.setClearbalance(c.getClearbalance()-totalamount);
				this.custService.updateCustomer(c);
				this.transactionRepo.save(trans);
			}
			else {
				System.out.println("In else pf trest controller");
				return false;
			}
		}catch(IllegalArgumentException e )
		{
//			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	

}
