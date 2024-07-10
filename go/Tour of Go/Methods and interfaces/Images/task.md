


[Package image](https://go.dev/pkg/image/#Image) defines the `Image` interface:

	package image

	type Image interface {
		ColorModel() color.Model
		Bounds() Rectangle
		At(x, y int) color.Color
	}

*Note*: the `Rectangle` return value of the `Bounds` method is actually an
[`image.Rectangle`](https://go.dev/pkg/image/#Rectangle), as the
declaration is inside package `image`.

(See [the documentation](https://go.dev/pkg/image/#Image) for all the details.)

The `color.Color` and `color.Model` types are also interfaces, but we'll ignore that by using the predefined implementations `color.RGBA` and `color.RGBAModel`. These interfaces and types are specified by the [image/color package](https://go.dev/pkg/image/color/)

