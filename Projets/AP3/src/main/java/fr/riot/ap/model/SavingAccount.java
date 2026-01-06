package fr.riot.ap.model;

public class SavingAccount extends Account {
	private int id;
	private float interest;

	public SavingAccount(float balance, Devise devise, Client owner, float interest) {
		this(-1, -1, balance, devise, owner, interest);
	}
	public SavingAccount(int id, int accountId, float balance, Devise devise, Client owner, float interest) {
		super(accountId, balance, devise, owner);

		this.id = id;
		this.interest = interest;
	}

	public int getId(boolean parent) {
		if (parent)
			return super.id;

		return this.id;
	}
	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	@Override
	public String getFormattedType() {
		return "Compte Epargne";
	}
}
