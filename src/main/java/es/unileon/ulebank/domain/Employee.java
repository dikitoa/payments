package es.unileon.ulebank.domain;

// Generated Jun 15, 2014 6:36:38 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEE", catalog = "ULEBANK_FINAL")
public class Employee implements java.io.Serializable {

	private String id;
	private Offices offices;
	private GenericHandler genericHandler;
	private String name;
	private String surnames;
	private double salary;
	private String address;
	private Set<Buyable> buyables = new HashSet<Buyable>(0);

	public Employee() {
	}

	public Employee(Offices offices, GenericHandler genericHandler,
			String name, String surnames, double salary, String address) {
		this.offices = offices;
		this.genericHandler = genericHandler;
		this.name = name;
		this.surnames = surnames;
		this.salary = salary;
		this.address = address;
	}

	public Employee(Offices offices, GenericHandler genericHandler,
			String name, String surnames, double salary, String address,
			Set<Buyable> buyables) {
		this.offices = offices;
		this.genericHandler = genericHandler;
		this.name = name;
		this.surnames = surnames;
		this.salary = salary;
		this.address = address;
		this.buyables = buyables;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "genericHandler"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "office_id", nullable = false)
	public Offices getOffices() {
		return this.offices;
	}

	public void setOffices(Offices offices) {
		this.offices = offices;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public GenericHandler getGenericHandler() {
		return this.genericHandler;
	}

	public void setGenericHandler(GenericHandler genericHandler) {
		this.genericHandler = genericHandler;
	}

	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surnames", nullable = false, length = 128)
	public String getSurnames() {
		return this.surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	@Column(name = "salary", nullable = false, precision = 22, scale = 0)
	public double getSalary() {
		return this.salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Column(name = "address", nullable = false, length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Buyable> getBuyables() {
		return this.buyables;
	}

	public void setBuyables(Set<Buyable> buyables) {
		this.buyables = buyables;
	}

}