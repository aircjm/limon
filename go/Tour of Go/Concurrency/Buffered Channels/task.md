


Channels can be _buffered_.  Provide the buffer length as the second argument to `make` to initialize a buffered channel:

	ch := make(chan int, 100)

Sends to a buffered channel block only when the buffer is full. Receives block when the buffer is empty.

Modify the example to overfill the buffer and see what happens.

