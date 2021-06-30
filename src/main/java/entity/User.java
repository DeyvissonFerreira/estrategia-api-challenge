package entity;

public class User {
	
	public int id;
	public String name, email, gender, status, created_at, updated_at;
	
	public User(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}

	public User() {
	}
}