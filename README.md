Preconditioner
==============

Preconditioner is inspired by Google Guava Precondions class. It
allows to use a Preconditions like API with any kind of Exception:

```java
Preconditioner precondition = new ExceptionReflectionPreconditioner(MyFancyException.class);
precondition.check("TEST".equals(arg), "%s is not equals %s", arg, "TEST");
```

instead of

```java
if(! "TEST".equals(arg))
{
	throw new MyFancyException(String.format("%s is not equals %s", arg, "TEST"));
}
```