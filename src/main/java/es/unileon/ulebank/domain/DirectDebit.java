package es.unileon.ulebank.domain;

// Generated Jun 15, 2014 6:36:38 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * DirectDebit generated by hbm2java
 */
@Entity
@Table(name = "DIRECT_DEBIT", catalog = "ULEBANK_FINAL")
public class DirectDebit implements java.io.Serializable {

	private String id;
	private GenericHandler genericHandler;
	private AccountDirectDebits accountDirectDebits;

	public DirectDebit() {
	}

	public DirectDebit(GenericHandler genericHandler,
			AccountDirectDebits accountDirectDebits) {
		this.genericHandler = genericHandler;
		this.accountDirectDebits = accountDirectDebits;
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

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public GenericHandler getGenericHandler() {
		return this.genericHandler;
	}

	public void setGenericHandler(GenericHandler genericHandler) {
		this.genericHandler = genericHandler;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_direct_debit_id", nullable = false)
	public AccountDirectDebits getAccountDirectDebits() {
		return this.accountDirectDebits;
	}

	public void setAccountDirectDebits(AccountDirectDebits accountDirectDebits) {
		this.accountDirectDebits = accountDirectDebits;
	}

}
