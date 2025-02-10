
public class WTTester {

    public static void main(String[] args) {

        WordTree words = new WordTree();
        words.add("cat");
        words.add("catalog");
        words.add("catalogs");
        words.add("army");
        words.add("arms");
        words.add("arm");
        words.add("ball");
        words.add("barn");
        words.add("a");
        
        
        System.out.println(words.toString());
        System.out.println(words.nodeCount());
        System.out.println(words.wordCount());
        System.out.println(words.contains("apples"));
        System.out.println(words.contains("applesd"));
        System.out.println(words.contains(""));
        System.out.println(words.wordList());
        System.out.println(words.letterMap());
        System.out.println(words.letterCount());
        
        words.remove("army");
        words.remove("catalogs");
        words.remove("cat");
        words.remove("barn");
        System.out.println(words.wordList());
        
        words.clear();
        System.out.println(words.toString());
    }

}
