


The interface type that specifies zero methods is known as the _empty_interface_:

	interface{}

An empty interface may hold values of any type.
(Every type implements at least zero methods.)

Empty interfaces are used by code that handles values of unknown type.
For example, `fmt.Print` takes any number of arguments of type `interface{}`.

