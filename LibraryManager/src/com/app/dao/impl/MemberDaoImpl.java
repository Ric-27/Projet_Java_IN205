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

public class MemberDaoImpl implements MemberDao {

    private static MemberDaoImpl instance;
	private MemberDaoImpl() { }	
	public static MemberDao getInstance() {
		if(instance == null) {
			instance = new MemberDaoImpl();
		}
		return instance;
    }
}