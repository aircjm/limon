


Remember the [picture generator](course://%20Methods%20and%20interfaces/Exercise%3A%20Images/task.go) you wrote earlier? Let's write another one, but this time it will return an implementation of `image.Image` instead of a slice of data.

Define your own `Image` type, implement [the necessary methods](https://go.dev/pkg/image/#Image), and call `pic.ShowImage`.

`Bounds` should return a `image.Rectangle`, like `image.Rect(0, 0, w, h)`.

`ColorModel` should return `color.RGBAModel`.

`At` should return a color; the value `v` in the last picture generator corresponds to `color.RGBA{v, v, 255, 255}` in this one.

<sub>**Note:** if the dependency in the _import_ section is highlighted in red, click the dependency, press <span class="shortcut">&shortcut:ShowIntentionActions;</span> and select **Sync dependencies of main**.</sub>

<div class="hint" title="Click to see possible solution">

    package main
    
    import (
    	"image"
    	"image/color"
    
    	"golang.org/x/tour/pic"
    )
    
    type Image struct {
    	Height, Width int
    }
    
    func (m Image) ColorModel() color.Model {
    	return color.RGBAModel
    }
    
    func (m Image) Bounds() image.Rectangle {
    	return image.Rect(0, 0, m.Height, m.Width)
    }
    
    func (m Image) At(x, y int) color.Color {
    	c := uint8(x ^ y)
    	return color.RGBA{c, c, 255, 255}
    }
    
    func main() {
    	m := Image{256, 256}
    	pic.ShowImage(m)
    }
    
</div>
