package com.example.demo.service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.StudentModel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	public byte[] getReport() throws JRException, IOException {
        // tìm kiếm file report
		JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("reports/Flower_Landscape.jrxml").getInputStream());
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
        // compile file report cùng các tham số đã khai báo
		return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
	}
}