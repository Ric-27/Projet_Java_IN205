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
    
    private static final String SELECT_ALL_QUERY = "SELECT * FROM emprunt;";
	private static final String SELECT_NOT_RETURNED_QUERY = "SELECT * FROM emprunt WHERE emprunt.dateRetour IS NULL;";
	private static final String SELECT_NOT_RETURNED_MEM_QUERY = "SELECT * FROM  Emprunt WHERE dateRetour IS NULL AND idMembre = ?;";
	private static final String SELECT_NOT_RETURNED_LIV_QUERY = "SELECT * FROM emprunt WHERE dateRetour IS NULL AND idLivre = ?;";
	private static final String SELECT_ONE_QUERY = "SELECT * FROM emprunt WHERE id = ?;";
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

                    //System.out.println("CREATE LOAN: " + lending);
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
        //System.out.println("UPDATE LOAN: " + lending);
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
                
                System.out.println("List of all the loans: " + lendings);

                while(res.next()){
                    lendings.add(new Lending(res.getInt("id"),
                                            memberDao.getById(res.getInt("idMembre")),
                                            bookDao.getById(res.getInt("idLivre")),
                                            res.getDate("dateEmprunt").toLocalDate(),
                                            res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
                }

            
        } catch (SQLException e) {
            throw new DaoException("Problems getting the general list of loans");
        }
        return lendings;
    }
    @Override
    public List<Lending> getListCurrent() throws DaoException{
        List<Lending> lendings = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_QUERY);
        ResultSet res = preparedStatement.executeQuery();){
            MemberDao memberDao= MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();

            while(res.next()){
                lendings.add(new Lending(res.getInt("id"),
                                        memberDao.getById(res.getInt("idMembre")),
                                        bookDao.getById(res.getInt("idLivre")),
                                        res.getDate("dateEmprunt").toLocalDate(),
                                        res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
                                        //System.out.println("List of the current lendings : " + lendings);
            }
    } catch (SQLException e) {
        throw new DaoException("Problems getting the current list of loans");
    }
    return lendings;
    }
    

    
    public ResultSet getByFunction(PreparedStatement preparedStatement,int idMembre) throws SQLException{
        
        preparedStatement.setInt(1, idMembre);
        ResultSet res = preparedStatement.executeQuery();
        return res;
    }

    @Override
	public List<Lending> getListCurrentByMembre(int idMembre) throws DaoException{
        List<Lending> lendings = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_MEM_QUERY);
        ResultSet res = getByFunction(preparedStatement, idMembre)){

            MemberDao memberDao= MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();

            while(res.next()){
                lendings.add(new Lending(res.getInt("id"),
                                        memberDao.getById(res.getInt("idMembre")),
                                        bookDao.getById(res.getInt("idLivre")),
                                        res.getDate("dateEmprunt").toLocalDate(),
                                        res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }
            //System.out.println("List of the current loans of the member: "+ idMembre + ". Loans: " + lendings);
        
        } catch (SQLException e) {
            throw new DaoException("Problems getting the list by member");
        }
        return lendings;       
    }

    @Override
	public List<Lending> getListCurrentByLivre(int idLivre) throws DaoException{
        List<Lending> lendings = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_RETURNED_LIV_QUERY);
        ResultSet res = getByFunction(preparedStatement, idLivre)){

            MemberDao memberDao= MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();

            while(res.next()){
                lendings.add(new Lending(res.getInt("id"),
                                        memberDao.getById(res.getInt("idMembre")),
                                        bookDao.getById(res.getInt("idLivre")),
                                        res.getDate("dateEmprunt").toLocalDate(),
                                        res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }
            //System.out.println("List of the current loans of the book: "+ idLivre + ". Loans: " + lendings);
        
        } catch (SQLException e) {
            throw new DaoException("Problems getting the list by livre");
        }
        return lendings; 

    }

    @Override
	public Lending getById(int id) throws DaoException{
        Lending lending = new Lending();

        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
        ResultSet res = getByFunction(preparedStatement, id)){

            MemberDao memberDao= MemberDaoImpl.getInstance();
            BookDao bookDao = BookDaoImpl.getInstance();

            if(res.next()){
                lending = new Lending(res.getInt("id"),
                                        memberDao.getById(res.getInt("idMembre")),
                                        bookDao.getById(res.getInt("idLivre")),
                                        res.getDate("dateEmprunt").toLocalDate(),
                                        res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate());
            }
            //System.out.println("Loan with the ID: "+ id + ". Loan:  " + lending);
        
        } catch (SQLException e) {
            throw new DaoException("Problems getting the list by Id");
        }
        return lending; 
    }

    @Override
	public int count() throws DaoException{
        int lendings = -1;

        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
        ResultSet result = preparedStatement.executeQuery();) {

       if (result.next()) {
           lendings = result.getInt(1);
           //System.out.println("LOANS QUANTITY: " + lendings);
       }
   } catch (SQLException e) {
       throw new DaoException("Problems counting the number of loans", e);
   } 
   
   return lendings;
    }
}