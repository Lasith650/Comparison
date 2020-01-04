import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SimilarityCalculator {

    //These two lists were created only for stimulation purpose.
    //Replace them with the inputs from the other two modules
    ArrayList<String> tmtOutput = new ArrayList<String>(Arrays.asList("Dog", "can", "run", "fast", "Dog"));
    ArrayList<String> cweDescription = new ArrayList<>(Arrays.asList("Dog", "run", "fast", "house", "plant", "can"));

    //This method calculates the Cosine Similarity value while substituting the relevant values using two methods created.
    //Method 01 - getSumOfTheProductOfTheCommonWords()
    //Method 02 - getSumOfSquireOfAllWords()
    public double getCosineSimilarityValue() {
        double cosineSimilarityValue = getSumOfTheProductOfTheCommonWords(getCommonWords()) /
                (Math.sqrt(getSumOfSquiresOfAllWords(tmtOutput)) * Math.sqrt(getSumOfSquiresOfAllWords(cweDescription)));
        return cosineSimilarityValue;
    }

    //This method is used to find the common words in the inputs provided by the module 1 tool and module 2
    public ArrayList<String> getCommonWords() {
        ArrayList<String> commonWords = new ArrayList<String>();
        for (int i = 0; i < tmtOutput.size(); i++) {
            for (int a = 0; a < cweDescription.size(); a++) {
                if ((tmtOutput.get(i) == cweDescription.get(a)) && (Collections.frequency(commonWords, tmtOutput.get(i)) == 0)) {
                    commonWords.add(tmtOutput.get(i));
                }
            }
        }
        return commonWords;
    }

    //This method will return the sum of the products of the common words
    public double getSumOfTheProductOfTheCommonWords(ArrayList<String> commonWords) {
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

    //This main method was written only for checking purposes
    public static void main(String[] args) {
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        for (String i : similarityCalculator.getCommonWords()) {
            System.out.println(i);
        }
        System.out.println(similarityCalculator.getSumOfTheProductOfTheCommonWords(similarityCalculator.getCommonWords()));
        System.out.println(similarityCalculator.getSumOfSquiresOfAllWords(similarityCalculator.tmtOutput));
        System.out.println(similarityCalculator.getCosineSimilarityValue());
    }
}
