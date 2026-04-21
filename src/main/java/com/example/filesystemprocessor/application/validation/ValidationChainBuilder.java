package com.example.filesystemprocessor.application.validation;

public class ValidationChainBuilder {

    public static ValidationHandler build(){
        ValidationHandler nameValidator = new NameValidator();
        ValidationHandler extensionValidator = new ExtensionValidator();
        ValidationHandler contentValidator = new ContentValidator();

        nameValidator.setNext(extensionValidator).setNext(contentValidator);

        return nameValidator;

    }

}
