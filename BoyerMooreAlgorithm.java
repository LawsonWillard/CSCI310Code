public class BoyerMooreAlgorithm{

    //

    public void BoyerMooreAlgorithm(String text1, String pattern1) {

        char[] text = text1.toCharArray();
        char[] pattern = pattern1.toCharArray();
        int positionOfPattern = indexOfPattern(text, pattern);
        if (positionOfPattern == -1) {
            System.out.println("\nNo Match Found\n");
        } else {
            System.out.println("\nPattern found at index: " + positionOfPattern);
        }
    }

    //From book
    //Alphabet length is total ASCII length = 2^8 =256
    //Creates a bad character shift table based on the Alphabet size and pattern length
    public int[] shiftTable(char[] pattern) {
        int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; i++) {
            table[i] = pattern.length;
        }
        for (int i = 0; i < pattern.length; i++) {
            table[pattern[i]] = pattern.length - 1 - i;
        }
        return table;
    }

    //Shift table for a good-suffix shift
    //Finds if there is a prefix and then finds the suffix length
    //From here it stores the info in the table
    public int[] shiftTableMatch(char[] pattern) {
        int table[] = new int[pattern.length]; //Creates table of pattern length
        int lastPos = pattern.length; //Last position starts at end of pattern

        //Finds Prefix Length
        for (int i = pattern.length; i > 0; i--) {
            if (isPrefix(pattern, i)) {
                lastPos = i;
            }
            table[pattern.length - i] = lastPos - i + pattern.length;
        }

        //Finds Suffix Length
        for (int i = 0; i < pattern.length - 1; i++) {
            int suffixLength = suffix(pattern, i);
            table[suffixLength] = pattern.length - 1 - i + suffixLength;
        }
        //Returns Suffix Table
        return table;
    }

    //Finds Suffix Length
    public int suffix(char[] pattern, int p) {
        int length = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j) {
            length += 1;
        }
        return length;
    }

    //Finds if the prefix matches
    public boolean isPrefix(char[] pattern, int i) {
        for (int j = i, k = 0; j < pattern.length; k++, ++j) {
            if (pattern[j] != pattern[k]) {
                return false;
            }
        }
        return true;

    }



    //Finds the pattern at an index i
    //If there is no match then it returns -1
    public int indexOfPattern(char[] text, char[] pattern) {
        //If pattern is 0 then return 0
        if (pattern.length == 0) {
            return 0;
        }
        int charTable[] = shiftTable(pattern);  //Creates bad character shift table
        int suffixShiftTable[] = shiftTableMatch(pattern); //Creates good suffix shift table

        //Run throught the text array
        for (int i = pattern.length - 1, j; i < text.length; ) {

            //Runs through pattern array
            //If j reaches 0 then it has run through the pattern and found a match
            //If not then it shifts based on the max between the good shift table and bad shift table
            for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j)

                if (j == 0) {
                    return i;
                }
                System.out.println("\nComparison: i: " + pattern[j]+", " + "j: "+ text[i]+"\n");
            //Prints either good suffix shift or bad character depending on what it will shift on
            System.out.println("Suffix Shift Table: " +suffixShiftTable[pattern.length - 1 - j]);
                System.out.println("Bad Character Table: " + charTable[text[i]]);

            if(Math.max(suffixShiftTable[pattern.length - 1 - j], charTable[text[i]]) == suffixShiftTable[pattern.length - 1 - j]){
                System.out.println("\nShift by Good Suffix Table: " + suffixShiftTable[pattern.length - 1 - j]+"\n");
            }
            else{
                System.out.println("Shift by Bad Character Table: " + charTable[text[i]]+"\n");
            }
            i += Math.max(suffixShiftTable[pattern.length - 1 - j], charTable[text[i]]);//moves search by i+ max of shift

        }
        return -1;
    }



    //Main
    public static void main(String[] args) {

        String text = "BESS_KNEW_ABOUT_BAOBABS";
        String pattern = "BAOBAB";

        System.out.println("Comparing "+ pattern+" against "+text);

        BoyerMooreAlgorithm BMA = new BoyerMooreAlgorithm();

        BMA.BoyerMooreAlgorithm(text, pattern);


    }
}
