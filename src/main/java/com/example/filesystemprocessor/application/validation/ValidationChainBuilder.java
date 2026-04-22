package com.example.filesystemprocessor.application.validation;

import java.util.Set;

public class ValidationChainBuilder {

    public static ValidationHandler buildForInvoice(){
        ValidationHandler name = new NameValidator();
        ValidationHandler extension = new ExtensionValidator(Set.of(".xml", ".json"));
        ValidationHandler content = new ContentValidator(Set.of("customerId", "amount"));

        name.setNext(extension).setNext(content);
        return name;

    }

    public static ValidationHandler buildForContract() {
        ValidationHandler name = new NameValidator();
        ValidationHandler extension = new ExtensionValidator(Set.of(".pdf"));
        ValidationHandler content = new ContentValidator(Set.of("clientName", "signed=true"));

        name.setNext(extension).setNext(content);
        return name;
    }

    public static ValidationHandler buildForReport() {
        ValidationHandler name      = new NameValidator();
        ValidationHandler extension = new ExtensionValidator(Set.of(".csv", ".xlsx"));
        ValidationHandler rows      = new MinRowsValidator(3);

        return name.setNext(extension).setNext(rows);

    }

}
