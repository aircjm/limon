package main

import "golang.org/x/tour/reader"

type MyReader struct{}

// TODO: Implement a Read method for the MyReader type. The method should return "int" and "error" values.

func main() {
	reader.Validate(MyReader{})
}
