package com.codechimp.preconditioner;

import static com.google.common.base.Preconditions.checkArgument;

import com.codechimp.preconditioner.factory.ErrorFactory;

public class DefaultPreconditioner<E extends Throwable> implements Preconditioner<E>
{
	private final ErrorFactory<E> exceptionFactory;
	
	public DefaultPreconditioner(final ErrorFactory<E> exceptionFactory)
	{
		checkArgument(exceptionFactory != null, "Exception factory must not be null");
		this.exceptionFactory = exceptionFactory;
	}

	public void check(final boolean isOK, final String msg, final Object...msgArgs) throws E
	{
		if(! isOK) 
		{
			exceptionFactory.throwError(String.format(msg, msgArgs));
		}
	}
	
	public void check(final boolean isOK) throws E
	{
		if(! isOK) 
		{
			exceptionFactory.throwError();
		}
	}
	
	@Override
	public String toString() 
	{
		return "Preconditioner [exceptionFactory=" + exceptionFactory + "]";
	}
}
