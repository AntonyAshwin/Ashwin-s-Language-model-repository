class Tokenizer {

    HashMap<String, Integer> wordToIndex = new HashMap<>();
    LinkedHashMap<Integer, String> indexToWord = new LinkedHashMap<>();
    int tokenCounter  = 0;

    public Tokenizer(String trainingData) {
       createVocabalary(trainingData);
    }

   public void createVocabalary(String response)
   {
        String[] words = response.split(" "); 
        for(String word : words)
        {
            if(!wordToIndex.containsKey(word)) {
            wordToIndex.putIfAbsent(word, tokenCounter);
            indexToWord.putIfAbsent(tokenCounter, word);
            tokenCounter++;
            }  
        }
   }

   public int[] getTokens(String sentence)
   {
    int i = 0;
    int[] tokens = new int[sentence.split(" ").length];
        for(String word: sentence.split(" "))
         tokens[i++] = wordToIndex.getOrDefault(word, 0);
    return tokens;
   } 
}