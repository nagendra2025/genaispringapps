package com.ai.SpringAiDemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.SpringAiDemo.service.ChatService;
import com.ai.SpringAiDemo.service.ImageService;
import com.ai.SpringAiDemo.service.RecipeService;
import com.ai.SpringAiDemo.service.WishesService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;

@RestController
@Validated
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;
    private final WishesService wishesService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService,
    		WishesService wishesService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
        this.wishesService = wishesService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam @NotBlank (message="Prompt is required" ) String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam @NotBlank (message="Prompt is required") String prompt,
    								@RequestParam(defaultValue = "gpt-4o") String model,
    								@RequestParam(defaultValue = "0.4F") float temp){
        return chatService.getResponseOptions(prompt,model, temp);
    }


    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam  @NotBlank (message="Prompt is required") String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "3") int n,
                                       @RequestParam(defaultValue = "1024") int width,
                                       @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);

        // Streams to get urls from ImageResponse
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam  @NotBlank (message="Ingredients are required") String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestriction) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }
    
    @GetMapping("wishes")
    public String wishesGenerator() throws InterruptedException {
        return wishesService.createWishes();
    }
}
