package com.ashwin.ashwinsmodel.data;

public class DatasetLoader {

    public String getDataSet() {
        // for now this string later swap with get call
        String trainingData = """
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

}
