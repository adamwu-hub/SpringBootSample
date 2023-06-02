package com.example.core.service;

import com.example.core.drivingPort.IMessageService;
import com.example.domain.Message;
import com.example.domain.MyAppProperties;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements IMessageService {

    private final MyAppProperties _appProperties;

    MessageService(MyAppProperties appProperties) {
        _appProperties = appProperties;    
    }
    
    @Override
    public Message getMessage(String content) {
        return new Message(String.format("%s %s %s",
                _appProperties.getName(),
                _appProperties.getVersion(),
                content));
    }
}
