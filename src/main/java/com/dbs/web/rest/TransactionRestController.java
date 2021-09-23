package com.dbs.web.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Transaction;
import com.dbs.web.service.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionRestController {

	@Autowired
	private TransactionService transService;
	private Object customerRepository;
	private Object transactionRepository;

	@GetMapping
	public List<Transaction> findTransactions()
	{
		return this.transService.getTransactions();
	}

//	@PostMapping
//	public ResponseEntity<String> insertTransaction(@RequestBody Transaction transaction){
//		try {
//			if(this.transService.insertTransaction(transaction))
//				return  ResponseEntity
//						.status(HttpStatus.ACCEPTED)
//						.body("Transaction inserted with id "+transaction.getTransactionId());
//		}
//		catch(Exception e) {
//			return  ResponseEntity
//					.status(HttpStatus.NOT_FOUND)
//					.body("Transaction not inserted with id "+transaction.getTransactionId()+"\n\n\n"+e.getMessage());
//		}
//		return  ResponseEntity
//				.status(HttpStatus.NOT_FOUND)
//				.body("Transaction not inserted with id "+transaction.getTransactionId());
//	}
	
	@PostMapping("/transactions")
	public Transaction insertTransaction(@RequestBody Transaction transaction)
	{
		
		Customer customer = this.customerRepository.findById(transaction.getCustomer().getCustomerId()).get();
		boolean ofac = SearchFile.find(new File("src/main/resources/sdnlist.txt"), transaction.getReceiverAccountholderName());
		if(ofac)
			return null;
		double balance = customer.getClearBalance();
		balance = balance - (transaction.getInrAmount() + transaction.getTransferFees());
		customer.setClearBalance(balance);
		Customer receiver = this.customerRepository.findById(transaction.getReceiverAccountholderNumber()).get();
		double rbalance = receiver.getClearBalance();
		rbalance = rbalance + (transaction.getInrAmount() + transaction.getTransferFees());
		receiver.setClearBalance(rbalance);
		this.customerRepository.save(customer);
		this.customerRepository.save(receiver);
		this.transactionRepository.save(transaction); //change position of this if needed
		return transaction;
	}
	
	@GetMapping("/transactions/send/{cid}")
	public List<Transaction> getSentTransaction(@PathVariable String cid)
	{	
		List<Transaction> trans = new ArrayList<>();
		Optional<Customer> customer = this.customerRepository.findById(cid);
		if(customer.isPresent())
			this.transactionRepository.findByCustomer(customer.get()).forEach(tr -> trans.add(tr));
		
		return trans;	
	}

}


