package mkobilas.homework.movieDatabase;

import java.util.Comparator;
/**
 * This class is used to create objects of the type TitleComparator, which are used to sort movies according to their
 *   title. This class implements the Comparator interface in order to allow for the same methods to be used from the
 *   standard Comparator object.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class TitleComparator implements Comparator<Movie>{
    /**
     * Constructor for objects of type TitleComparator.
     * @postcondition
     *      Creates a TitleComparator object.
     */
    public TitleComparator(){
    }
    /**
     * The compare method is used to return a value according to whether or not the two parameters are sorted properly.
     * @param left
     *      Movie left is the movie ahead of the other movie in the current data structure.
     * @param right
     *      Movie right is the movie behind the other movie in the current data structure.
     * @return
     *      Returns 1 if Movie left is less than Movie right, 0 if they are equal, and -1 if Movie left is greater than
     *        Movie right alphabetically. This will ignore the case.
     */
    public int compare(Movie left, Movie right){
        return (left.getTitle().compareToIgnoreCase(right.getTitle()));
    }
}
