package com.ashwin.ashwinsmodel.model;
import com.ashwin.ashwinsmodel.data.Tokenizer; 
import com.ashwin.ashwinsmodel.data.DatasetLoader;
public class NeuralBigramModel
{
    public NeuralBigramModel()
    {
        DatasetLoader datasetLoader = new DatasetLoader();  
        Tokenizer tokenizer = new Tokenizer(datasetLoader.getDataSet());
        int uniqueVocabSize = tokenizer.getWordToIndex().size();
        double [][] bigramMatrix = new double[uniqueVocabSize][uniqueVocabSize];
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
}