


One of the most ubiquitous interfaces is [`Stringer`](https://go.dev/pkg/fmt/#Stringer) defined by the [`fmt`](https://go.dev/pkg/fmt/) package.

	type Stringer interface {
		String() string
	}

A `Stringer` is a type that can describe itself as a string. The `fmt` package
(and many others) look for this interface to print values.

