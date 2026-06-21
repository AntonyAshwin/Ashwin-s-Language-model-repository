package com.ashwin.ashwinsmodel;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ashwin.ashwinsmodel.data.Tokenizer;

import java.util.HashMap;
import java.util.Scanner;

import com.ashwin.ashwinsmodel.model.NeuralBigramModel;	
import com.ashwin.ashwinsmodel.data.DatasetLoader;	
// @SpringBootApplication
public class AshwinsmodelApplication {

	public static void main(String[] args) {
		// SpringApplication.run(AshwinsmodelApplication.class, args);
		// DatasetLoader datasetLoader = new DatasetLoader();
		// String trainingData = datasetLoader.getDataSet();
		// Tokenizer tokenizer = new Tokenizer(trainingData);
		// HashMap<String, Integer> wordToIndex = tokenizer.getWordToIndex();
		// System.out.println(wordToIndex);
		// int[] tokens = tokenizer.getTokens("the cat sat on the mat mat mat mat");
		// for(int token : tokens)
		// 	System.out.print(token + " ");
		NeuralBigramModel neuralBigramModel = new NeuralBigramModel();
		neuralBigramModel.trainModel();
		Scanner sc = new Scanner(System.in);
		while (true) {
			String current = sc.nextLine();
			StringBuilder sentence = new StringBuilder(current);
			int count = 0;
			if (!current.endsWith(".")) {
				while (count < 10) {
					current = neuralBigramModel.predict(current);
					if (current.startsWith("Unknown word:")) {
						break;
					}
					sentence.append(" ").append(current);
					count++;
					if (current.endsWith(".")) break;
				}
			}
			System.out.println(sentence.toString());
		}
	}

}
