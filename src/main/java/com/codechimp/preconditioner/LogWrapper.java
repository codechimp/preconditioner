package com.codechimp.preconditioner;

import static com.google.common.base.Preconditions.checkArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogWrapper<E extends Throwable> implements Preconditioner<E>
{
	private final Preconditioner<E> precond;
	
	private final Logger logger;
	
	public LogWrapper(final Preconditioner<E> toBeWrappedPrecond, final Class<?> loggingClass)
	{
		checkArgument(toBeWrappedPrecond != null, "Preconditioner to be wrapped must not be null");
		checkArgument(loggingClass != null, "logging target class must not be null");
		
		this.precond = toBeWrappedPrecond;
		this.logger  = LoggerFactory.getLogger(loggingClass);
	}
	
	@SuppressWarnings("unchecked")
	public void check(boolean isOK, String msg, Object... msgArgs) throws E 
	{
		try
		{
			precond.check(isOK, msg, msgArgs);
		}
		catch(final Exception e)
		{
			logger.error(e.getMessage());
			throw (E) e;
		}
	}

	public void check(boolean isOK) throws E 
	{
		precond.check(isOK);
	}

	@Override
	public String toString() 
	{
		return "LogWrapper [precond=" + precond + "]";
	}
}
