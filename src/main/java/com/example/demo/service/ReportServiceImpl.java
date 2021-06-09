package com.example.demo.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Constant.KeyConstant;
import com.example.demo.model.StudentModel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService  {

	@Value("${jasper.report.path}")
	private String jrRepoPath;
	
	public byte[] getReport() throws JRException, IOException {
		URL path = getClass().getClassLoader().getResource("reports/flower1.png");
        // tìm kiếm file report
		JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/Blank_A4.jrxml").getInputStream());
		// đây là list data của mình, thông thường list này sẽ đc query dưới db
		List<StudentModel> list = new ArrayList<>();
		list.add(new StudentModel(0, "Nguyễn Văn A", 0));
		list.add(new StudentModel(1, "Trần Thị B", 1));
		list.add(new StudentModel(2, "Nguyễn Thị C", 1));
		// khởi tạo data source
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		// khai báo các parameter
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "TuanLM"); 
		parameters.put("imagesDir",path);
        // compile file report cùng các tham số đã khai báo
		return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
	}

	@Override
	public boolean geterateReport(String type)
	{		
		try 
		{
			URL path = getClass().getClassLoader().getResource("reports/flower1.png");
			// tìm kiếm file report
			JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/Blank_A4.jrxml").getInputStream());
			// đây là list data của mình, thông thường list này sẽ đc query dưới db
			List<StudentModel> list = new ArrayList<>();
			list.add(new StudentModel(0, "Nguyễn Văn A", 0));
			list.add(new StudentModel(1, "Trần Thị B", 1));
			list.add(new StudentModel(2, "Nguyễn Thị C", 1));
			// khởi tạo data source
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			// khai báo các parameter
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "TuanLM"); 
			parameters.put("imagesDir",path);
			// compile file report cùng các tham số đã khai báo
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			
			switch (type.toUpperCase())
			{
				case KeyConstant.REPO_TYPE_HTML :
					 JasperExportManager.exportReportToHtmlFile(jasperPrint, jrRepoPath + "employees.html");
					break;
				case KeyConstant.REPO_TYPE_PDF :
					JasperExportManager.exportReportToPdfFile(jasperPrint, jrRepoPath + "employees.pdf");
					break;				
			}			
		} 
		catch (FileNotFoundException e)
		{
			// log.error("FileNotFoundException : ", e.getMessage(), e.getCause());
		} 
		catch (JRException e) 
		{
			// log.error("JRException : ", e.getMessage(), e.getCause());
		}		
		return true;
	}
}
