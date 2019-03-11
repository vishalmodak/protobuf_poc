package main

import (
	"encoding/json"
	"github.com/golang/protobuf/proto"
	any "github.com/golang/protobuf/ptypes/any"
	"github.com/gorilla/mux"
	"net/http"
	"protobuf_poc/svc-aggregator/generated-protos"
	"strings"
)

func setupRoutes() *mux.Router {
	// var router = web.New()
	var router = mux.NewRouter()
	apiRouter := router.PathPrefix("/v1/").Subrouter()
	apiRouter.HandleFunc("/loan/{loanNumber}", lookupLoan).Methods("GET")
	apiRouter.HandleFunc("/prospect/{firstName}", lookupProspect).Methods("GET")

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

func lookupProspect(w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)
	acceptHeader := r.Header.Get("Accept")

	var err error
	firstName, _ := params["firstName"]
	logger.Infof("Fetchig Prospect: %s", firstName)
	business := com_lending_proto.Business{Name: "Nocode Inc", Naics: "NACIS1234", Fein: "FEIN6789"}
	businessProspect := com_lending_proto.Prospect_Business{&business}
	person := com_lending_proto.Person{FirstName: "Joe"}
	personProspect := com_lending_proto.Prospect_Person{&person}
	prospect := com_lending_proto.Prospect{ProspectType: &businessProspect}
	prospect.ProspectType = &personProspect
	bytes, _ := proto.Marshal(&business)
	prospect.AnyProspect = &any.Any{Value: bytes}
	if err != nil {
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusInternalServerError)
		logger.Errorf("Failure getting loan: %s", err)
		json.NewEncoder(w).Encode(err)
	}
	if acceptHeader == "application/json" {
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusOK)
		json.NewEncoder(w).Encode(prospect)
	} else {
		w.Header().Set("Content-Type", "application/x-protobuf")
		data, err := proto.Marshal(&prospect)
		if err != nil {
			w.Header().Set("Content-Type", "application/json")
			w.WriteHeader(http.StatusInternalServerError)
			logger.Errorf("Failure marshaling proto: %s", err)
			json.NewEncoder(w).Encode(err)
		}
		_, err = w.Write(data)
		if err != nil {
			w.Header().Set("Content-Type", "application/json")
			w.WriteHeader(http.StatusInternalServerError)
			logger.Errorf("Failure writing response: %s", err)
			json.NewEncoder(w).Encode(err)
		}
	}
}
