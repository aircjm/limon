package main

import "fmt"

type IPAddr [4]byte

// TODO: Add a "String" method that is defined on values of type "IPAddr" (with "ip" being the receiver) and returns a string.

func main() {
	hosts := map[string]IPAddr{
		"loopback":  {127, 0, 0, 1},
		"googleDNS": {8, 8, 8, 8},
	}
	for name, ip := range hosts {
		fmt.Printf("%v: %v\n", name, ip)
	}
}
