


The `default` case in a `select` is run if no other case is ready.

Use a `default` case to try a send or receive without blocking:

	select {
	case i := <-c:
		// use i
	default:
		// receiving from c would block
	}

