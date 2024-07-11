


The `io` package specifies the `io.Reader` interface,
which represents the read end of a stream of data.

The Go standard library contains [many implementations](https://cs.opensource.google/search?q=Read%5C(%5Cw%2B%5Cs%5C%5B%5C%5Dbyte%5C)&ss=go%2Fgo) of this interface, including files, network connections, compressors, ciphers, and others.

The `io.Reader` interface has a `Read` method:

	func (T) Read(b []byte) (n int, err error)

`Read` populates the given byte slice with data and returns the number of bytes
populated and an error value. It returns an `io.EOF` error when the stream
ends.

The example code creates a
[`strings.Reader`](https://go.dev/pkg/strings/#Reader)
and consumes its output 8 bytes at a time.

