package com.app.model;

/**
 * lending
 */
public class lending {
    private Integer id;
	private member member;
    private book book;
    private java.time.LocalDate lendDate;
    private java.time.LocalDate returnDate;	
	
	public lending() {
		this(0,null,null,null,null);
	}
	public lending(member member, book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this.member = member;
		this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
	}
	public lending(Integer id, member member, book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this(member,book,lendDate, returnDate);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public book getBook() {
		return book;
	}
	public void setBook(book book) {
		this.book = book;
	}
	public member getMember() {
		return member;
	}
	public void setMember(member member) {
		this.member = member;
	}
	public java.time.LocalDate getLendDate() {
		return lendDate;
	}
	public void setLendDate(java.time.LocalDate lendDate) {
		this.lendDate = lendDate;
    }
    public java.time.LocalDate getReturnDate() {
		return returnDate;
	}
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