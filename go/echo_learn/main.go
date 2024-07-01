package main

import (
	"github.com/labstack/echo/v4"
	"net/http"
)

func main() {

	e := echo.New()

	e.GET("/", helloworld)

	e.Logger.Fatal(e.Start(":1323"))
}

func helloworld(c echo.Context) error {
	return c.String(http.StatusOK, "Hello, World!")
}
