package es.unileon.ulebank.domain;

// Generated Jun 15, 2014 6:36:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Offices generated by hbm2java
 */
@Entity
@Table(name = "OFFICES", catalog = "ULEBANK_FINAL")
public class Offices implements java.io.Serializable {

	private String officeId;
	private String bankId;
	private int localCost;
	private int utlitiesCost;
	private int employeCost;
	private int totalExpenses;
	private int totalIncome;
	private int balance;
	private String accountNumber;
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Offices() {
	}

	public Offices(String officeId, String bankId, int localCost,
			int utlitiesCost, int employeCost, int totalExpenses,
			int totalIncome, int balance, String accountNumber) {
		this.officeId = officeId;
		this.bankId = bankId;
		this.localCost = localCost;
		this.utlitiesCost = utlitiesCost;
		this.employeCost = employeCost;
		this.totalExpenses = totalExpenses;
		this.totalIncome = totalIncome;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	public Offices(String officeId, String bankId, int localCost,
			int utlitiesCost, int employeCost, int totalExpenses,
			int totalIncome, int balance, String accountNumber,
			Set<Employee> employees) {
		this.officeId = officeId;
		this.bankId = bankId;
		this.localCost = localCost;
		this.utlitiesCost = utlitiesCost;
		this.employeCost = employeCost;
		this.totalExpenses = totalExpenses;
		this.totalIncome = totalIncome;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.employees = employees;
	}

	@Id
	@Column(name = "office_id", unique = true, nullable = false, length = 64)
	public String getOfficeId() {
		return this.officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	@Column(name = "bank_id", nullable = false, length = 64)
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "local_cost", nullable = false)
	public int getLocalCost() {
		return this.localCost;
	}

	public void setLocalCost(int localCost) {
		this.localCost = localCost;
	}

	@Column(name = "utlities_cost", nullable = false)
	public int getUtlitiesCost() {
		return this.utlitiesCost;
	}

	public void setUtlitiesCost(int utlitiesCost) {
		this.utlitiesCost = utlitiesCost;
	}

	@Column(name = "employe_cost", nullable = false)
	public int getEmployeCost() {
		return this.employeCost;
	}

	public void setEmployeCost(int employeCost) {
		this.employeCost = employeCost;
	}

	@Column(name = "total_expenses", nullable = false)
	public int getTotalExpenses() {
		return this.totalExpenses;
	}

	public void setTotalExpenses(int totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	@Column(name = "total_income", nullable = false)
	public int getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Column(name = "balance", nullable = false)
	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Column(name = "account_number", nullable = false, length = 64)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "offices")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Object searchClient(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
