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

public class BookDaoImpl implements BookDao{

    private static BookDaoImpl instance;
	private BookDaoImpl() { }	
	public static BookDao getInstance() {
		if(instance == null) {
			instance = new BookDaoImpl();
		}
		return instance;
    }
}