package com.dbs.web.beans;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	private int transactionid;
	@OneToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	@OneToOne
	@JoinColumn(name="currencycode")
	private Currency currency;
	@OneToOne
	@JoinColumn(name="senderBIC")
	private Bank senderBank;
	@OneToOne
	@JoinColumn(name="receiverBIC")
	private Bank receiverBank;
	private String receiveraccountholdernumber;
	private String receiveraccountholdername;
	@OneToOne
	@JoinColumn(name="transfertypecode")
	private Transfertypes transfertype;
	@OneToOne
	@JoinColumn(name="messagecode")
	private Message message;
	private double currencyamount;
	private double transferfees;
	private double inramount;
	private LocalDate transferdate;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int transactionId, Customer customer, Currency currency, Bank senderBank, Bank receiverBank,
			String receiveraccountholdernumber, String receiverAccountHolderName, Transfertypes transferType, Message message,
			double currencyAmount, double transferFees, double inrAmount, LocalDate transferDate) {
		super();
		this.transactionid = transactionId;
		this.customer = customer;
		this.currency = currency;
		this.senderBank = senderBank;
		this.receiverBank = receiverBank;
		this.receiveraccountholdernumber = receiveraccountholdernumber;
		this.receiveraccountholdername = receiverAccountHolderName;
		this.transfertype = transferType;
		this.message = message;
		this.currencyamount = currencyAmount;
		this.transferfees = transferFees;
		this.inramount = inrAmount;
		this.transferdate = transferDate;
	}
	public int getTransactionId() {
		return transactionid;
	}
	public void setTransactionId(int transactionId) {
		this.transactionid = transactionId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Bank getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(Bank senderBank) {
		this.senderBank = senderBank;
	}
	public Bank getReceiverBIC() {
		return receiverBank;
	}
	public void setReceiverBIC(Bank receiverBank) {
		this.receiverBank = receiverBank;
	}
	public String getreceiveraccountholdernumber() {
		return receiveraccountholdernumber;
	}
	public void setreceiveraccountholdernumber(String receiveraccountholdernumber) {
		this.receiveraccountholdernumber = receiveraccountholdernumber;
	}
	public String getReceiverAccountHolderName() {
		return receiveraccountholdername;
	}
	public void setReceiverAccountHolderName(String receiverAccountHolderName) {
		this.receiveraccountholdername = receiverAccountHolderName;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Transfertypes getTransfertype() {
		return transfertype;
	}
	public void setTransfertype(Transfertypes transfertype) {
		this.transfertype = transfertype;
	}
	public double getCurrencyamount() {
		return currencyamount;
	}
	public void setCurrencyamount(double currencyamount) {
		this.currencyamount = currencyamount;
	}
	public double getTransferfees() {
		return transferfees;
	}
	public void setTransferfees(double transferfees) {
		this.transferfees = transferfees;
	}
	public double getInramount() {
		return inramount;
	}
	public void setInramount(double inramount) {
		this.inramount = inramount;
	}
	public LocalDate getTransferdate() {
		return transferdate;
	}
	public void setTransferdate(LocalDate transferdate) {
		this.transferdate = transferdate;
	}
		
}
