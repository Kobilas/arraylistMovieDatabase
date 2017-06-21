package mkobilas.homework.movieDatabase;

import java.util.ArrayList;
import java.util.List;
import big.data.*;
/**
 * The Movie class is used to create Movie objects which are used to store information regarding movies. This includes
 *   the String title of the movie, the int year the movie was produced, and a list of actors that starred in it. This
 *   class uses the omdb api and big.data structure in order to find movie information and obtain it. This class has
 *   two constructors as well, using a String url for one of them in order to obtain xml information from the omdb api.
 *   This class also uses several mutator and accessor methods for the String title, int year, and List<Actor> actors.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class Movie {
    private String title;
    private int year;
    private List<Actor> actors;
    /**
     * Constructor for Movie objects which takes parameters in the form of a String for the title of the movie, and an
     *   int for the year the movie was created.
     * @param initTitle
     *      String initTitle is what String title will be initialized to once this Movie object is created.
     * @param initYear
     *      int initYear is what int year will be initialized to once this Movie object is created.
     * @precondition
     *      String initTitle cannot be null.
     * @precondition
     *      int initYear cannot be negative.
     * @postcondition
     *      This Movie object is created with String title initialized to String initTitle and int year initialized to
     *        int initYear.
     * @throws IllegalArgumentException
     *      Throws an exception if String initTitle is null or initYear is negative.
     */
    public Movie(String initTitle, int initYear){
        if(initTitle ==  null)
            throw new IllegalArgumentException("Argument String initTitle cannot be null.");
        if(initYear < 0)
            throw new IllegalArgumentException("Argument int initYear cannot be negative.");
        title = initTitle;
        year = initYear;
        actors = new ArrayList<> ();
    }
    /**
     * Constructor for Movie objects which takes a parameter in the form of a String to represent the URL at which a
     *   movie can be found in the omdb api. This URL is then used to find the various information used to populate the
     *   variables in this Movie object, including the title, year, and actors in the movie. The URL is read using a
     *   DataSource object which also finds the title, year, and actors of the movie using api keys.
     * @param url
     *      String url is the URL which the DataSource object will retrieve the xml file from.
     * @precondition
     *      String url cannot be null.
     * @postcondition
     *      Creates this Movie object and initializes all of the variables according to the xml file retrieved from
     *        omdb and using the api keys to find all the different information.
     * @throws IllegalArgumentException
     *      Throws an exception if String url is null.
     */
    public Movie(String url){
        if(url ==  null)
            throw new IllegalArgumentException("Argument String url cannot be null.");
        DataSource omdbSource = DataSource.connectXML(url);
        omdbSource.load();
        title = omdbSource.fetchString("movie/title");
        year = omdbSource.fetchInt("movie/year");
        actors = new ArrayList<> ();
        String[] actorNames = omdbSource.fetchString("movie/actors").split(", ");
        for(int i = 0; i < actorNames.length; i++ )
            actors.add(new Actor(actorNames[i]));
    }
    /**
     * Accessor method for the String title of this Movie object.
     * @return
     *      Returns this Movie object's String title.
     */
    public String getTitle(){
        return title;
    }
    /**
     * Mutator method for the String title of this Movie object. 
     * @param newTitle
     *      String newTitle is what String title will be set to after this method is called.
     * @precondition
     *      String newTitle cannot be null.
     * @postcondition
     *      String title of this Movie object is set to String newTitle.
     * @throws IllegalArgumentException
     *      Throws an exception if String newTitle is null.
     */
    public void setTitle(String newTitle){
        if(newTitle ==  null)
            throw new IllegalArgumentException("Argument String newTitle cannot be null.");
        title = newTitle;
    }
    /**
     * Accessor method for the int year of this Movie object.
     * @return
     *      Returns this Movie object's int year.
     */
    public int getYear(){
        return year;
    }
    /**
     * Mutator method for the int year of this Movie object.
     * @param newYear
     *      int newYear is what int year will be set to after this method is called.
     * @precondition
     *      int newYear cannot be negative.
     * @postcondition
     *      int year of this Movie object is set to int newYear.
     * @throws IllegalArgumentException
     *      Throws an exception if int newYear is negative.
     */
    public void setYear(int newYear){
        if(newYear < 0)
            throw new IllegalArgumentException("Argument int newYear cannot be negative.");
        year = newYear;
    }
    /**
     * Accessor method for the List<Actor> actors of this Movie object.
     * @return
     *      Returns a reference to this Movie object's List<Actor> actors.
     */
    public List<Actor> getActors(){
        return actors;
    }
    /**
     * Mutator method for the List<Actor> actors of this Movie object.
     * @param newActors
     *      List<Actor> newActors is what List<Actor> actors will be changed to after this method is called.
     * @precondition
     *      List<Actor> newActors cannot be null.
     * @postcondition
     *      List<Actor> actors of this Movie object is changed to List<Actor> newActors.
     * @throws IllegalArgumentException
     *      Throws an exception if List<Actor> newActors is null.
     */
    public void setActors(List<Actor> newActors){
        if(newActors ==  null)
            throw new IllegalArgumentException("Argument List<Actor> newActors cannot be null.");
        if(newActors.isEmpty())
            actors.clear();
        else
            for(int i = 0; i < newActors.size(); i++ )
                actors.add(newActors.get(i));
    }
    /**
     * Accessor method for the List<Actor> actors of this Movie object, except that it returns a String of the actors
     *   within the list with commas between them. Used to print the actors that starred in this movie if the movies
     *   are sorted by the user.
     * @return
     *      Returns a String of the list of the actors that were part of this movie separated by commas.
     */
    public String actorsToString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < actors.size(); i++ ){
            result.append(actors.get(i).getName());
            if(i+1 != actors.size())
                result.append(", ");
        }
        return result.toString();
    }
}
