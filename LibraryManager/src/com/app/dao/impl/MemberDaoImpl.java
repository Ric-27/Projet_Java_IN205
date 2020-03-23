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
import com.app.model.Member;
import com.app.model.Member.Subscription;
import com.app.persistence.ConnectionManager;

public class MemberDaoImpl implements MemberDao {

    private static MemberDaoImpl instance;
	private MemberDaoImpl() { }	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
	}

	private static final String SELECT_ALL_QUERY = "SELECT * from membre;";
	private static final String SELECT_BY_ID_QUERY = "SELECT * from membre WHERE id = ?;";
	private static final String CREATE_QUERY = "INSERT INTO membre (nom,prenom,adresse,email,telephone,abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE membre SET nom=?, prenom=?,adresse=?,email=?,telephone=?,abonnement=? WHERE id=?;";
	private static final String DELETE_QUERY = "DELETE FROM membre WHERE id=?;";
	private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM membre;";
	
	@Override
	public List<Member> getList() throws DaoException{
		List<Member> members = new ArrayList<Member>();
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet result = preparedStatement.executeQuery();) 
		{
			while(result.next()){

				members.add(new Member(result.getInt("id"),result.getString("nom"),result.getString("prenom"),result.getString("adresse"),result.getString("email"),result.getString("telephone"),Member.Subscription.valueOf(result.getString("abonnement"))));
			}
        } catch (SQLException e) {
            throw new DaoException("Error Downloading List of Members");
		}
		return members;
	}

	public ResultSet prepareGetByIdStatement(PreparedStatement preparedStatement, int id) throws SQLException{
		preparedStatement.setInt(1,id);
		return preparedStatement.executeQuery();
	}

	@Override
	public Member getById(int id) throws DaoException{
		Member member = new Member();
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            ResultSet result = prepareGetByIdStatement(preparedStatement, id);) 
		{
            if (result.next()) {
				member.setId(result.getInt("id"));
				member.setLastname(result.getString("nom"));
				member.setName(result.getString("prenom"));
				member.setAddress(result.getString("adresse"));
				member.setEmail(result.getString("email"));
				member.setPhone(result.getString("telephone"));
				member.setSubscription(Member.Subscription.valueOf(result.getString("abonnement")));
			}
        } catch (SQLException e) {
            throw new DaoException("Error downloading member with id:" + id);
		}
		return member;
	}

	public ResultSet prepareCreateStatement(PreparedStatement preparedStatement, String lastname, String name, String address, String email, String phone) throws SQLException{
		ResultSet result;
		preparedStatement.setString(1,lastname);
		preparedStatement.setString(2,name);
		preparedStatement.setString(3,address);
		preparedStatement.setString(4,email);
		preparedStatement.setString(5,phone);
		preparedStatement.setString(6,"BASIC");
		preparedStatement.executeUpdate();
		result = preparedStatement.getGeneratedKeys();
		return result;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
		int id = -1;
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY,Statement.RETURN_GENERATED_KEYS);
            ResultSet result = prepareCreateStatement(preparedStatement, nom, prenom, adresse, email, telephone);) 
		{
            if (result.next()) {
				id = result.getInt("id");
			}
        } catch (SQLException e) {
            throw new DaoException("Error Creating Member");
		}
		return id;
	}

	public void prepareUpdateStatement(PreparedStatement preparedStatement, String lastname, String name, String address, String email, String phone, Subscription subscription, int id) throws SQLException{
		preparedStatement.setString(1,lastname);
		preparedStatement.setString(2,name);
		preparedStatement.setString(3,address);
		preparedStatement.setString(4,email);
		preparedStatement.setString(5,phone);
		preparedStatement.setString(6,subscription.toString());
		preparedStatement.setInt(7,id);
		preparedStatement.executeUpdate();
	}

	@Override
	public void update(Member member) throws DaoException{
		try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);)
		{
            prepareUpdateStatement(preparedStatement, member.getLastname(),member.getName(),member.getAddress(),member.getEmail(),member.getPhone(),member.getSubscription(),  member.getId());
        } catch (SQLException e) {
            throw new DaoException("Error Updating Member of id: " + member.getId());
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
            throw new DaoException("Error Deleting Member of id: " + id);
		}
	}
	@Override
	public int count() throws DaoException{
		int amountOfMembers = -1;
		try (Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
			ResultSet result = preparedStatement.executeQuery();) 
		{
            if (result.next()) {
				amountOfMembers = result.getInt("count");
			}
        } catch (SQLException e) {
            throw new DaoException("Error Counting Members");
		}
		return amountOfMembers;
	}
}