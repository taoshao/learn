package com.maggie.tree;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class TreeDataServlet extends HttpServlet{

	private String getData(){
		StringBuffer sb = new StringBuffer();
		sb.append("Hello~First Servlet!");
		return sb.toString();
	}
	private String chagestr(String s){
		StringBuffer str = new StringBuffer();
		String s0 = new String(s.substring(0, 1));
		str.append(s0.toUpperCase());
		str.append(s.substring(1).toLowerCase());
		return str.toString();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		String input = req.getParameter("input");
		String output = chagestr(input);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("output", output);
		jsonObject.put("input", input);
		System.out.print(jsonObject.toString());
//		String data = this.getData();
		
		resp.setContentType("text/html;charset=GBK");
		resp.getOutputStream().write(jsonObject.toString().getBytes("GBK"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
	}
}
