package org.bagab.mcsvc.util.constants.countrycode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Map;
@Validated
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




    @GetMapping(path = "/findBy/{key}/{value}")
    public ResponseEntity<CountryCodeInfo> findBy(@PathVariable("key") @NotNull @CountryCodeFindKey String key,@PathVariable("value") @NotNull String value) {
        CountryCodeInfo countryCodeInfo   = null;
        switch (key) {
            case "alpha3":
                countryCodeInfo = repository.getInfoByAlpha3().get(value);
                break;
            case "alpha2":
                countryCodeInfo = repository.getInfoByAlpha2().get(value);
                break;
            case "name":
                countryCodeInfo = repository.getInfoByName().get(value);
                break;
            default:
                throw new IllegalArgumentException("Unexpected default in switch");

        }

        if (countryCodeInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(countryCodeInfo);
    }

}
