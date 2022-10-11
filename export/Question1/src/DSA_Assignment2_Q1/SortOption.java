package DSA_Assignment2_Q1;

/**
 *
 * @author eirik
 */
public enum SortOption {
    NAME("Name"),
    MARK("Mark");

    public static final int NUMBER_OF_OPTIONS = 2;
    public final String nameOfKey;

    private SortOption(String nameOfKey) {
        this.nameOfKey = nameOfKey;
    }
}
