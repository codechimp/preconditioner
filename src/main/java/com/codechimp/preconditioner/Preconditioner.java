package com.codechimp.preconditioner;

public interface Preconditioner<E extends Throwable>
{
	public void check(final boolean isOK, final String msg, final Object...msgArgs) throws E;
	public void check(final boolean isOK) throws E;
}
