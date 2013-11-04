Preconditioner
==============

Preconditioner is inspired by Google's Guava Precondions class. It
allows to use a Preconditions like API with any kind of Exception:

```java
Preconditioner precondition = new ExceptionReflectionPreconditioner(MyException.class);
precondition.check("TEST".equals(arg), "%s is not equals %s", arg, "TEST");
```

instead of

```java
if(! "TEST".equals(arg))
{
	throw new MyException(String.format("%s is not equals %s", arg, "TEST"));
}
```