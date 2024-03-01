package com.ecom.entities;

public class Response {

	String result;

	public Response(String result) {
		super();
		this.result = result;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response [result=" + result + "]";
	}
	
}
