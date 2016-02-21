package com.gnostrenoff.cdb.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gnostrenoff.cdb.dao.CompanyDao;
import com.gnostrenoff.cdb.dao.ComputerDao;
import com.gnostrenoff.cdb.dao.exceptions.DaoException;
import com.gnostrenoff.cdb.dao.impl.CompanyDaoImpl;
import com.gnostrenoff.cdb.dao.impl.ComputerDaoImpl;
import com.gnostrenoff.cdb.dao.utils.JDBCConnection;
import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.exceptions.TransactionException;

/**
 * implementation of a company service
 * 
 * @author excilys
 */
public class CompanyServiceImpl implements CompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
	private static CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
	private static CompanyDao companyDao;

	private CompanyServiceImpl() {
		companyDao = CompanyDaoImpl.getInstance();
	}

	public static CompanyServiceImpl getInstance() {
		return companyServiceImpl;
	}

	@Override
	public List<Company> getList() {
		return companyDao.getList();
	}

	@Override
	public Company get(long companyId) {
		return companyDao.get(companyId);
	}

	@Override
	public void delete(long id) {
		
		// get connection to use from connection provider
		Connection conn = JDBCConnection.getInstance().getConnection();

		try{
			conn.setAutoCommit(false);
			
			//then delete the related computers
			ComputerDao computerDao = ComputerDaoImpl.getInstance();
			QueryParams params = new QueryParams(id);
			List<Computer> list = computerDao.getList(params, conn);
			for(Computer computer : list){
				LOGGER.info("deleting related computer " + computer.getName() + "(" + computer.getId() + ")");
				computerDao.delete(computer.getId(), conn);
			}
			
			//first delete company
			LOGGER.info("deleting company " + id);
			companyDao.delete(id, conn);
			
			conn.commit();
			
		}catch(DaoException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				LOGGER.error("failed to rollback transaction");
				throw new TransactionException("failed to roolback transaction");
			}
			LOGGER.error("an error occured during transaction");
			throw new TransactionException("an error occured during transaction");
		} catch (SQLException e) {
			LOGGER.error("failed to disable autocommit");
			throw new TransactionException("failed to disable autocommit");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("failed to close connection");
				throw new TransactionException("failed to close connection");
			}
		}
		
	}

}
