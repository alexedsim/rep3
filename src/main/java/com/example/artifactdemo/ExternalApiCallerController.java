package com.example.artifactdemo;

import DB.DBSavedTransaction;

import ErrorHandling.TransactionException;
import ErrorHandling.TransactionResponseError;
import Repository.TransactionRepository;
import model.*;
import model.Error;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/callExternalApi")
public class ExternalApiCallerController {

    private final TransactionRepository repository = new TransactionRepository();

    @GetMapping(value="/accounts")
    public ResponseEntity<String> callExternalApi( ) {
        String url="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema", "S2S");
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        HttpEntity<Object> entity=new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response;
    }



    @GetMapping(value="/balance/{accountId}")
    public ResponseBalance getBalance(@PathVariable("accountId") String accountId ) {

        String url="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountId+"/balance";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema", "S2S");
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");


        HttpEntity<Object> entity=new HttpEntity<>(headers);


        ResponseEntity<Balance> response = restTemplate.exchange(url, HttpMethod.GET, entity, Balance.class);
        ResponseBalance responseBalance = new ResponseBalance();
        responseBalance.setBalance(response.getBody().getPayload().getBalance());


        return responseBalance;
    }


    //Assente gestione errori ed implementazione Payload per la response
    @PostMapping(value="/createMoneyTransfer/{accountId}")
    public ResponseEntity<ResponseBonifico> createMoneyTransfer(@PathVariable("accountId") String accountId, @RequestBody DatiBonifico datiBonifico) throws TransactionException {

        String url="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountId+"/payments/money-transfers";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema", "S2S");
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.add("X-Time-Zone", "Europe/Rome");


        HttpEntity<DatiBonifico> entity = new HttpEntity<>(datiBonifico, headers);


        ResponseEntity<ResponseBonifico> response = restTemplate.exchange(url, HttpMethod.POST, entity, ResponseBonifico.class);


        return response;
    }


    @GetMapping(value="/transactions/{accountId}")
    public Transactions getAccountTransactions(@PathVariable("accountId") String accountId, @PathParam("fromAccountingDate") String fromAccountingDate, @PathParam("toAccountingDate") String toAccountingDate) {

        String url="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/"+accountId+"/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth-Schema", "S2S");
        headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");


        HttpEntity<Object> entity=new HttpEntity<>(headers);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fromAccountingDate", fromAccountingDate);
        uriVariables.put("toAccountingDate", toAccountingDate);

        ResponseEntity<TransactionsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TransactionsResponse.class,uriVariables);
        Transactions transactions = new Transactions();
        transactions.setList(response.getBody().getPayload().getList());

        if(!response.getBody().getPayload().getList().isEmpty()){
           for(Transaction t : transactions.getList()) {
               repository.save(new DBSavedTransaction(t));
           }

        }

        return transactions;
    }




}
