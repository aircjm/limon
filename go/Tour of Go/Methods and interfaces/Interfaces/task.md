


An _interface type_ is defined as a set of method signatures.

A value of interface type can hold any value that implements those methods.

*Note:* There is an error in the example code on line 22.
`Vertex` (the value type) doesn't implement `Abser` because
the `Abs` method is defined only on `*Vertex` (the pointer type).

