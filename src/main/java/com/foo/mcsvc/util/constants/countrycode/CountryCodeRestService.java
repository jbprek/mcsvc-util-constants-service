package com.foo.mcsvc.util.constants.countrycode;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/countries",
        produces = "application/json")
public class CountryCodeRestService {

    private CountryListRepository repository;

    public CountryCodeRestService(CountryListRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<CountryCodeInfo> getAll() {
        return repository.getInfoList();
    }

    @GetMapping("/{searchToken}")
    public ResponseEntity<CountryCodeInfo> search(@PathVariable("searchToken") String searchToken) {
        if (searchToken == null)
            return null;
        val token = searchToken.trim();
        val tokenLen = token.length();
        CountryCodeInfo countryCodeInfo;
        if (tokenLen == 2) {
            countryCodeInfo = repository.getInfoByAlpha2().get(token);
        } else if (tokenLen == 3) {
            countryCodeInfo = repository.getInfoByAlpha3().get(token);
        } else {
            countryCodeInfo = repository.getInfoByName().get(token);
        }
        if (countryCodeInfo != null) {
            return ResponseEntity.ok(countryCodeInfo);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
