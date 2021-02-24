package com.ai.cbp;

import java.util.List;
import java.util.stream.Collectors;

public class CBPDomainInteger implements CBPDomain<Integer> {

    private List<CBPDomainValue<Integer>> domain;

    public CBPDomainInteger(List<Integer> domain) {
        this.domain = domain.stream().map(CBPDomainValue::new).collect(Collectors.toList());
    }

    @Override
    public List<CBPDomainValue<Integer>> getDomainValues() {
        return domain.stream().filter(value -> !value.isRemoved()).collect(Collectors.toList());
    }

    @Override
    public void removeDomainValue(Integer value) {
        for (CBPDomainValue<Integer> domainValue : domain) {
            if (domainValue.getValue().equals(value)) {
                domainValue.markRemoved();
                return;
            }
        }
    }
}
