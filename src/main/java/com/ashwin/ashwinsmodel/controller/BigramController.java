package com.ashwin.ashwinsmodel.controller;

import com.ashwin.ashwinsmodel.model.NeuralBigramModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BigramController {

    private final NeuralBigramModel neuralBigramModel;

    public BigramController(NeuralBigramModel neuralBigramModel) {
        this.neuralBigramModel = neuralBigramModel;
    }

    @PostMapping("/predict")
    public String predict(@RequestBody String word) {
        String current = word.trim();
        StringBuilder sentence = new StringBuilder(current);
        int count = 0;
        
        if (!current.endsWith(".")) {
            while (count < 10) {
                current = neuralBigramModel.predict(current);
                
                // Check if the model encountered an unknown word
                if (current != null && current.startsWith("Unknown word:")) {
                    sentence.append(" (word not present in vocabulary)");
                    break; // Stop the loop immediately instead of running 10 times
                }
                
                sentence.append(" ").append(current);
                count++;
                
                if (current != null && current.endsWith(".")) {
                    break;
                }
            }
        }
        return sentence.toString();
    }
}