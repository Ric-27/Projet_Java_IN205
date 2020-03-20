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
import com.app.model.Lending;

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


}