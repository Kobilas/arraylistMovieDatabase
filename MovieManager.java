package mkobilas.homework.movieDatabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * The MovieManager class is used to create MovieManager objects. These objects act as a database to store information
 *   regarding movies and the actors that play in them. This class has methods that allow for the manipulation of the
 *   different movies in the database as well as the actors. These movies and actors are stored in the form of List<> 
 *   objects in order to make manipulating the elements within them easier.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class MovieManager {
    private List<Movie> movies;
    private List<Actor> actors;
    private boolean isActorsEmpty;
    /**
     * Constructor for MovieManager objects which initializes List<Movie> movies and List<Actor> actors to new, empty
     *   ArrayList<>  objects. The constructor also initializes boolean isActorsEmpty to true to represent the fact that
     *   the object was created and none of the methods have yet been run. This allows for the updating of the list of
     *   actors to work properly.
     */
    public MovieManager(){
        movies = new ArrayList<> ();
        actors = new ArrayList<> ();
        isActorsEmpty = true;
    }
    /**
     * Accessor method for this MovieManager object's List<Movie> movies object.
     * @return
     *      Returns a reference to this MovieManager object's List<Movie> movies object, which holds all of the movies
     *        that are currently in the database.
     */
    public List<Movie> getMovies(){
        return movies;
    }
    /**
     * Accessor method for this MovieManager object's List<Actor> actors object.
     * @return
     *      Returns a reference to this MovieManager object's List<Actor> actors object, which holds all of the actors
     *        that played across all of the movies that are stored in this MovieManager object.
     */
    public List<Actor> getActors(){
        return actors;
    }
    /**
     * Method used to update List<Actor> actors of this MovieManager object whenever it is called. It first sets the
     *   count of the number of movies that each actor has starred in to zero, then it updates the list based on any
     *   new movies that have been added to the List<Movie> movies object. If the List<Actor> actors object was
     *   previously empty, then the method simply adds any new actors to the list, otherwise it will search the list
     *   for any duplicates and simply increase the count, or add the actors if it did not exist prior. After that is 
     *   complete, the count of each actor in the list is incremented according to the number of movies they have
     *   starred in that are in the database. At the end of the function, any actors whose count is zero in the
     *   list are removed.
     * @postcondition
     *      List<Actor> actors is updated to include any new actors found amongst any new movies in List<Movie> movies,
     *        as well as changing their count according to the number of movies they are in. Actors with a count of
     *        zero are removed.
     */
    public void updateActors(){
        ArrayList<Integer> containedIndex = new ArrayList<> ();
        boolean contained = false;
        if(actors.isEmpty())
            isActorsEmpty = true;
        else
            isActorsEmpty = false;
        for(int i = 0; i < actors.size(); i++ )
            actors.get(i).setCount(0);
        for(int j = 0; j < movies.size(); j++ ){
            for(int k = 0; k < movies.get(j).getActors().size(); k++ ){
                if(isActorsEmpty){
                    actors.add(movies.get(j).getActors().get(k));
                    containedIndex.add(actors.size()-1);
                }
                else{
                    if(actors.contains(movies.get(j).getActors().get(k))){
                        containedIndex.add(actors.indexOf(movies.get(j).getActors().get(k)));
                        contained = true;
                    }
                    else
                        if(!contained){
                            actors.add(movies.get(j).getActors().get(k));
                            containedIndex.add(actors.size()-1);
                        }
                    contained = false;
                }
            }
        }
        for(int l = 0; l < containedIndex.size(); l++ )
            actors.get(containedIndex.get(l)).setCount(actors.get(containedIndex.get(l)).getCount()+1);
        for(int m = 0; m < actors.size(); m++ )
            if(actors.get(m).getCount() ==  0)
                actors.remove(m);
    }
    /**
     * Accessor method for the List<Movie> movies, except that it creates a new list, and sorts the movies in it
     *   according to a comparator and direction entered by the user.
     * @param comp
     *      Comparator<Movie> comp is a comparator chosen by the user that changes the way the List<Movie> movies
     *        object will be sorted based on the type of comparator chosen.
     * @param isAscending
     *      boolean isAscending determines whether or not the user wanted to sort the List<Movie> movies object
     *        normally or in the reversed direction.
     * @return
     *      Returns List<Movie> result which is the List<Movie> movies list rearranged according to the type of sort
     *        selected by the user.
     */
    public List<Movie> getSortedMovies(Comparator<Movie> comp, boolean isAscending){
        updateActors();
        List<Movie> result = movies;
        if(isAscending)
            result.sort(comp);
        else
            result.sort(comp.reversed());
        return result;
    }
    /**
     * Accessor method for the List<Actor> actors, except that it creates a new list, and sorts the actors in it
     *   according to a comparator and direction entered by the user.
     * @param comp
     *      Comparator<Actor> comp is a comparator chosen by the user that changes the way the List<Actor> actors
     *        object will be sorted based on the type of comparator chosen.
     * @param isAscending
     *      boolean isAscending determines whether or not the user wanted to sort the List<Actor> actors object
     *        normally or in the reversed direction.
     * @return
     *      Returns List<Actor> result which is the List<Actor> actors list rearranged according to the type of sort
     *        selected by the user.
     */
    public List<Actor> getSortedActors(Comparator<Actor> comp, boolean isAscending){
        updateActors();
        List<Actor> result = actors;
        if(isAscending)
            result.sort(comp);
        else
            result.sort(comp.reversed());
        return result;
    }
    /**
     * Method used to return movies from List<Movie> movies according to the String title entered by the user.
     * @param title
     *      Title of the movie to delete from List<Movie> movies object. Must be entered exactly as shown in the
     *        program menu.
     * @precondition
     *      String title must be entered exactly as shown in the program's menus.
     * @postcondition
     *      String title named movie will be removed from List<Movie> movies.
     * @return
     *      Returns true if the movie entered was removed successfully, and false otherwise. 
     */
    public boolean removeByTitle(String title){
        for(int i = 0; i < movies.size(); i++ )
            if(movies.get(i).getTitle().equals(title)){
                movies.remove(i);
                updateActors();
                return true;
            }
        return false;
    }
}
