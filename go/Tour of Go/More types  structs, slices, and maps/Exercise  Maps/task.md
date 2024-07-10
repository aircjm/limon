


Implement `WordCount`.  It should return a map of the counts of each “word” in the string `s`. The `wc.Test` function runs a test suite against the provided function and prints success or failure.

You might find [strings.Fields](https://go.dev/pkg/strings/#Fields) helpful.

<sub>**Note:** if the dependency in the _import_ section is highlighted in red, click the dependency, press <span class="shortcut">&shortcut:ShowIntentionActions;</span> and select **Sync dependencies of main**.</sub>

<div class="hint" title="Click to see possible solution">

    package main
    
    import (
    	"strings"
    
    	"golang.org/x/tour/wc"
    )
    
    func WordCount(s string) map[string]int {
    	m := make(map[string]int)
    	for _, f := range strings.Fields(s) {
    		m[f]++
    	}
    	return m
    }
    
    func main() {
    	wc.Test(WordCount)
    }
    
</div>
