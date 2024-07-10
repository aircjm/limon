


When slicing, you may omit the high or low bounds to use their defaults instead.

The default is zero for the low bound and the length of the slice for the high bound.

For the array

	var a [10]int

these slice expressions are equivalent:

	a[0:10]
	a[:10]
	a[0:]
	a[:]

