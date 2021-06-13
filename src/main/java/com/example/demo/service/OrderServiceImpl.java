package com.example.demo.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;

import com.example.demo.Constant.KeyConstant;
import com.example.demo.model.StudentModel;
import com.example.demo.model.TradeModel;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService  {
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	
	@Autowired
	OrderService orderService;
	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
    private EntityManager entityManager;
	@Override
	public boolean searchAll(String customerId) {
		Session session = sessionFactory.openSession();
		// Session session = entityManager.unwrap(Session.class);

		

		try 
		{
			Query query = session.createSQLQuery("CALL procedure_customer(:customerId,:search)")
			.addEntity(OrderServiceImpl.class)
			.setParameter("customerId",customerId)
			.setParameter("search","");
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				System.out.println(row);
			}
			// ProcedureCall call = session.createStoredProcedureCall( "procedure_customer");
			// call.registerParameter(1, String.class, ParameterMode.IN).bindValue(customerId);
			// call.registerParameter(2, String.class, ParameterMode.IN).bindValue("");
			// call.registerParameter(3, Class.class, ParameterMode.REF_CURSOR);
			// call.execute();
			// Output output = call.getOutputs().getCurrent();
		} 
		catch (Exception ex) 
		{

			log.error("JRException : ", ex.getMessage());
		}		
		return true;
	}
}
