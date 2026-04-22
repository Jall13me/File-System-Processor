package com.example.filesystemprocessor.application.notification;

public class SmsNotifier extends AbstractNotifier {

    @Override
    public NotifierType getType(){
        return NotifierType.SMS;
    }

    @Override
    protected void doNotify(String formattedMessage){
        System.out.println("[SMS]" + formattedMessage);
    }

}
