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
		super();
	}
	public member(String name, String lastname, String address, String aegEmail, String argPhone) {
		this();
		this.titre = titre;
		this.realisateur = realisateur;
	}
	public member(Integer id, String titre, String realisateur) {
		this(titre, realisateur);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
				+ "titre:" + titre + ", "
				+ "realisateur: " + realisateur
				+ "}";
	}
    
}