package com.cod.exception;

public class CODException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7913816964824586799L;


	private String msg;
	private Throwable e;
	
	public CODException() {
		super();
	}
	
	
	public CODException(String msg,Throwable e) {
		super(msg,e);
		this.msg = msg;
		this.e = e;
	}

	public CODException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public CODException(Throwable e) {
		super(e);
	}
	
	
}
