package mkobilas.homework.movieDatabase;

import java.util.Comparator;
/**
 * This class is used to create objects of the type NameComparator, which are used to sort actors according to their
 *   name. This class implements the Comparator interface in order to allow for the same methods to be used from the
 *   standard Comparator object.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class NameComparator implements Comparator<Actor>{
    /**
     * Constructor for objects of type NameComparator.
     * @postcondition
     *      Creates a NameComparator object.
     */
    public NameComparator(){
    }
    /**
     * The compare method is used to return a value according to whether or not the two parameters are sorted properly.
     * @param left
     *      Actor left is the movie ahead of the other actor in the current data structure.
     * @param right
     *      Actor right is the movie behind the other actor in the current data structure.
     * @return
     *      Returns 1 if Actor left is less than Actor right, 0 if they are equal, and -1 if Actor left is greater than
     *        Actor right alphabetically. This will ignore the case.
     */
    public int compare(Actor left, Actor right){
        return (left.getName().compareToIgnoreCase(right.getName()));
    }
}
