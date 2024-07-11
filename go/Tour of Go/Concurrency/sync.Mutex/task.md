


We've seen how channels are great for communication among goroutines.

But what if we don't need communication? What if we just want to make sure only
one goroutine can access a variable at a time to avoid conflicts?

This concept is called _mutual_exclusion_, and the conventional name for the data structure that provides it is _mutex_.

Go's standard library provides mutual exclusion with
[`sync.Mutex`](https://go.dev/pkg/sync/#Mutex) and its two methods:

- `Lock`
- `Unlock`

We can define a block of code to be executed in mutual exclusion by surrounding it
with a call to `Lock` and `Unlock` as shown on the `Inc` method.

We can also use `defer` to ensure the mutex will be unlocked as in the `Value` method.

