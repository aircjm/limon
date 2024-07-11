


Channels are a typed conduit through which you can send and receive values with the channel operator, `<-`.

	ch <- v    // Send v to channel ch.
	v := <-ch  // Receive from ch, and
	           // assign value to v.

(The data flows in the direction of the arrow.)

Like maps and slices, channels must be created before use:

	ch := make(chan int)

By default, sends and receives block until the other side is ready. This allows goroutines to synchronize without explicit locks or condition variables.

The example code sums the numbers in a slice, distributing the work between two goroutines.
Once both goroutines have completed their computation, it calculates the final result.

