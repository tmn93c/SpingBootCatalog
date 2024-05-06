package com.example.demo.service;


import java.sql.Connection;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;

import com.example.demo.util.Dbconf;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Service;

// import net.sf.jasperreports.engine.JRException;
// import net.sf.jasperreports.engine.JasperCompileManager;
// import net.sf.jasperreports.engine.JasperExportManager;
// import net.sf.jasperreports.engine.JasperFillManager;
// import net.sf.jasperreports.engine.JasperPrint;
// import net.sf.jasperreports.engine.JasperReport;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@RequiredArgsConstructor
@PackagePrivate
public class OrderServiceImpl implements OrderService  {
	static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
//	final Dbconf dbconf;
	Connection con = null;
    final SessionFactory sessionFactory;
    final EntityManager entityManager;
	
	@Override
	public boolean searchAll(String customerId) {
		// Session session = sessionFactory.openSession();
		Session session = entityManager.unwrap(Session.class);
//		con = dbconf.getConnection();
		

		try 
		{
			// Query query = session.createSQLQuery("CALL procedure_customer(:customerId,:search)")
			// .addEntity(OrderServiceImpl.class)
			// .setParameter("customerId",customerId)
			// .setParameter("search","");
			// List<Object[]> rows = query.list();
			// for(Object[] row : rows){
			// 	System.out.println(row);
			// }

			ProcedureCall call = session.createStoredProcedureCall( "procedure_customer");
			call.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			call.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			call.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
			// call.registerStoredProcedureParameter(4, void.class, ParameterMode.REF_CURSOR);
			// call.registerStoredProcedureParameter(5, void.class, ParameterMode.REF_CURSOR);
			call.setParameter(1, customerId);
			call.setParameter(2, "");
			boolean execute = call.execute();
			while (!execute && call.hasMoreResults()) {
				execute = call.execute();
			}
			if (!execute) {
				System.err.println("Cannot find result set");
				return false;
			}
			List resultList = call.getResultList();
			entityManager.close();
			Output output = call.getOutputs().getCurrent();
			var data3 = ( (ResultSetOutput) output ).getResultList();
			log.info("data : ",data3);
	
			// StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "procedure_customer");
			// query.registerStoredProcedureParameter( "v_customerId", String.class, ParameterMode.IN);
			// query.registerStoredProcedureParameter( "v_search", String.class, ParameterMode.IN);
			// query.registerStoredProcedureParameter( "rc", Class.class, ParameterMode.OUT);
			// query.registerStoredProcedureParameter( "rc1", Class.class, ParameterMode.OUT);
			// query.registerStoredProcedureParameter( "rc2", Class.class, ParameterMode.OUT);
			// query.setParameter("v_customerId", customerId);
			// query.setParameter("v_search", "");

			// query.execute();
			// List<Object[]> data_ = ( (ResultSetOutput) query.getOutputParameterValue("rc") ).getResultList();
			// log.info("data : ",data_.size());
		} 
		catch (Exception ex) 
		{

			log.error("JRException : ", ex.getMessage());
		}		
		return true;
	}
}
