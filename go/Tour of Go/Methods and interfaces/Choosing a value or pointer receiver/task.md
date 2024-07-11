


There are two reasons to use a pointer receiver.

The first is so that the method can modify the value that its receiver points to.

The second is to avoid copying the value on each method call.
This can be more efficient if the receiver is a large struct, for example.

In this example, both `Scale` and `Abs` are methods with receiver type `*Vertex`,
even though the `Abs` method needn't modify its receiver.

In general, all methods on a given type should have either value or pointer
receivers, but not a mixture of both.
(We'll see why over the next few pages.)

