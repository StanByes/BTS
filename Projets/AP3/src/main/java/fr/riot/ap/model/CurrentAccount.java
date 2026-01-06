package fr.riot.ap.model;


import java.sql.Date;
import java.time.YearMonth;

public class CurrentAccount extends Account {
	private int id;
	private int cardNumber;
	private Date expiration;
	private int cvv;
	private int overdraft;

	public CurrentAccount(float balance, Devise devise, Client owner, int cardNumber, YearMonth expiration, int cvv, int overdraft) {
		this(-1, -1, balance, devise, owner, cardNumber, expiration, cvv, overdraft);
	}
	public CurrentAccount(int id, int accountId, float balance, Devise devise, Client owner, int cardNumber, YearMonth expiration, int cvv, int overdraft) {
		this(id, accountId, balance, devise, owner, cardNumber, parseYearMonthExpiration(expiration), cvv, overdraft);
	}
	public CurrentAccount(int id, int accountId, float balance, Devise devise, Client owner, int cardNumber, Date expiration, int cvv, int overdraft) {
		super(accountId, balance, devise, owner);

		this.id = id;
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.cvv = cvv;
		this.overdraft = overdraft;
	}

	public int getId(boolean parent) {
		if (parent)
			return super.id;

		return this.id;
	}
	public int getCardNumber() {
		return this.cardNumber;
	}
	public Date getExpiration() {
		return this.expiration;
	}
	public int getCVV() {
		return cvv;
	}
	public int getOverdraft() {
		return this.overdraft;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setExpiration(YearMonth expiration) {
		this.expiration = parseYearMonthExpiration(expiration);
	}
	public void setCVV(int cvv) {
		this.cvv = cvv;
	}
	public void setOverdraft(int overdraft) {
		this.overdraft = overdraft;
	}

	private static Date parseYearMonthExpiration(YearMonth yearMonth) {
		return Date.valueOf(yearMonth.atDay(1));
	}

	@Override
	public String getFormattedType() {
		return "Compte Courant";
	}
}
