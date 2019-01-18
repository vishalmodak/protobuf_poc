package main

import (
	"github.com/golang/protobuf/proto"
	"io/ioutil"
	"net/http"
	"protobuf_poc/svc-aggregator/generated-protos"
	"time"
)

//LoanClient interface
type LoanClient interface {
	GetLoan(loanNumber string) (loan *com_lending_proto.Loan, err error)
}

//GetLoan fetches loan data from Loan Service
func GetLoan(loanNumber string) (loan *com_lending_proto.Loan, err error) {
	loan = new(com_lending_proto.Loan)
	loanClient := &http.Client{
		Timeout: time.Second * 2, // Maximum of 2 secs
	}

	loanURL += "/" + loanNumber
	req, err := http.NewRequest(http.MethodGet, loanURL, nil)
	if err != nil {
		logger.Errorf("Failure building loan request: %s", err)
		return loan, err
	}
	q := req.URL.Query()            // Get a copy of the query values.
	q.Add("loanNumber", loanNumber) // Add a new value to the set.
	req.URL.RawQuery = q.Encode()   // Encode and assign back to the original query.
	req.Header.Set("Accept", "application/x-protobuf")
	res, err := loanClient.Do(req)
	if err != nil {
		logger.Errorf("Failure invoking %s due to: %s", loanURL, err)
		return loan, err
	}
	logger.Debugf("%#v", res)
	if res.StatusCode != 200 {
		logger.Errorf("Failure invoking %s due to error HTTP Status: %s", loanURL, http.StatusText(res.StatusCode))
		return loan, err
	}
	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		logger.Errorf("Failure converting response body to byte[]: %s", err)
		return loan, err
	}
	logger.Debugf("ResponseBody: %s", string(body))
	err = proto.Unmarshal(body, loan)
	if err != nil {
		logger.Errorf("Failure unmarshaling proto: %s", err)
		return loan, err
	}
	return loan, nil
}
