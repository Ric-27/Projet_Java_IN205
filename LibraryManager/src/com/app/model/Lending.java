package com.app.model;

/**
 * Lending
 */
public class Lending {
    private Integer id;
	private Member member;
    private Book book;
    private java.time.LocalDate lendDate;
    private java.time.LocalDate returnDate;	
	
	public Lending() {
		this(0,null,null,null,null);
	}
	public Lending(Member member, Book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this.member = member;
		this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
	}
	public Lending(Integer id, Member member, Book book, java.time.LocalDate lendDate,java.time.LocalDate returnDate) {
		this(member,book,lendDate, returnDate);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
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