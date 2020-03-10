package com.app.model;

/**
 * member
 */
public class member {
    private Integer id;
	private String lastname;
    private String name;
    private String address;
    private String email;
    private String phone;
    public enum Subscription{
        BASIC(0),PREMIUM(1) , VIP(2);
        private int index;
        private Subscription(int index){
            this.index = index;
        }
    }
    private Subscription subscription;
	
	
	public member() {
		this(0,"","","","","");
	}
	public member(String name, String lastname, String address, String email, String phone) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	public member(Integer id, String name, String lastname, String address, String email, String phone) {
		this(name,lastname,address,email,phone);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
				+ "Name:" + name + ", "
				+ "Lastname: " + lastname
				+ "Address:" + address + ", "
				+ "Email:" + email + ", "
				+ "Phone:" + phone
				+ "}";
	}
    
}