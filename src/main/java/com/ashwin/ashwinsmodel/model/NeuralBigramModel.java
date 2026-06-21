package com.ashwin.ashwinsmodel.model;
import com.ashwin.ashwinsmodel.data.Tokenizer;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.ashwin.ashwinsmodel.data.DatasetLoader;

@Component
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

    private static final double LEARNING_RATE = 0.01;

    @PostConstruct
    public void trainModel()
    {
        ArrayList<String[]> pairs = datasetLoader.getWordPairs();

        for (int epoch = 0; epoch < 100; epoch++)
        {
            double totalLoss = 0.0;

            for (String[] pair : pairs)
            {
                if (pair.length < 2) continue;

                String inputWord  = pair[0];
                String targetWord = pair[1];

                Integer inputIdx  = tokenizer.getWordToIndex().get(inputWord);
                Integer targetIdx = tokenizer.getWordToIndex().get(targetWord);

                if (inputIdx == null || targetIdx == null) continue;
                double[] logits = new double[uniqueVocabSize];
                for (int j = 0; j < uniqueVocabSize; j++)
                    logits[j] = bigramMatrix[inputIdx][j];

                double[] probs = softmax(logits);

                double loss = computeLoss(probs, targetIdx);
                totalLoss += loss;

                double[] gradLogits = computeGradients(probs, targetIdx);
                updateWeights(inputIdx, gradLogits);
            }

            System.out.println("Epoch " + epoch + " | Avg Loss = " + totalLoss / pairs.size());
        }
    }
    
        public String predict(String inputWord) {
        Integer inputIdx = tokenizer.getWordToIndex().get(inputWord);
        if (inputIdx == null) {
            return "NOT_IN_VOCABULARY:" + inputWord;
        }

        double[] logits = new double[uniqueVocabSize];
        for (int j = 0; j < uniqueVocabSize; j++)
            logits[j] = bigramMatrix[inputIdx][j];

        double[] probs = softmax(logits);

        int bestIdx = 0;
        for (int j = 1; j < uniqueVocabSize; j++)
            if (probs[j] > probs[bestIdx]) bestIdx = j;

        return tokenizer.getIndexToWord().get(bestIdx);
    }

    public double computeLoss(double[] probs, int targetIdx)
    {
        return -Math.log(probs[targetIdx] + 1e-10); 
    }

    public double[] computeGradients(double[] probs, int targetIdx)
    {
        double[] grad = new double[probs.length];
        for (int j = 0; j < probs.length; j++)
            grad[j] = probs[j];
        grad[targetIdx] -= 1.0;
        return grad;
    }

    public void updateWeights(int inputIdx, double[] gradLogits)
    {
        for (int j = 0; j < uniqueVocabSize; j++)
            bigramMatrix[inputIdx][j] -= LEARNING_RATE * gradLogits[j];
    }


    public double[] softmax(double[] logits)
    {
        double max = logits[0];
        for (double v : logits)
            if (v > max) max = v;

        double sumExp = 0.0;
        double[] exps = new double[logits.length];
        for (int i = 0; i < logits.length; i++)
        {
            exps[i] = Math.exp(logits[i] - max); 
            sumExp += exps[i];
        }

        double[] probs = new double[logits.length];
        for (int i = 0; i < logits.length; i++)
            probs[i] = exps[i] / sumExp;

        return probs;
    }
}