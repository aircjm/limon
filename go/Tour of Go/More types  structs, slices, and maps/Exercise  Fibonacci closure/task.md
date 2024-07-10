


Let's have some fun with functions.

Implement a `fibonacci` function that returns a function (a closure) that
returns successive [fibonacci numbers](https://en.wikipedia.org/wiki/Fibonacci_number)
(0, 1, 1, 2, 3, 5, ...).

<div class="hint" title="Click to see possible solution">

    package main
    
    import "fmt"
    
    // fibonacci is a function that returns
    // a function that returns an int.
    func fibonacci() func() int {
    	f, g := 1, 0
    	return func() int {
    		f, g = g, f+g
    		return f
    	}
    }
    
    func main() {
    	f := fibonacci()
    	for i := 0; i < 10; i++ {
    		fmt.Println(f())
    	}
    }
    
</div>
