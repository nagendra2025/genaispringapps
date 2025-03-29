package com.ai.SpringAiDemo.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class WishesService {
    private final ChatModel chatModel;

    public WishesService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createWishes() {
        var template = """
        		Give me the name of a world-famous celebrity and a world-renowned scientist who were born on today’s date {todaysdate}. 
        		Also provide one inspirational quote from each of them. Format the output line by line as follows:
        		Celebrity Name:
        		Celebrity Quote:
        		Scientist Name:
        		Scientist Quote:
        		Make sure the names and quotes are accurate and relevant to today’s date.
                   """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
       
        
        promptTemplate.add("todaysdate",new SimpleDateFormat("dd-MM").format(new Date()));
    
        return chatModel.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

}
