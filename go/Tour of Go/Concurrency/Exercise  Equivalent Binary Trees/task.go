package main

import (
	"fmt"
	"golang.org/x/tour/tree"
)

func walkImpl(t *tree.Tree, ch chan int) {
	/* TODO */
}

// Walk walks the tree t sending all values
// from the tree to the channel ch.
func Walk(t *tree.Tree, ch chan int) {
	/* TODO */
}

// Same determines whether the trees
// t1 and t2 contain the same values.
func Same(t1, t2 *tree.Tree) bool {
	/* TODO */
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
