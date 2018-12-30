package primary;

public class ParallelDriver {

    public static void main(String[] args) {
        String[] arr = "This is the best night of my life!".split(" ");
        Strings.stringTask(arr);

        String[] arr1 = ("THIS IS A MUCH LONGER ARRAY OF CHARACTERS OR I GUESS I SHOULD JUST SAY STRINGS SINCE THAT'S" +
                "WHAT STRINGS ACTUALLY ARE - arrays of characters. Oh, and also did you realize that there's going" +
                "to be a completely new order of chaos that comes raining down onto modern civilization that is going" +
                "to completely change the world.").split(" ");
        Strings.stringTask(arr1);

        String[] arr2 = ("DID YOU KNOW THAT YOU COULD PUT AN INSANELY LONG SEQUENCE OF CONCATENATED STRINGS, OR" +
                "ANY KIND OF STRING FOR THAT MATTER, INTO A SET OF PARENTHESIS THAT MAKE IT VERY EASY TO USE " +
                "INSTANCE METHODS ON THE ENTIRETY OF THE COMBINED STRING. SINCE THE PARENTHESIS MAKE THE COMPILER " +
                "EVALUATE THE EXPRESSION, AND THEN CALL THE METHOD, THIS MAKES IT VERY EASY TO BUILD STRINGS IN AN" +
                "AESTHETICALLY PLEASING WAY.").split(" ");

        printArr(arr2);
        Strings.stringTask(arr2);

    }

    public static void printArr(String[] arr) {
        String retStr = "[" + arr[0];
        for (int i = 1; i < arr.length; i++) {
            retStr += ", " + arr[i];
        }
        System.out.println(retStr + "]");
    }
}
