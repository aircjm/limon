


A _type switch_ is a construct that permits several type assertions in series.

A type switch is like a regular switch statement, but the cases in a type
switch specify types (not values), and those values are compared against
the type of the value held by the given interface value.

	switch v := i.(type) {
	case T:
		// here v has type T
	case S:
		// here v has type S
	default:
		// no match; here v has the same type as i
	}

The declaration in a type switch has the same syntax as a type assertion `i.(T)`,
but the specific type `T` is replaced with the keyword `type`.

This switch statement tests whether the interface value `i`
holds a value of type `T` or `S`.
In each of the `T` and `S` cases, the variable `v` will be of type
`T` or `S` respectively and hold the value held by `i`.
In the default case (where there is no match), the variable `v` is
of the same interface type and value as `i`.

