package fr.riot.ap.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client {
	public enum Genre {
		MAN,
		WOMAN,
		OTHER,
		UNKNOWN;

		public static Genre getByDatabaseValue(int value) {
            return switch (value) {
                case 1 -> MAN;
                case 2 -> WOMAN;
                case 3 -> OTHER;
                default -> UNKNOWN;
            };
		}

		public static int getByEnumValue(Genre genre) {
			return switch (genre) {
				case MAN -> 1;
				case WOMAN -> 2;
				case OTHER -> 3;
				default -> 4;
			};
		}
	}

	private int id;
	private String name;
	private String firstname;
	private Genre genre;
	private Date birthdate;
	private String category;
	private String address;
	private String phone;
	private String email;
	private final List<Account> accounts = new ArrayList<>();

	public Client(int id, String name, String firstname, Genre genre,
				  Date birthdate, String category, String address, String phone, String email) {
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.genre = genre;
		this.birthdate = birthdate;
		this.category = category;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public Genre getGenre() {
		return genre;
	}
	public Date getBirthdate() {
		return this.birthdate;
	}
	public String getCategory() {
		return this.category;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getEmail() {
		return email;
	}
	public List<Account> getAccounts() {
		return this.accounts;
	}
	public String getCompleteName() {
		return this.name + " " + this.firstname;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}


}
