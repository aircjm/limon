


Implement a `Reader` type that emits an infinite stream of the ASCII character
`'A'`.

<sub>**Note:** if the dependency in the _import_ section is highlighted in red, click the dependency, press <span class="shortcut">&shortcut:ShowIntentionActions;</span> and select **Sync dependencies of main**.</sub>

<div class="hint" title="Click to see possible solution">

    package main
    
    import "golang.org/x/tour/reader"
    
    type MyReader struct{}
    
    func (r MyReader) Read(b []byte) (int, error) {
    	for i := range b {
    		b[i] = 'A'
    	}
    	return len(b), nil
    }
    
    func main() {
    	reader.Validate(MyReader{})
    }
    
</div>
