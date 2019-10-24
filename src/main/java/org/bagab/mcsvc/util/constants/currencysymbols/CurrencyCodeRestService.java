package org.bagab.mcsvc.util.constants.currencysymbols;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/currencies",
        produces = "application/json")
public class CurrencyCodeRestService {

    CurrencyListRepository currencyListRepository;

    public CurrencyCodeRestService(CurrencyListRepository currencyListRepository) {
        this.currencyListRepository = currencyListRepository;
    }

    @GetMapping
    public Iterable<CurrencyCodeInfo> getAll() {
        return currencyListRepository.getInfoList();
    }

    @GetMapping("/{searchToken}")
    public ResponseEntity<CurrencyCodeInfo> search(@PathVariable("searchToken") String searchToken) {
        // TODO Bad request
        if (searchToken == null)
            return null;
        val token = searchToken.trim();
        val tokenLen = token.length();
        CurrencyCodeInfo countryCodeInfo;
        if (tokenLen == 3) {
            countryCodeInfo = currencyListRepository.getInfoByCode().get(token);
        } else {
            countryCodeInfo = currencyListRepository.getInfoByName().get(token);
        }
        if (countryCodeInfo != null) {
            return ResponseEntity.ok(countryCodeInfo);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
