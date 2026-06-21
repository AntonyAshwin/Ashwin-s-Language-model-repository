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

        if (current.isEmpty()) {
            return "";
        }

        if (neuralBigramModel.predict(current).startsWith("NOT_IN_VOCABULARY:")) {
            return "NOT_IN_VOCABULARY:" + current;
        }

        StringBuilder sentence = new StringBuilder(current);
        int count = 0;
        
        if (!current.endsWith(".")) {
            while (count < 10) {
                current = neuralBigramModel.predict(current);
                
                if (current != null && current.startsWith("NOT_IN_VOCABULARY:")) {
                    return current;
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