package com.ai.cbp;

import java.util.List;

public interface CBPDomain <T> {
    List<CBPDomainValue<T>> getDomainValues();
    void removeDomainValue(T value);
}
