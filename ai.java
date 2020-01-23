//Mark Valeriani 
import java.util.*;

public class ai{
    private static boolean mem = false;
    private static int member = 0; 
    public static void main(final String Args[]) {
        String response = ""; // what the user is saying // to end the conversation type stop
        Scanner user = new Scanner(System.in); // to read what the user says
        String[] sentence; 
        System.out.println("Start by saying Hello.");

        while (!response.equals("continue")) {
            System.out.print("User: ");
            response = user.nextLine();// the users response
            sentence = response.toLowerCase().split(" ");
            if (sentence[0].equals("")) // check if it is empty first so it doesn't run through all the code 
                System.out.print("Please type to continue! \n");
            else
                System.out.printf(eliza(response, sentence)); // what eliza is going to respond with
        }

    }
    public static String eliza(String response, String[] sentence){
        Random rand = new Random(); 
        int length = response.length()-1;
        int countresponses = 0; // to count the responses in the array 
        String[] elizaRespond = new String[10];//making sure space between user and eliza 
        System.out.print("Eliza: ");//where eliza talks 
        String [] family = {"dad", "mom", "brother", "sister", "mother", "father", "girlfriend", "boyfriend", "girl", "boy", "sis", "bro"};
        String [] badWords = {"darn", "damn", "hell", "jerk", "ass", "shit", "bs"};

        char reader = response.charAt(length);
        boolean negResponse = false; // if nothing matches up we will continue the conversation

        if (sentence[0].equals("hello") || sentence[0].equals("hi" )){ // saying hello to the person or the start of the conversation 
            return  "Hello how are you, lets talk! \n"; 
        }
        if (mem == true){
            return shortTerm(member, family);
        }
        for (int s =0; s <= sentence.length-1; s++){  // runs through the sentence to see if it matches any type of bad word 
            for (int t=0; t<family.length-1; t++){
                if(family[t].equals(sentence[s])){
                    mem = true;
                    member = t; 
                }
            }
        }
        for (int b =0; b <= sentence.length-1; b++){  // runs through the sentence to see if it matches any type of bad word 
            for (int w=0; w<badWords.length-1; w++){
                if(badWords[w].equals(sentence[b])){
                    return "Lets not speak like that here...Thank you.\n";
                }
            }
        }
        if (reader =='?'){ // has a character reader read the last character to tell if it is a sentence 
            int pickResponse = rand.nextInt(3);
            switch (pickResponse) {
                case 1:
                    elizaRespond[countresponses] = "Have you ever asked questions like that?\n";
                    countresponses++;
                    negResponse = true;
                    break;
                case 2: 
                    elizaRespond[countresponses] = "Why do you ask questions like that?\n";
                    countresponses++;
                    negResponse = true;
                default:
                    elizaRespond[countresponses] = "What will answering these questions do for you?\n";
                    negResponse = true;
                    countresponses++;
                    break;
            }
        } 
        for  (int i =  0; i <= sentence.length-1; i++){ // going to be the main loop to run through all cases 
            
            if (sentence[i].equals("yes")){
                elizaRespond[countresponses] =  " You sound pretty positive, contuinue.\n"; // focusing on the yes and no 
                countresponses++;
                negResponse = true;
            } 
            if (sentence[i].equals("no")){
                elizaRespond[countresponses] = "You sound pretty negative, contuinue.\n";// focusing on the yes and no 
                countresponses++;
                negResponse = true;
            }
            if (sentence[i].equals("name") || sentence[i].equals("names") || sentence[i].equals("named") ){
                elizaRespond[countresponses] = "We don't need to mention any names, just continue talking.\n";
                countresponses++;    // making sure they don't have to mention any names, but it is still going to be a random eliza answer  
                negResponse = true;
            }
            
            if (i != sentence.length-1){
                if (sentence[i].equals("i") && sentence[i+1].equals("need") || sentence[i].equals("i") && sentence[i+1].equals("want"))  {
                    int picks = rand.nextInt(4); //working with what the user would want or need in their life 
                    switch (picks){
                        case 0:
                            elizaRespond[countresponses] = "Why do you desire such things?\n";
                            countresponses++;
                            negResponse = true;
                            break;
                        case 1:
                            elizaRespond[countresponses] = "Would this really complete you?\n";
                            countresponses++;
                            negResponse = true;
                            break;
                        case 2: 
                            elizaRespond[countresponses] = "Why do you want ";
                            for(int u = i; u<=sentence.length-1; u++){
                                elizaRespond[countresponses] += sentence[u] + " ";
                            }
                            elizaRespond[countresponses] += " \n";
                            countresponses++;
                            break;
                        default:
                            elizaRespond[countresponses] = "I am not sure this would really fulfill you?\n";
                            countresponses++;
                            negResponse = true;
                            break; 
                        }
                }
            }
            
            if (sentence[i].equals("i") && sentence[i+1].equals("am")){
                try {
                    if(sentence[i+2].equals("good") || sentence[i+2].equals("bad")){ // if the sentence says i am it follows whether you are good or bad 
                        elizaRespond[countresponses] = "Why are you " + sentence [i+2] +  "?\n"; //if you don't say good or bad it moves on to say why are you whatever you are 
                        countresponses++;
                        negResponse = true;
                    }
                }
                catch (Exception e){
                    elizaRespond[countresponses] = "Go ahead and tell me more...\n"; // just a filler if this doesn't match 
                    countresponses++;
                    negResponse = true;
                    break;
                }
                
                if (sentence.length-1 <= 8){
                    elizaRespond[countresponses] = "Did you come to talk because you are "; // asks why you feel a certain way 
                    for (int j = 2;   j <= sentence.length-1; j++){
                        elizaRespond[countresponses] += sentence[j] + " "; 
                    }  
                    elizaRespond[countresponses] += "?\n";
                    countresponses++;
                    negResponse = true;
                }
                if (sentence.length-1 > 8){
                    elizaRespond[countresponses] = "Why do you think you "; // if it is more than 8 words it asks you this way 
                    for (int j = 2; j <= sentence.length-1; j++){
                        elizaRespond[countresponses] += sentence[j] + " "; 
                    }
                    elizaRespond[countresponses] += "\n";
                    countresponses++;
                    negResponse = true;
                }
                
            }
            if (sentence[i].equals("i'm")){
                try {
                    if(sentence[i+2].equals("good") || sentence[i+2].equals("bad")){ // if the sentence says i am it follows whether you are good or bad 
                        elizaRespond[countresponses] = "Why are you " + sentence [i+2] +  "?\n"; //if you don't say good or bad it moves on to say why are you whatever you are 
                        countresponses++;
                        negResponse = true;
                    }
                }
                catch (Exception e){
                    elizaRespond[countresponses] = "Go ahead and tell me more...\n";
                    countresponses++;
                    negResponse = true;
                    break;
                }
                
                if (sentence.length-1 <= 8){
                    elizaRespond[countresponses] = "Did you come to talk because you are "; // asks why you feel a certain way 
                    for (int j = 2;   j <= sentence.length-1; j++){
                        elizaRespond[countresponses] += sentence[j] + " "; 
                    }  
                    elizaRespond[countresponses] += "?\n";
                    countresponses++;
                    negResponse = true;
                }
                if (sentence.length-1 > 8){
                    elizaRespond[countresponses] = "Why do you think you "; // if it is more than 8 words it asks you this way 
                    for (int j = 2; j <= sentence.length-1; j++){
                        elizaRespond[countresponses] += sentence[j] + " "; 
                    }
                    elizaRespond[countresponses] += "\n";
                    countresponses++;
                    negResponse = true;
                }
                
            } 
            if (sentence[i].equals("maybe") || sentence[i].equals("perhaps") || sentence[i].equals("kinda") || sentence[i].equals("sorta") || sentence[i].equals("probably")){ // talking about uncertainty 
                elizaRespond[countresponses] = "You sound uncertain of things, could this be affecting you? \n";
                countresponses++;
                negResponse = true;
            }
            if (sentence[i].equals("sorry")){ // don't be sorry lets talk more 
                elizaRespond[countresponses] = "Don't be sorry. Lets keep chatting about it... \n";
                countresponses++;
                negResponse = true;
            }
            if (i != sentence.length-1){ // not to put focus on eliza only the person speaking 
                if (sentence[i].equals("are")){ // the if before this is so no array index out of bounds
                    if (sentence[i+1].equals("you")){
                        return "Lets not focus on me but focus on you...";
                    }
                }
            }
            if (i != sentence.length-1){ // if for the reason of arrayindexoutofbounds
                if (sentence[i].equals("you")){ // chcking the ressemblance 
                    if (sentence[i+1].equals("look") || sentence[i+1].equals("sound") || sentence[i+1].equals("are") || sentence[i+1].equals("act")){
                        if (sentence[i+2].equals("like")){
                            return "Well what resemblance do you see?\n";
                        }
                    }
                }
            }
            if (sentence[i].equals("you") || sentence[i].equals("yours") || sentence[i].equals("your") || sentence[i].equals("you're")){
                int chose = rand.nextInt(3); // choices for saying don't focus about me lets talk about you. 
                switch (chose){
                    case 1:
                        elizaRespond[countresponses] = "Lets focus on you not me. \n";
                        countresponses++;
                        negResponse = true;
                        break;
                    case 2:
                        elizaRespond[countresponses] = "We are here for you not me. \n";
                        countresponses++;
                        negResponse = true;
                        break;
                    case 3:
                        elizaRespond[countresponses] = "Why are you concered about ";
                        for(int l = i; l < sentence.length-1; l++){
                            elizaRespond[countresponses] += sentence[l];
                        }
                        elizaRespond[countresponses] += " \n";
                        countresponses++;
                        negResponse = true;
                        break;   
                }
            }
            if (i != sentence.length-1){ // array indexout of bounds checker 
                if (sentence[i].equals("i") && sentence[i+1].equals("dont") || sentence[i].equals("i") && sentence[i+1].equals("don't")){
                    elizaRespond[countresponses] = "Why don't you ";
                    for (int e=2; e <= sentence.length - 1; e++){
                        elizaRespond[countresponses] += sentence[e] + " "; 
                    }
                    elizaRespond[countresponses] += "\n"; 
                    countresponses++;
                    negResponse = true; 
                }
            }
        }
        if (negResponse == false){ // where we pick our filler, a couple of random options here 
            return filler();
        }
        Random picker = new Random();// making a random generator 
        int selection = picker.nextInt(countresponses); // picking whatever the countresponses has # wise 
        return elizaRespond[selection]; 
    }
    public static String shortTerm(int member, String[] family){ // short term memory for family members 
        String feeling = " ";
        feeling = "How do you think your " + family[member] + " feels?\n";
        mem = false;
        return feeling; 
    }
    public static String filler (){ // all the fillers 
        Random ran = new Random();
        int pick = ran.nextInt(7);
        String fill;

        switch (pick) {
            case 0:
                fill = "Well lets see what we can do, keep talking.\n";
                break;
            case 1:
                fill = "I'd love to hear more.\n";
                break;
            case 2:
                fill = "There must be more continue.\n";
                break;
            case 3:
                fill = "There must be more continue.\n";
                break;
            case 4:
                fill = "Is this going to help you if we talk about it?\n";
                break;
            case 5:
                fill = "Is this why you're here?\n";
                break;
            default:
                fill = "Tell me more.\n";
                break;
        }
        return fill; 
    }
}
        
