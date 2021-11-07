package com.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

//@Controller
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/index")
	public List<Employee> index() {
		List<Employee> employees = employeeRepository.findAll();

		return employees;
	}

	@GetMapping("/report/{format}")
	public String index(@PathVariable String format) throws FileNotFoundException, JRException {
		String path = "C:\\Users\\Student\\Desktop\\Report";
		List<Employee> employees = employeeRepository.findAll();
		// load file and compile it
		File file = ResourceUtils.getFile("classpath:employees.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", "Shahidur");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (format.equalsIgnoreCase("html")) {
			 JasperExportManager.exportReportToHtmlFile(jasperPrint, path +"\\employees.html");
		}
		if (format.equalsIgnoreCase("pdf")) {
			 JasperExportManager.exportReportToPdfFile(jasperPrint, path +"\\employees.pdf");
		}

		//return "report generated in path : " + path;
		 return "index";
	}
}
