package fr.riot.ap.model;

import fr.riot.ap.utils.ChoosableEnum;

abstract public class Account {
	public enum Devise implements ChoosableEnum {
		EUR("Euro", "€"),
		USD("United States Dollar", "$");

		private final String displayValue;
		private final String symbol;
		Devise(String displayValue, String symbol) {
			this.displayValue = displayValue;
			this.symbol = symbol;
		}

		@Override
		public String getDisplayValue() {
			return this.displayValue;
		}
		public String getSymbol() {
			return this.symbol;
		}
	}

	protected int id;
	protected float balance;
	protected Devise devise;
	protected Client owner;
	
	public Account(int id, float balance, Devise devise, Client owner) {
		this.id = id;
		this.balance = balance;
		this.devise = devise;
		this.owner = owner;
	}

	public abstract int getId(boolean parent);
	public float getBalance() {
		return balance;
	}
	public Devise getDevise() {
		return devise;
	}
	public Client getOwner() {
		return owner;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public void setDevise(Devise devise) {
		this.devise = devise;
	}
	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public abstract String getFormattedType();

	public String formatAccount() {
		return id + " - Type : " + getFormattedType() + " - Solde : " + balance + devise.getSymbol();
	}

}
