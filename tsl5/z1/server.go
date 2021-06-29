package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
    http.HandleFunc("/", handler)
    fmt.Printf("Server listening at port 8000\n")
	if err := http.ListenAndServe(":8000", nil); err != nil {
		log.Fatal(err)
	}
} 

func handler(w http.ResponseWriter, r *http.Request) {
    r.Header.Write(w)
}

