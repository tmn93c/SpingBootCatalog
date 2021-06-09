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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService  {
	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Value("${jasper.report.path}")
	private String jrRepoPath;
	
	@Autowired
	TradeServiceImpl tradeService;
	// public byte[] getReport() throws JRException, IOException {
	// 	URL path = getClass().getClassLoader().getResource("reports/flower1.png");
    //     // tìm kiếm file report
	// 	JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/Blank_A4.jrxml").getInputStream());
	// 	// đây là list data của mình, thông thường list này sẽ đc query dưới db
	// 	List<StudentModel> list = new ArrayList<>();
	// 	list.add(new StudentModel(0, "Nguyễn Văn A", 0));
	// 	list.add(new StudentModel(1, "Trần Thị B", 1));
	// 	list.add(new StudentModel(2, "Nguyễn Thị C", 1));
	// 	// khởi tạo data source
	// 	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	// 	// khai báo các parameter
	// 	Map<String, Object> parameters = new HashMap<>();
	// 	parameters.put("createdBy", "TuanLM"); 
	// 	parameters.put("imagesDir",path);
    //     // compile file report cùng các tham số đã khai báo
	// 	return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
	// }

	@Override
	public boolean geterateReport(String type)
	{		
		List<TradeModel> trades = tradeService.getTrades();
		try 
		{
			URL path = getClass().getClassLoader().getResource("reports/flower1.png");
			// tìm kiếm file report
			File file = ResourceUtils.getFile("classpath:reports/Blank_A4.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			// đây là list data của mình, thông thường list này sẽ đc query dưới db
			List<StudentModel> list = new ArrayList<>();
			list.add(new StudentModel(0, "Nguyễn Văn A", 0));
			list.add(new StudentModel(1, "Trần Thị B", 1));
			list.add(new StudentModel(2, "Nguyễn Thị C", 1));
			// khởi tạo data source
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(trades);
			// khai báo các parameter
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "TuanLM"); 
			parameters.put("Author", "Tamnd"); 
			parameters.put("imagesDir",path);
			// compile file report cùng các tham số đã khai báo
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
			LocalDateTime now = LocalDateTime.now();  
			String dateNow = dtf.format(now);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			switch (type.toUpperCase())
			{
				case KeyConstant.REPO_TYPE_HTML :
					 JasperExportManager.exportReportToHtmlFile(jasperPrint, jrRepoPath + dateNow+"_employees.html");
					break;
				case KeyConstant.REPO_TYPE_PDF :
					JasperExportManager.exportReportToPdfFile(jasperPrint, jrRepoPath +dateNow+ "_employees.pdf");
					break;				
			}			
		} 
		catch (FileNotFoundException e)
		{
			log.error("FileNotFoundException : ", e.getMessage(), e.getCause());
		} 
		catch (JRException e) 
		{
			log.error("JRException : ", e.getMessage(), e.getCause());
		}		
		return true;
	}
}
