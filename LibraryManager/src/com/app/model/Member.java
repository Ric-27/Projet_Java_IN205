package com.app.model;

/**
 * Class corresponding to the members of the database.
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
	
	/**
	 * Default constructor
	 */
	public Member() {
		this(0,"","","","","",Subscription.BASIC);
	}

	/**
	 * Constructor
	 * @param lastname
	 * @param name
	 * @param address
	 * @param email
	 * @param phone
	 * @param subs
	 */
	public Member( String lastname, String name, String address, String email, String phone, Subscription subs) {
		this.name = name;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.subscription = subs;
	}

	/**
	 * Constructor
	 * @param id
	 * @param lastname
	 * @param name
	 * @param address
	 * @param email
	 * @param phone
	 * @param subs
	 */
	public Member(Integer id, String lastname, String name, String address, String email, String phone, Subscription subs) {
		this(lastname,name,address,email,phone,subs);
		this.id = id;
	}
	
	/**
	 * Gets the id of the member in the database.
	 * @return id of the member in the database
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id of the member.
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name of the member.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the member
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gest the last name of the member
	 * @return last name of the member.
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the last name of the member.
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the address of the member.
	 * @return the adress of the member.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the member.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the email of the member.
	 * @return the email of the member.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the member.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the email of the member
	 * @return the email of the member
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone number of the member.
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the subscription of the member.
	 * @return
	 */
	public Subscription getSubscription() {
		return subscription;
	}

	/**
	 * Sets the subscription of the member.
	 * @param subs
	 */
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