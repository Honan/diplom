package ru.denis1;



import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        InputStream inputStreamTokenizer = new FileInputStream("model/en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);
        Tokenizer tokenizer = new TokenizerME(tokenModel);

        String paragraph = reader.readLine();

        //String paragraph = "Russian president Vladimir Putin to visit China this week";
        String tokens[] = tokenizer.tokenize(paragraph);

        InputStream modelIn = new FileInputStream("model/en-ner-person.bin");
        TokenNameFinderModel modelPerson = new TokenNameFinderModel(modelIn);
        NameFinderME nameFinderPerson = new NameFinderME(modelPerson);
        Span nameSpansPerson[] = nameFinderPerson.find(tokens);

        for(Span s: nameSpansPerson)
            System.out.println(s.toString()+"  "+tokens[s.getStart()]);


        InputStream inputStreamNameFinder = new FileInputStream("model/en-ner-location.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        NameFinderME nameFinder = new NameFinderME(model);
        Span nameSpans[] = nameFinder.find(tokens);

       for (Span text: nameSpans){
           System.out.println(text.toString()+"  "+tokens[text.getStart()]);
       }








    }


}
