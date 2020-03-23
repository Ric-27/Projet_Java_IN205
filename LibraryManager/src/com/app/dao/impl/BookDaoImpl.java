package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.*;
import com.app.exception.*;
import com.app.model.Book;
import com.app.persistence.ConnectionManager;

public class BookDaoImpl implements BookDao{

    private static BookDaoImpl instance;
	private BookDaoImpl() { }	
	public static BookDao getInstance() {
		if(instance == null) {
			instance = new BookDaoImpl();
		}
		return instance;
	}

	private static final String SELECT_ALL_QUERY = "SELECT * from livre;";
	private static final String SELECT_BY_ID_QUERY = "SELECT * from livre WHERE id = ?;";
	private static final String CREATE_QUERY = "INSERT INTO livre (titre,auteur,isbn) VALUES (?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE livre SET titre=?, auteur=?,isbn=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM livre WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM livre;";
    
	@Override
	public List<Book> getList() throws DaoException{
		List<Book> books = new ArrayList<Book>();
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet result = preparedStatement.executeQuery();) 
		{
			while(result.next()){
				books.add(new Book(result.getInt("id"),result.getString("titre"),result.getString("auteur"),result.getString("isbn")));
			}
        } catch (SQLException e) {
            throw new DaoException("Error Downloading List of Books");
		}
		return books;
	}

	public ResultSet prepareGetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException{
		preparedStatement.setInt(1,id);
		return preparedStatement.executeQuery();
	}

	@Override
	public Book getById(int id) throws DaoException{
		Book book = new Book();
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ResultSet result = prepareGetByIdStatement(preparedStatement, id);) 
		{
            if (result.next()) {
				book.setId(result.getInt("id"));
				book.setTitle(result.getString("titre"));
				book.setAuthor(result.getString("auteur"));
				book.setIsbn(result.getString("isbn"));
			}
        } catch (SQLException e) {
            throw new DaoException("Error downloading Books with id:" + id);
		}
		return book;
	}

	public ResultSet prepareCreateStatement(PreparedStatement preparedStatement, String title, String author, String isbn) throws SQLException{
		ResultSet result;
		preparedStatement.setString(1,title);
		preparedStatement.setString(2,author);
		preparedStatement.setString(3,isbn);
		preparedStatement.executeUpdate();
		result = preparedStatement.getGeneratedKeys();
		return result;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws DaoException{
		int id = -1;
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = prepareCreateStatement(preparedStatement, titre, auteur, isbn);) 
		{
            if (result.next()) {
				id = result.getInt("id");
			}
        } catch (SQLException e) {
            throw new DaoException("Error Creating Book");
		}
		return id;
	}

	public void prepareUpdateStatement(PreparedStatement preparedStatement, String title, String author, String isbn, int id) throws SQLException{
		preparedStatement.setString(1,title);
		preparedStatement.setString(2,author);
		preparedStatement.setString(3,isbn);
		preparedStatement.setInt(4,id);
		preparedStatement.executeUpdate();
	}

	@Override
	public void update(Book book) throws DaoException{
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);)
		{
            prepareUpdateStatement(preparedStatement, book.getTitle(), book.getAuthor(),book.getIsbn(), book.getId());
        } catch (SQLException e) {
            throw new DaoException("Error Updating Book of id: " + book.getId());
		}
	}

	public void prepareDeleteStatement(PreparedStatement preparedStatement, int id) throws SQLException{
		preparedStatement.setInt(1,id);
		preparedStatement.executeUpdate();
	}

	@Override
	public void delete(int id) throws DaoException{
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);)
		{
            prepareDeleteStatement(preparedStatement,id);
        } catch (SQLException e) {
            throw new DaoException("Error Deleting Book of id: " + id);
		}
	}

	@Override
	public int count() throws DaoException{
		int amountOfBooks = -1;
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
			ResultSet result = preparedStatement.executeQuery();) 
		{
            if (result.next()) {
				amountOfBooks = result.getInt("count");
			}
        } catch (SQLException e) {
            throw new DaoException("Error Counting Books");
		}
		return amountOfBooks;
	}
}