package com.codechimp.preconditioner.factory;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import com.google.common.base.Preconditions;

public final class ExceptionReflectionFactory<E extends Throwable> implements ErrorFactory<E>
{
	private final Constructor<E> defaultConstructor;
	private final Constructor<E> msgConstructor;
	
	public ExceptionReflectionFactory(final Class<E> exceptionClass)
	{
		Preconditions.checkArgument(exceptionClass != null, "Exception class must not be null");
		this.defaultConstructor   = getConstructor(exceptionClass);
		this.msgConstructor       = getConstructor(exceptionClass, String.class);
	}

	private Constructor<E> getConstructor(final Class<E> exceptionClass, final Class<?>...paramTypes)
	{
		try 
		{
			return exceptionClass.getConstructor(paramTypes);
		} 
		catch (final NoSuchMethodException e) 
		{
			throw new IllegalArgumentException(exceptionClass + 
											   " does not have a constructor with parameter types " +
											   	Arrays.toString(paramTypes));
		} 
		catch (final SecurityException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public void throwError() throws E 
	{
		throw newError(defaultConstructor);
	}
	
	public void throwError(final String msg) throws E 
	{
		throw newError(msgConstructor, msg);
	}

	
	private E newError(final Constructor<E> constructor, final Object...args)
	{
		try
		{
			return (E) constructor.newInstance(args);
			
		}
		catch(final Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString() 
	{
		return "ExceptionReflectionFactory [defaultConstructor="
				+ defaultConstructor + ", msgConstructor=" + msgConstructor
				+ "]";
	}
}
