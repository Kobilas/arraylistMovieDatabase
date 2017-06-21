package mkobilas.homework.movieDatabase;
/**
 * The Actor class is used to create Actor objects. These objects have String and int variables which are used to
 *   hold the name and number of movies that the actor has been seen in, respectively. This class also has the
 *   necessary accessor and mutator methods for String name and int count. This object is used to keep a list of
 *   the actors that can be found in any particular movie, as well as the main list of actors amongst all the movies
 *   in the database.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class Actor {
    private String name;
    private int count;
    /**
     * Constructor for Actor objects which takes a parameter in the form of a String to represent the name of the
     *   actor.
     * @param initName
     *      String initName is what String name will be set to after the Actor object is created.
     * @precondition
     *      String initName cannot be null.
     * @postcondition
     *      This Actor object is created with String name initialized to String initName.
     * @throws IllegalArgumentException
     *      Throws an exception if String initName is null.
     */
    public Actor(String initName){
        if(initName ==  null)
            throw new IllegalArgumentException("Argument String initName cannot be null.");
        name = initName;
        count = 0;
    }
    /**
     * Accessor method for the String name of this Actor object.
     * @return
     *      Returns the String name of this Actor object.
     */
    public String getName(){
        return name;
    }
    /**
     * Mutator method for the String name of this Actor object.
     * @param newName
     *      String newName is what String name will be set to after this method is called.
     * @precondition
     *      String newName cannot be null.
     * @postcondition
     *      String name of this Actor object is set to String newName.
     * @throws IllegalArgumentException
     *      Throws an exception if String newName is null.
     */
    public void setName(String newName){
        if(newName ==  null)
            throw new IllegalArgumentException("Argument String newName cannot be null.");
        name = newName;
    }
    /**
     * Accessor method for the int count of this Actor object.
     * @return
     *      Returns the int count of this Actor object.
     */
    public int getCount(){
        return count;
    }
    /**
     * Mutator method for the int count of this Actor object.
     * @param newCount
     *      int newCount is what int count will be set to after this method is called.
     * @precondition
     *      int newCount cannot be negative.
     * @postcondition
     *      int count of this Actor object is set to int newCount.
     * @throws IllegalArgumentException
     *      Throws an exception if int newCount is negative.
     */
    public void setCount(int newCount){
        if(newCount < 0)
            throw new IllegalArgumentException("Argument int newCount cannot be negative.");
        count = newCount;
    }
}
