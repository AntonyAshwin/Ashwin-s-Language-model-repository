package com.ashwin.ashwinsmodel.model;
import com.ashwin.ashwinsmodel.data.Tokenizer;

import java.util.ArrayList;
import java.util.HashSet;

import com.ashwin.ashwinsmodel.data.DatasetLoader;
public class NeuralBigramModel
{
    private double[][] bigramMatrix;
    private Tokenizer tokenizer;
    private DatasetLoader datasetLoader;
    private int uniqueVocabSize;

    public NeuralBigramModel()
    {
        datasetLoader = new DatasetLoader();
        datasetLoader.getDataSet();
        tokenizer = new Tokenizer(datasetLoader.getDataSet());
        uniqueVocabSize = tokenizer.getWordToIndex().size();
        bigramMatrix = new double[uniqueVocabSize][uniqueVocabSize];
        System.out.println("unique vocab size " + uniqueVocabSize);
        for(int i = 0; i < uniqueVocabSize; i++)
        {
            for(int j = 0; j < uniqueVocabSize; j++)
            {
                bigramMatrix[i][j] = Math.random();
                System.out.print(bigramMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Forward pass: dot input one-hot into bigramMatrix row, apply softmax
    public void trainModel()
    {
        ArrayList<HashSet<String>> pairs = datasetLoader.getWordPairs();

        for (HashSet<String> pair : pairs)
        {
            String[] words = pair.toArray(new String[0]);
            if (words.length < 2) continue;

            String inputWord  = words[0];
            String targetWord = words[1];

            Integer inputIdx  = tokenizer.getWordToIndex().get(inputWord);
            Integer targetIdx = tokenizer.getWordToIndex().get(targetWord);

            if (inputIdx == null || targetIdx == null) continue;

            // Forward propagation
            double[] logits = new double[uniqueVocabSize];
            for (int j = 0; j < uniqueVocabSize; j++)
            {
                logits[j] = bigramMatrix[inputIdx][j];
            }

            double[] probs = softmax(logits);

            System.out.println("Input: " + inputWord + " -> Target: " + targetWord
                + " | P(target) = " + probs[targetIdx]);
        }
    }

    // Softmax: convert raw logits to probability distribution
    public double[] softmax(double[] logits)
    {
        double max = logits[0];
        for (double v : logits)
            if (v > max) max = v;

        double sumExp = 0.0;
        double[] exps = new double[logits.length];
        for (int i = 0; i < logits.length; i++)
        {
            exps[i] = Math.exp(logits[i] - max); // subtract max for numerical stability
            sumExp += exps[i];
        }

        double[] probs = new double[logits.length];
        for (int i = 0; i < logits.length; i++)
            probs[i] = exps[i] / sumExp;

        return probs;
    }
}