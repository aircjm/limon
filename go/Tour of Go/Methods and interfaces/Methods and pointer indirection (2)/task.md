


The equivalent thing happens in the reverse direction.

Functions that take a value argument must take a value of that specific type:

	var v Vertex
	fmt.Println(AbsFunc(v))  // OK
	fmt.Println(AbsFunc(&v)) // Compile error!

while methods with value receivers take either a value or a pointer as the
receiver when they are called:

	var v Vertex
	fmt.Println(v.Abs()) // OK
	p := &v
	fmt.Println(p.Abs()) // OK

In this case, the method call `p.Abs()` is interpreted as `(*p).Abs()`.

