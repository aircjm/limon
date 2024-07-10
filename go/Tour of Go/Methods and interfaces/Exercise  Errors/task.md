


Copy your `Sqrt` function from the [earlier exercise](course://%20Flow%20control%20statements%3A%20for%2C%20if%2C%20else%2C%20switch%20and%20defer/Exercise%3A%20Loops%20and%20Functions/taks.go) and modify it to return an `error` value.

`Sqrt` should return a non-nil error value when given a negative number, as it doesn't support complex numbers.

Create a new type

	type ErrNegativeSqrt float64

and make it an `error` by giving it a

	func (e ErrNegativeSqrt) Error() string

method such that `ErrNegativeSqrt(-2).Error()` returns `"cannot Sqrt negative number: -2"`.

*Note:* A call to `fmt.Sprint(e)` inside the `Error` method will send the program into an infinite loop. You can avoid this by converting `e` first: `fmt.Sprint(float64(e))`. Why?

Change your `Sqrt` function to return an `ErrNegativeSqrt` value when given a negative number.

<div class="hint" title="Click to see possible solution">

    package main
    
    import (
    	"fmt"
    	"math"
    )
    
    type ErrNegativeSqrt float64
    
    func (e ErrNegativeSqrt) Error() string {
    	return fmt.Sprintf("Sqrt: negative number %g", e)
    }
    
    const delta = 1e-10
    
    func Sqrt(f float64) (float64, error) {
    	if f < 0 {
    		return 0, ErrNegativeSqrt(f)
    	}
    	z := f
    	for {
    		n := z - (z*z-f)/(2*z)
    		if math.Abs(n-z) < delta {
    			break
    		}
    		z = n
    	}
    	return z, nil
    }
    
    func main() {
    	fmt.Println(Sqrt(2))
    	fmt.Println(Sqrt(-2))
    }
    
</div>
