package com.codechimp.preconditioner.factory;

public interface ErrorFactory<E extends Throwable> 
{
	public void throwError() throws E;
	public void throwError(String msg) throws E;
}
