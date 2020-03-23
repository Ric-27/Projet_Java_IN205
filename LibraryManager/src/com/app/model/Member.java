package com.app.model;

/**
 * Member
 */
public class Member {
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

		public int getIndex(){
			return this.index;
		}
    }
    private Subscription subscription;
	
	
	public Member() {
		this(0,"","","","","",Subscription.BASIC);
	}
	public Member( String lastname, String name, String address, String email, String phone, Subscription subs) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.subscription = subs;
	}
	public Member(Integer id, String lastname, String name, String address, String email, String phone, Subscription subs) {
		this(lastname,name,address,email,phone,subs);
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
	public Subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(Subscription subs) {
		this.subscription = subs;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
				+ "Name:" + name + ", "
				+ "Lastname: " + lastname
				+ "Address:" + address + ", "
				+ "Email:" + email + ", "
				+ "Phone:" + phone + ", "
				+ "Subscription:" + subscription
				+ "}";
	}    
}