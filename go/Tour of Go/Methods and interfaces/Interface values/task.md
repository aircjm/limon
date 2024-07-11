


Under the hood, interface values can be thought of as a tuple of a value and a
concrete type:

	(value, type)

An interface value holds a value of a specific underlying concrete type.

Calling a method on an interface value executes the method of the same name on
its underlying type.

