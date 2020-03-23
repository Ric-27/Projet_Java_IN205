package com.app.model;

/**
 * Class corresponding to the loans of the database.
 */
public class Lending {
    private Integer id;
	private Member member;
    private Book book;
    private java.time.LocalDate lendDate;
    private java.time.LocalDate returnDate;	
	

	/**
	 * Default constructor
	 */
	public Lending() {
		this(0,null,null,null,null);
	}

	/**
	 * Constructor
	 * @param member
	 * @param book
	 * @param lendDate
	 * @param returnDate
	 */
	public Lending(Member member, Book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this.member = member;
		this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
	}

	/**
	 * Constructor
	 * @param id
	 * @param member
	 * @param book
	 * @param lendDate
	 * @param returnDate
	 */
	public Lending(Integer id, Member member, Book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this(member,book,lendDate, returnDate);
		this.id = id;
	}
	
	/**
	 * Gets the id of the loan.
	 * @return the id of the loan.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id of the loan.
	 * @param id of the loan.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the book of the loan.
	 * @return book of the loan.
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Sets the book of the loans
	 * @param book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Gets the member of the loan.
	 * @return member of the loan.
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Sets the member of the loan.
	 * @param member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets the lending date of the loan.
	 * @return the lending date of the loan.
	 */
	public java.time.LocalDate getLendDate() {
		return lendDate;
	}

	/**
	 * Sets the lendind date of the loan.
	 * @param lendDate
	 */
	public void setLendDate(java.time.LocalDate lendDate) {
		this.lendDate = lendDate;
	}
	
	/**
	 * Gets the return date of the book lent.
	 * @return the return date of the book lent.
	 */
    public java.time.LocalDate getReturnDate() {
		return returnDate;
	}

	/**
	 * Sets the return date of the book lent
	 * @param returnDate
	 */
	public void setReturnDate(java.time.LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
				+ "Book:" + book + ", "
				+ "Member: " + member + ", "
                + "Lend Date:" + lendDate + ", "
                + "Return Date:" + lendDate
				+ "}";
	}    
}