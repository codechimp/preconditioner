package com.codechimp.preconditioner;

import com.codechimp.preconditioner.factory.ExceptionReflectionFactory;

public final class ExceptionReflectionPreconditioner<E extends Throwable> 
extends DefaultPreconditioner<E>
{
	public ExceptionReflectionPreconditioner(final Class<E> errorClass)
	{
		super(new ExceptionReflectionFactory<E>(errorClass));
	}

	@Override
	public String toString() 
	{
		return "ExceptionReflectionPreconditioner []" + super.toString();
	}
}
