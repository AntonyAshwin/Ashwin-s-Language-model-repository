package com.ashwin.ashwinsmodel.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class DatasetLoader {

    String trainingData;
    String trainingData2;

    public String getDataSet() {
        // for now this string later swap with get call
        trainingData = "Twinkle, twinkle, little star, how I wonder what you are. Up above the world so high, like a diamond in the sky. When the blazing sun is gone, when he nothing shines upon, then you show your little light, twinkle, twinkle, all the night. Then the traveler in the dark thanks you for your tiny spark, he could not see which way to go, if you did not twinkle so. In the dark blue sky you keep, and often through my curtains peep, for you never shut your eye, till the sun is in the sky. As your bright and tiny spark lights the traveler in the dark, though I know not what you are, twinkle, twinkle, little star. Jack and Jill went up the hill to fetch a pail of water. Jack fell down and broke his crown, and Jill came tumbling after. Up Jack got, and home did trot, as fast as he could caper, to old Dame Dob, who patched his knob with vinegar and brown paper. Then Jill came in, and she did grin, to see his paper plaster, her mother, vexed, did whip her next, for causing Jack s disaster. Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall. All the king s horses and all the king s men, couldn t put Humpty together again. Baa, baa, black sheep, have you any wool. Yes, sir, yes, sir, three bags full. One for the master, and one for the dame, and one for the little boy who lives down the lane. Mary had a little lamb, its fleece was white as snow. And everywhere that Mary went, the lamb was sure to go. It followed her to school one day, which was against the rule. It made the children laugh and play to see a lamb at school. And so the teacher turned it out, but still it lingered near, and waited patiently about till Mary did appear. Why does the lamb love Mary so, the eager children cry. Why, Mary loves the lamb, you know, the teacher did reply. Old Mother Hubbard went to the cupboard, to give her poor dog a bone, but when she got there the cupboard was bare, and so the poor dog had none. She went to the baker s to buy him some bread, but when she came back the poor dog was dead. She went to the undertaker s to buy him a coffin, but when she came back the dog was laughing. She took a clean dish to get him some tripe, but when she came back he was smoking a pipe. She went to the alehouse to get him some beer, but when she came back the dog sat in a chair. She went to the tavern to buy him some wine, but when she came back the dog was polite and fine. She went to the hatter s to buy him a hat, but when she came back he was feeding the cat. She went to the barber s to buy him a wig, but when she came back he was dancing a jig. She went to the cobbler s to buy him some shoes, but when she came back he was reading the news. The cat and the fiddle, the cow jumped over the moon. The little dog laughed to see such sport, and the dish ran away with the spoon. Hickety, pickety, my black hen, she lays eggs for gentlemen. Gentlemen come every day, to see what my black hen doth lay. Sometimes nine and sometimes ten, hickety, pickety, my black hen. Hickory, dickory, dock, the mouse ran up the clock. The clock struck one, the mouse ran down, hickory, dickory, dock. The clock struck two, the mouse said boo, hickory, dickory, dock. The clock struck three, the mouse went free, hickory, dickory, dock. The clock struck four, the mouse said no more, hickory, dickory, dock. Little Bo Peep has lost her sheep, and can t tell where to find them. Leave them alone, and they ll come home, bringing their tails behind them. Little Bo Peep fell fast asleep, and dreamt she heard them bleating, but when she awoke, she found it a joke, for they were still fleeting. Then up she took her little crook, determined for to find them, Normally I can help with things like this, but I don't seem to have access to that content. You can try again or ask me for something else.";
        trainingData2 = " the morning sun rose above the hills and the birds began to sing, people walked along quiet roads and children played in open fields, a gentle breeze moved across the grass and carried the scent of flowers, farmers worked through the day and travelers continued their journeys, the sky remained clear and bright, clouds appeared slowly in the distance and drifted across the horizon, evening arrived with calm colors and long shadows, families gathered together and shared stories, the stars appeared one by one in the dark sky, the night remained peaceful and still. the next day began much the same way, the town awoke early and activity returned to the streets, merchants opened their shops and customers arrived throughout the morning, students studied lessons and practiced new skills, workers completed tasks and planned future projects, time passed steadily and seasons changed, years went by and memories accumulated, knowledge grew through experience and effort, success followed patience and persistence, challenges appeared from time to time but people adapted and continued forward, progress was made step by step and goal by goal, the story continued without end.";
        return trainingData + trainingData2;
    }

    public ArrayList<String[]> getWordPairs()
    {
        String[] words = getwordArray();
        ArrayList<String[]> pairs = new ArrayList<>();
        for(int i = 0; i < words.length - 1; i++)
        {
            pairs.add(new String[] { words[i], words[i+1] });
            if(words[i+1].charAt(words[i+1].length() -  1) == '.')
                 i++;
        }
        return pairs;
    }

    public String[] getwordArray()
    {
        return Arrays.stream(trainingData.split("\\s+"))
                     .map(String::trim)
                     .filter(w -> !w.isEmpty())
                     .toArray(String[]::new);
    }
}
