package com.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

@Controller
@RequestMapping("/student")
public class StudentController {

	private StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@GetMapping("/index")
	public String index() {
		List<Student> students = studentRepository.findAll();

		return "index";
	}

	@GetMapping("/report")
	public String index(HttpServletResponse response) throws FileNotFoundException, JRException {
		List<Student> students = studentRepository.findAll();
		File file = ResourceUtils.getFile("classpath:coffee.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
		Map<String, Object> parameters = new HashMap<>();
		//parameters.put("name", "Shahidur");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		try {
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.setContentType("application/pdf");
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
}
