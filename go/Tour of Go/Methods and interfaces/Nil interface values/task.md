


A nil interface value holds neither value nor concrete type.

Calling a method on a nil interface is a run-time error because there is no
type inside the interface tuple to indicate which _concrete_ method to call.

