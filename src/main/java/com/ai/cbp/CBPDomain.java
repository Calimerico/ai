package com.ai.cbp;

import java.util.List;
import java.util.stream.Collectors;

public class CBPDomain<T> {

    private final List<CBPDomainValue<T>> domain;

    public CBPDomain(List<T> domain) {
        this.domain = domain.stream().map(CBPDomainValue::new).collect(Collectors.toList());
    }

    public List<CBPDomainValue<T>> getDomainValues() {
        return domain.stream().filter(value -> !value.isRemoved()).collect(Collectors.toList());
    }

    public void removeDomainValue(T value) {
        for (CBPDomainValue<T> domainValue : domain) {
            if (domainValue.getValue().equals(value)) {
                domainValue.markRemoved();
                return;
            }
        }
    }
}
