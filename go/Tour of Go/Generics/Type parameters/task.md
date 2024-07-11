


Go functions can be written to work on multiple types using type parameters. The
type parameters of a function appear between brackets, before the function's
arguments.

  func Index[T comparable](s []T, x T) int

This declaration means that `s` is a slice of any type `T` that fulfills the
built-in constraint `comparable`. `x` is also a value of the same type.

`comparable` is a useful constraint that makes it possible to use the `==` and
`!=` operators on values of the type. In this example, we use it to compare a
value to all slice elements until a match is found. This `Index` function works
for any type that supports comparison.

