import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimilarityCalculator {

    //These two lists were created only for stimulation purpose.
    //Replace them with the inputs from the other two modules
//    ArrayList<String> tmtOutput = new ArrayList<String>(Arrays.asList("Dog", "can", "run", "fast", "Dog"));
//    ArrayList<String> cweDescription = new ArrayList<>(Arrays.asList("Dog", "run", "fast", "house", "plant", "can"));

    //This method calculates the Cosine Similarity value while substituting the relevant values using two methods created.
    //Method 01 - getSumOfTheProductOfTheCommonWords()
    //Method 02 - getSumOfSquireOfAllWords()
    public double getCosineSimilarityValue(String interactionDescription,String cweDescription) {
        ArrayList<String> tmtOutput = splitIntoWords(interactionDescription);
        ArrayList<String> cwe = splitIntoWords(cweDescription);
        double cosineSimilarityValue = getSumOfTheProductOfTheCommonWords(getCommonWords(tmtOutput , cwe) , tmtOutput , cwe) /
                (Math.sqrt(getSumOfSquiresOfAllWords(tmtOutput)) * Math.sqrt(getSumOfSquiresOfAllWords(cwe)));
        System.out.println(cosineSimilarityValue);
        return cosineSimilarityValue;
    }

    //This method is used to find the common words in the inputs provided by the module 1 tool and module 2
    public ArrayList<String> getCommonWords(ArrayList<String> tmtOutput , ArrayList<String> cweDescription) {
        ArrayList<String> commonWords = new ArrayList<String>(tmtOutput);
//        for (int i = 0; i < tmtOutput.size(); i++) {
//            for (int a = 0; a < cweDescription.size(); a++) {
//                if ((tmtOutput.get(i) == cweDescription.get(a)) && (Collections.frequency(commonWords, tmtOutput.get(i)) == 0)) {
//                    commonWords.add(tmtOutput.get(i));
//                }
//            }
//        }
        commonWords.retainAll(cweDescription);
        ArrayList<String> distinctCommonWords = new ArrayList<String>();
        for (int i = 0 ; i < commonWords.size() ; i++){
            if (Collections.frequency(distinctCommonWords , commonWords.get(i)) == 0){
                distinctCommonWords.add(commonWords.get(i));
            }
        }
        System.out.println("these are common words");
        for (String v : distinctCommonWords){
            System.out.println(v);
        }
        return distinctCommonWords;
    }

    //This method will return the sum of the products of the common words
    public double getSumOfTheProductOfTheCommonWords(ArrayList<String> commonWords , ArrayList<String> tmtOutput , ArrayList<String> cweDescription) {
        double sum = 0.0;
        for (int i = 0; i < commonWords.size(); i++) {
            double product = (Collections.frequency(tmtOutput, commonWords.get(i))) * (Collections.frequency(cweDescription, commonWords.get(i)));
            sum = sum + product;
        }
        return sum;
    }

    //This method will return the sum of the squires of all the words in a given list of words
    public double getSumOfSquiresOfAllWords(ArrayList<String> words) {
        ArrayList<String> alreadySquired = new ArrayList<String>();
        double sum = 0.0;
        for (int i = 0; i < words.size(); i++) {
            if (Collections.frequency(alreadySquired, words.get(i)) == 0) {
                double squire = Math.pow(Collections.frequency(words, words.get(i)), 2);
                sum = sum + squire;
                alreadySquired.add(words.get(i));
            }
        }
        return sum;
    }

    public ArrayList<String> splitIntoWords(String sentence){
        String [] words = sentence.split("[^a-zA-Z]+");
        ArrayList<String> tokens = new ArrayList<String>();
        Collections.addAll(tokens, words);
        return tokens;
    }

    //This main method was written only for checking purposes
    public static void main(String[] args) {
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
//        for (String i : similarityCalculator.getCommonWords()) {
//            System.out.println(i);
//        }
//        System.out.println(similarityCalculator.getSumOfTheProductOfTheCommonWords(similarityCalculator.getCommonWords()));
//        System.out.println(similarityCalculator.getSumOfSquiresOfAllWords(similarityCalculator.tmtOutput));
//        System.out.println(similarityCalculator.getCosineSimilarityValue());
//        String [] arr = similarityCalculator.splitIntoWords("hi, there,hi Leo");
//        for (int i = 0 ; i < arr.length ; i++){
//            System.out.println(arr[i]);
//        }
    }
}
