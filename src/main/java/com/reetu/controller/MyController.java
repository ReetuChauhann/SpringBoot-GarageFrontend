package com.reetu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Garage;

@Controller
public class MyController {
	RestTemplate rt=new RestTemplate();
	String URL="http://localhost:6666";
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/addgarage")
	public String addgarage(@ModelAttribute Garage g,MultipartFile image, Model m) {
		String API="/add";
		
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<>();
		data.add("Garage", g);
		data.add("image",convertf(image));
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(data,header);
		ResponseEntity<String> status=rt.postForEntity(URL+API, requestEntity, String.class);
		if(status.getStatusCode()==HttpStatus.OK) {
			m.addAttribute("sts", "Successfully Added!");
		}else {
			m.addAttribute("sts", "Already Exists!");
		}
		return "index";
	}
	
	//for converting the file into FileSystemresource
	static FileSystemResource convertf(MultipartFile file) {
		File convertdf=new File(file.getOriginalFilename());
		try {
			convertdf.createNewFile();
			FileOutputStream fos=new FileOutputStream(convertdf);
			fos.write(file.getBytes());
			fos.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new FileSystemResource(convertdf);
	}
	
	//get all ids only
	@ModelAttribute
	public void getallids(Model m) {
		
		String API="/getallids";
		List<Integer> ids=rt.getForObject(URL+API, List.class);
		m.addAttribute("ids", ids);
	}
	
	//get all garage
	@GetMapping("GetallGarage")
	public String getallgarage(Model m){
		String API="/getallgarage";
		
		List<Garage> getall=rt.getForObject(URL+API, List.class);
		m.addAttribute("getall", getall);
		return "AllGarage";	
	}

	//get image
	@GetMapping("getimage")
	public void getimage(int gid, HttpServletResponse response) {
		String API="/getphoto/" + gid;
		
		byte[] b=rt.getForObject(URL+API, byte[].class);
		try {
			response.getOutputStream().write(b);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateG")
	public String page() {
		return "update";
	}
	
	@RequestMapping("/updategarage")
	public String update(@ModelAttribute Garage g, Model m){
		String API="/updategarage";
		HttpEntity<Garage> requestEntity=new HttpEntity<Garage>(g);
		
		ResponseEntity<String> status=rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
		if(status.getStatusCode()==HttpStatus.OK) {
			m.addAttribute("status", "Updated Successfully!");
		}else {
			m.addAttribute("status", "Something Went Wrong");
		}
		
        API="/getallgarage";
		
		List<Garage> getall=rt.getForObject(URL+API, List.class);
		m.addAttribute("getall", getall);
		
		return "update";
	}
	
	@RequestMapping("/Updateimage")
	public String updateimage(int gid, MultipartFile image, Model m, HttpServletResponse response) {
		
		String API="/updateimage";
		
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		LinkedMultiValueMap<String, Object> data=new  LinkedMultiValueMap<>();
		data.add("gid", gid);
		data.add("image", convertf(image));
		
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(data,header);
		
		ResponseEntity<String> status=rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
		if(status.getStatusCode()==HttpStatus.OK) {
			
			m.addAttribute("status", "Updated!");
		}else {
			m.addAttribute("status", "Something Went Wrong");
		}
		
		    API="/getallgarage";
			
			List<Garage> getall=rt.getForObject(URL+API, List.class);
			m.addAttribute("getall", getall);
			
		
		return "AllGarage";
		
	}
	
	@RequestMapping("/Deletebyid")
	public String deletebyid(int gid, Model m){
		String API="/deletebyid/"+gid;
		
		ResponseEntity<String> sts=rt.exchange(URL+API, HttpMethod.DELETE, null, String.class);
		if(sts.getStatusCode()==HttpStatus.OK) {
			m.addAttribute("sts", "Successfully Deleted!");
		}else {
			m.addAttribute("sts", "Something Went Wrong!");
		}
		
		return "index";
	}
	
	@RequestMapping("/likename")
	public String page2() {
		return "Likename";	
	}
	
	@RequestMapping("/likenamegarage")
	public String samenamegarage(String name, Model m) {
		String API="/getlikenamegarage/" +name;
		
		List<Garage> likenamegarage=rt.getForObject(URL+API, List.class);
		m.addAttribute("likename",likenamegarage);
		return "Likename";
	}
}
