package com.ashwin.ashwinsmodel.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DatasetLoader {


    String trainingData;
    ArrayList<HashSet<String>> pairs = new ArrayList<HashSet<String>>();

    public String getDataSet() {
        // for now this string later swap with get call
        trainingData = """
        the mat sat on the cat.
        the cat sat on the mat.
        the dog sat on the rug.
        the cat chased the bird.
        the dog chased the cat.
        the bird flew over the mat.
        a cat is on the rug.
        a dog is on the mat.
        the boy saw the cat.s
        the girl saw the dog.
        the boy liked the dog.
        the girl liked the cat.
        """;
        return trainingData;
    }

    public ArrayList<HashSet<String>> getWordPairs()
    {
        String[] words = getwordArray();
        for(int i = 0; i < words.length; i++)
        {
            if(i >= words.length - 1)
                break;
            pairs.add(new HashSet<>(Arrays.asList(words[i], words[i+1])));
            if(words[i+1].charAt(words[i+1].length() -  1) == '.')
                 i++;
        }
        return pairs;
    }

    public String[] getwordArray()
    {
        return trainingData.split(" ");
    }
}
