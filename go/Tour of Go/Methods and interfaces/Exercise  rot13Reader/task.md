


A common pattern is an [io.Reader](https://go.dev/pkg/io/#Reader) that wraps another `io.Reader`, modifying the stream in some way.

For example, the [gzip.NewReader](https://go.dev/pkg/compress/gzip/#NewReader) function takes an `io.Reader` (a stream of compressed data) and returns a `*gzip.Reader` that also implements `io.Reader` (a stream of the decompressed data).

Implement a `rot13Reader` that implements `io.Reader` and reads from an `io.Reader`, modifying the stream by applying the [rot13](https://en.wikipedia.org/wiki/ROT13) substitution cipher to all alphabetical characters.

The `rot13Reader` type is provided for you.
Make it an `io.Reader` by implementing its `Read` method.

<div class="hint" title="Click to see possible solution">

    package main
    
    import (
    	"io"
    	"os"
    	"strings"
    )
    
    func rot13(b byte) byte {
    	var a, z byte
    	switch {
    	case 'a' <= b && b <= 'z':
    		a, z = 'a', 'z'
    	case 'A' <= b && b <= 'Z':
    		a, z = 'A', 'Z'
    	default:
    		return b
    	}
    	return (b-a+13)%(z-a+1) + a
    }
    
    type rot13Reader struct {
    	r io.Reader
    }
    
    func (r rot13Reader) Read(p []byte) (n int, err error) {
    	n, err = r.r.Read(p)
    	for i := 0; i < n; i++ {
    		p[i] = rot13(p[i])
    	}
    	return
    }
    
    func main() {
    	s := strings.NewReader(
    		"Lbh penpxrq gur pbqr!")
    	r := rot13Reader{s}
    	io.Copy(os.Stdout, &r)
    }
    
</div>
