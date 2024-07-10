


There can be many different binary trees with the same sequence of values stored in it. For example, here are two binary trees storing the sequence 1, 1, 2, 3, 5, 8, 13.

![Tree Image](tree.png)

A function to check whether two binary trees store the same sequence is quite complex in most languages. We'll use Go's concurrency and channels to write a simple solution.

This example uses the `tree` package, which defines the type:

	type Tree struct {
		Left  *Tree
		Value int
		Right *Tree
	}

*1.* Implement the `Walk` function.

*2.* Test the `Walk` function.

The function `tree.New(k)` constructs a randomly-structured (but always sorted) binary tree holding the values `k`, `2k`, `3k`, ..., `10k`.

Create a new channel `ch` and kick off the walker:

	go Walk(tree.New(1), ch)

Then read and print 10 values from the channel. It should be the numbers 1, 2, 3, ..., 10.

*3.* Implement the `Same` function using `Walk` to determine whether `t1` and `t2` store the same values.

*4.* Test the `Same` function.

`Same(tree.New(1), tree.New(1))` should return true, and `Same(tree.New(1), tree.New(2))` should return false.

The documentation for `Tree` can be found [here](https://godoc.org/golang.org/x/tour/tree#Tree).

<sub>**Note:** if the dependency in the _import_ section is highlighted in red, click the dependency, press <span class="shortcut">&shortcut:ShowIntentionActions;</span> and select **Sync dependencies of main**.</sub>

<div class="hint" title="Click to see possible solution">

    package main
    
    import (
    	"fmt"
    
    	"golang.org/x/tour/tree"
    )
    
    func walkImpl(t *tree.Tree, ch chan int) {
    	if t == nil {
    		return
    	}
    	walkImpl(t.Left, ch)
    	ch <- t.Value
    	walkImpl(t.Right, ch)
    }
    
    // Walk walks the tree t sending all values
    // from the tree to the channel ch.
    func Walk(t *tree.Tree, ch chan int) {
    	walkImpl(t, ch)
    	// Need to close the channel here
    	close(ch)
    }
    
    // Same determines whether the trees
    // t1 and t2 contain the same values.
    // NOTE: The implementation leaks goroutines when trees are different.
    // See binarytrees_quit.go for a better solution.
    func Same(t1, t2 *tree.Tree) bool {
    	w1, w2 := make(chan int), make(chan int)
    
    	go Walk(t1, w1)
    	go Walk(t2, w2)
    
    	for {
    		v1, ok1 := <-w1
    		v2, ok2 := <-w2
    		if !ok1 || !ok2 {
    			return ok1 == ok2
    		}
    		if v1 != v2 {
    			return false
    		}
    	}
    }
    
    func main() {
    	fmt.Print("tree.New(1) == tree.New(1): ")
    	if Same(tree.New(1), tree.New(1)) {
    		fmt.Println("PASSED")
    	} else {
    		fmt.Println("FAILED")
    	}
    
    	fmt.Print("tree.New(1) != tree.New(2): ")
    	if !Same(tree.New(1), tree.New(2)) {
    		fmt.Println("PASSED")
    	} else {
    		fmt.Println("FAILED")
    	}
    }
    
</div>
