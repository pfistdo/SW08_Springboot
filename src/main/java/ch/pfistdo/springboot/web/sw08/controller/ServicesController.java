package ch.pfistdo.springboot.web.sw08.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ch.pfistdo.springboot.web.sw08.model.SentimentAnalysis;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ServicesController {

    private SentimentAnalysis analysis = new SentimentAnalysis();

    @GetMapping("/ping")
    public String ping() {
        return "Sentiment app is up and running!";
    }

    @GetMapping("/sentiment")
    public String predict(@RequestParam(name = "text", required = true) String text) throws Exception {
        var result = analysis.predict(text);
        return result.toJson();
    }

}