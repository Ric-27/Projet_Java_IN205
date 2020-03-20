package com.app.dao.impl;

import java.time.LocalDate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.app.dao.*;
import com.app.exception.*;
import com.app.model.Lending;
import com.app.persistence.ConnectionManager;

public class LendingDaoImpl implements LendingDao {
    
    private static LendingDaoImpl instance;
	private LendingDaoImpl() { }	
	public static LendingDao getInstance() {
		if(instance == null) {
			instance = new LendingDaoImpl();
		}
		return instance;
    }
    
    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
	private static final String SELECT_NOT_RETURNED_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
	private static final String SELECT_NOT_RETURNED_MEM_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
	private static final String SELECT_NOT_RETURNED_LIV_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
	private static final String SELECT_ONE_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
	private static final String CREATE_QUERY = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE Emprunt SET idMembre=?, idLivre=?,dateEmprunt=?, dateRetour=? WHERE id=?;";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM emprunt WHERE idMembre IN (SELECT id FROM membre) and idLivre IN (SELECT id FROM livre);";
    

    public ResultSet createStatementFunction(PreparedStatement preparedStatement, int idMembre, int idLivre, LocalDate lendingDate) throws SQLException{
        preparedStatement.setInt(1, idMembre);
        preparedStatement.setInt(2, idLivre);
        preparedStatement.setDate(3, Date.valueOf(lendingDate));
        preparedStatement.setDate(4, null);
        ResultSet res = preparedStatement.getGeneratedKeys();
        return res;
    }

    @Override
	public void create(int idMembre, int idLivre, LocalDate dateLending) throws DaoException{
        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = createStatementFunction(preparedStatement, idMembre,idLivre, dateLending);) 
            {
                preparedStatement.setInt(1, idMembre);
                
                if (result.next()){

                    MemberDao memberDao = MemberDaoImpl.getInstance();
                    BookDao bookDao = BookDaoImpl.getInstance();
                    Lending lending = new Lending(result.getInt("id"), memberDao.getById(idMembre), bookDao.getById(idLivre), dateLending, null);

                    System.out.println("CREATE LOAN: " + lending);
                }

        }catch(SQLException e){
            throw new DaoException("Probleme lors de la creation de l'emprunt");
        }
    }

    public void updateStatementFunction(PreparedStatement preparedStatement, Lending lending) throws SQLException{
        preparedStatement.setInt(2, lending.getBook().getId());
        preparedStatement.setDate(3, Date.valueOf(lending.getLendDate()));
        if (lending.getReturnDate() != null)
            preparedStatement.setDate(4, Date.valueOf(lending.getReturnDate()));
        else
            preparedStatement.setDate(4, null);
        preparedStatement.setInt(5, lending.getId());
        preparedStatement.executeUpdate();
        System.out.println("UPDATE LOAN: " + lending);
    }

    @Override
	public void update(Lending lending) throws DaoException{
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            ){
                updateStatementFunction(preparedStatement, lending);
            }catch(SQLException e){
                throw new DaoException("Problems updating the loan :" + lending);
            }

    }

    @Override
    public List<Lending> getList() throws DaoException{
        List<Lending> lendings = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = preparedStatement.executeQuery();){
                MemberDao memberDao= MemberDaoImpl.getInstance();
                BookDao bookDao = BookDaoImpl.getInstance();

                while(res.next()){
                    lendings.add(new Lending(res.getInt("id"),
                                            memberDao.getById(res.getInt("idMembre")),
                                            bookDao.getById(res.getInt("idLivre")),
                                            res.getDate("dateEmprunt").toLocalDate(),
                                            res.getDate("dateReturn").toLocalDate()));
                }
            
        } catch (SQLException e) {
            throw new DaoException("Problems getting the list");
        }
        return lendings;
    }
    public List<Lending> getListCurrent() throws DaoException{
        
    }
    

	public List<Lending> getListCurrentByMembre(int idMembre) throws DaoException;
	public List<Lending> getListCurrentByLivre(int idLivre) throws DaoException;
	public Lending getById(int id) throws DaoException;
	public int count() throws DaoException;
}