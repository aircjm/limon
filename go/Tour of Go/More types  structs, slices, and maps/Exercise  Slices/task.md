


Implement `Pic`. It should return a slice of length `dy`, each element of which is a slice of `dx` 8-bit unsigned integers. When you run the program, it will display your picture, interpreting the integers as grayscale (well, bluescale) values.

The choice of image is up to you. Interesting functions include `(x+y)/2`, `x*y`, and `x^y`.

(You need to use a loop to allocate each `[]uint8` inside the `[][]uint8`.)

(Use `uint8(intValue)` to convert between types.)

<sub>**Note:** if the dependency in the _import_ section is highlighted in red, click the dependency, press <span class="shortcut">&shortcut:ShowIntentionActions;</span> and select **Sync dependencies of main**.</sub>

<div class="hint" title="Click to see possible solution">

    package main
    
    import "golang.org/x/tour/pic"
    
    func Pic(dx, dy int) [][]uint8 {
    	p := make([][]uint8, dy)
    	for i := range p {
    		p[i] = make([]uint8, dx)
    	}
    
    	for y, row := range p {
    		for x := range row {
    			row[x] = uint8(x * y)
    		}
    	}
    
    	return p
    }
    
    func main() {
    	pic.Show(Pic)
    }
    
</div>
