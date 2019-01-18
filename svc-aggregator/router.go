package main

import (
	"encoding/json"
	mw "git.enova.com/go/mw"
	"github.com/golang/protobuf/proto"
	"github.com/gorilla/mux"
	"net/http"
	"strings"
)

func setupRoutes() *mux.Router {
	// var router = web.New()
	var router = mux.NewRouter()
	apiRouter := router.PathPrefix("/v1/").Subrouter()
	apiRouter.HandleFunc("/loan/{loanNumber}", lookupLoan).Methods("GET")
	// apiRouter.HandleFunc("/payments/{loanNumber}", lookupPayment).Methods("GET")

	logger.Println("Registered routes...")
	err := router.Walk(func(route *mux.Route, router *mux.Router, ancestors []*mux.Route) error {
		pathTemplate, _ := route.GetPathTemplate()
		methods, _ := route.GetMethods()
		if methods != nil {
			logger.Println("Route...", strings.Join(methods, ","), pathTemplate)
		}
		return nil
	})
	if err != nil {
		logger.Warn(err)
	}

	router.Use(mw.ContentType("application/json"))
	return router
}
func lookupLoan(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	var err error
	loanNumber, _ := params["loanNumber"]
	logger.Infof("Fetchig Loan Number: %s", loanNumber)
	loan, err := GetLoan(loanNumber)
	if err != nil {
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusInternalServerError)
		logger.Errorf("Failure getting loan: %s", err)
		json.NewEncoder(w).Encode(err)
	}
	data, err := proto.Marshal(loan)
	if err != nil {
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusInternalServerError)
		logger.Errorf("Failure marshaling proto: %s", err)
		json.NewEncoder(w).Encode(err)
	}
	w.Header().Set("Content-Type", "application/x-protobuf")
	_, err = w.Write(data)
	if err != nil {
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusInternalServerError)
		logger.Errorf("Failure writing response: %s", err)
		json.NewEncoder(w).Encode(err)
	}
}
