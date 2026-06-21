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
        int unknownCount = 0;
        if (!current.endsWith(".")) {
            while (count < 10) {
                current = neuralBigramModel.predict(current);
                if (current.startsWith("Unknown word:")) {
                    unknownCount++;
                    if (unknownCount >= 10) break;
                }
                sentence.append(" ").append(current);
                count++;
                if (current.endsWith(".")) break;
            }
        }
        return sentence.toString();
    }
}
