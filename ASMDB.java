package mkobilas.homework.movieDatabase;

import java.util.List;
import java.util.Scanner;

import big.data.DataInstantiationException;
/**
 * Driver class used to receive input from the user and edit the database according to their preferences. The options
 *   available to the user include adding movies to the database, removing them, sorting the sum of actors, sorting
 *   the movies, and quitting the program.
 * @author Matthew Kobilas
 *      matthew.kobilas@stonybrook.edu
 *      SBU ID: 111152838
 *      CSE214-R02
 */
public class ASMDB {
    private static Scanner input;
    private static String answer, title;
    private static final String URL_PREFIX = "http://www.omdbapi.com/?t=", URL_POSTFIX = "&y=&plot=short&r=xml";
    private static boolean invalidAnswer;
    private static MovieManager database;
    private static StringBuilder toPrint;
    /**
     * The main method hosts the main loop which asks for input and preforms actions based on the input, as well as a
     *   means to quit the program gracefully. The parameters asked for by the method are simply there to make the main
     *   method act as a main method in the class. The different exceptions thrown by the other classes and methods are
     *   also handled in this method.
     * @param args
     *      String[] args is simply asked for as a parameter in order to let the ASMDB class know that this is the main
     *        method.
     */
    public static void main(String[] args){
        //Used to efficiently concatenate strings within loops.
        toPrint = new StringBuilder();
        //The actual database holding the movies and actors entered by the user.
        database = new MovieManager();
        input = new Scanner(System.in);
        System.out.println("Welcome to your movie database.\n");
        //Main loop which runs as long as the program does not order the program to quit.
        topLayerLoop: while(true){
            printMainMenu();
            //Asks for input and loops as long as the input is invalid
            answerLayerLoop: while(true){
                if(invalidAnswer){
                    System.out.print("Please select a valid option: ");
                    invalidAnswer = false;
                }
                else
                    System.out.print("Please select an option: ");
                answer = input.nextLine();
                System.out.println("");
                if(answer.length() != 1)
                    invalidAnswer = true;
                else
                    break answerLayerLoop;
            }
            //Hosts the switch/case statement that determines what the program will do based on the user's input.
            switch(answer.toLowerCase()){
                //If the user enters "I", the program will ask the user for a movie title to enter into the database.
                  //If the movie is not found in the omdb database, it tells the user that the movie was not found.
                case("i"):
                    System.out.print("Please enter a movie title: ");
                    title = input.nextLine();
                    try{
                        database.getMovies().add(new Movie(URL_PREFIX + (title.replace(" ", "+").toLowerCase())
                          + URL_POSTFIX));
                    }
                    catch(DataInstantiationException err){
                        System.out.println("\nMovie not found.\n");
                        continue topLayerLoop;
                    }
                    database.updateActors();
                    System.out.println("\nMovie added: \""
                      + database.getMovies().get(database.getMovies().size()-1).getTitle() + "\"\n");
                    continue topLayerLoop;
                //If the user enters "D", the program will ask the user for a movie title to remove from the database.
                  //If the movie is not found in the main database, it tells the user that the movie was not found.
                case("d"):
                    System.out.print("Please enter a movie title: ");
                    title = input.nextLine();
                    if(database.removeByTitle(title))
                        System.out.println("\nMovie deleted: \"" + title + "\"\n");
                    else
                        System.out.println("\nMovie not found.\n");
                    continue topLayerLoop;
                //If the user enters "A", the program will ask the user in what manner to sort the actors that are
                  //currently in the database.
                case("a"):
                    //Loops while the user has not entered valid input, and if they have, the program sorts according
                      //to the input and then returns to the main loop.
                    actorLayerLoop: while(true){
                        actorAnswerLayerLoop: while(true){
                            printActorSortingMenu();
                            if(invalidAnswer){
                                System.out.print("Please select a valid option: ");
                                invalidAnswer = false;
                            }
                            else
                                System.out.print("Please select an option: ");
                            answer = input.nextLine();
                            System.out.println("");
                            if(answer.length() != 2)
                                invalidAnswer = true;
                            else
                                break actorAnswerLayerLoop;
                        }
                        switch(answer.toLowerCase()){
                            case("aa"):
                                sortActors(true, true);
                                continue topLayerLoop;
                            case("ad"):
                                sortActors(true, false);
                                continue topLayerLoop;
                            case("na"):
                                sortActors(false, true);
                                continue topLayerLoop;
                            case("nd"):
                                sortActors(false, false);
                                continue topLayerLoop;
                            case("mm"):
                                System.out.println("Moving back to main menu");
                                continue topLayerLoop;
                            default:
                                invalidAnswer = true;
                                continue actorLayerLoop;
                        }
                    }
                //If the user enters "M", the program will ask the user in what manner to sort the movies that are
                  //currently in the database.
                case("m"):
                    //Loops while the user has not entered valid input, and if they have, the program sorts according
                      //to the input and then returns to the main loop.
                    movieLayerLoop: while(true){
                        movieAnswerLayerLoop: while(true){
                            printMovieSortingMenu();
                            if(invalidAnswer){
                                System.out.print("Please select a valid option: ");
                                invalidAnswer = false;
                            }
                            else
                                System.out.print("Please select an option: ");
                            answer = input.nextLine();
                            System.out.println("");
                            if(answer.length() != 2)
                                invalidAnswer = true;
                            else
                                break movieAnswerLayerLoop;
                        }
                        switch(answer.toLowerCase()){
                            case("ta"):
                                sortMovies(true, true);
                                continue topLayerLoop;
                            case("td"):
                                sortMovies(true, false);
                                continue topLayerLoop;
                            case("ya"):
                                sortMovies(false, true);
                                continue topLayerLoop;
                            case("yd"):
                                sortMovies(false, false);
                                continue topLayerLoop;
                            case("mm"):
                                System.out.println("Moving back to main menu");
                                continue topLayerLoop;
                            default:
                                invalidAnswer = true;
                                continue movieLayerLoop;
                        }
                    }
                //If the user enters "Q", the program will quit gracefully.
                case("q"):
                    break topLayerLoop;
                default:
                    invalidAnswer = true;
                    continue topLayerLoop;
            }
        }
        input.close();
        System.out.println("Thanks for using your favorite movie database!");
        System.exit(0);
    }
    /**
     * This method is used to print the main menu used to select in what manner to edit the database, and the options
     *   to sort the movies and actors in the database.
     * @postcondition
     *      Prints the main menu to standard out.
     */
    private static void printMainMenu(){
        System.out.println("Main Menu:");
        System.out.println("    I) Import a movie");
        System.out.println("    D) Delete a movie");
        System.out.println("    A) Sort actors");
        System.out.println("    M) Sort movies");
        System.out.println("    Q) Quit");
    }
    /**
     * This method is used to print the actor sorting menu, which presents the user with their options for sorting the
     *   actors that are currently in the database, or the option to return to the main menu.
     * @postcondition
     *      Prints the actor sorting menu to standard out.
     */
    private static void printActorSortingMenu(){
        System.out.println("Actor Sorting Menu:");
        System.out.println("    AA) Alphabetically by name ascending (A-Z)");
        System.out.println("    AD) Alphabetically by name descending (Z-A)");
        System.out.println("    NA) By number of movies they are in ascending");
        System.out.println("    ND) By number of movies they are in descending");
        System.out.println("    MM) Return to main menu");
    }
    /**
     * This method is used to print the movie sorting menu, which presents the user with their options for sorting the
     *   movies that are currently in the database, or the option to return to the main menu.
     * @postcondition
     *      Prints the movie sorting menu to standard out.
     */
    private static void printMovieSortingMenu(){
        System.out.println("Movie Sorting Menu:");
        System.out.println("    TA) Alphabetically by title ascending (A-Z)");
        System.out.println("    TD) Alphabetically by title descending (Z-A)");
        System.out.println("    YA) By year ascending");
        System.out.println("    YD) By year descending");
        System.out.println("    MM) Return to main menu");
    }
    /**
     * This method is used to actually sort the actors if the user wants the program to do so. It takes input in the
     *   form of two boolean values which are used to represent the manner in which the user wants to sort the actors
     *   in the database.
     * @param isName
     *      If boolean isName is true, then the program will sort the actors by name, otherwise, they are sorted by 
     *        the count of movies that they have starred in.
     * @param isAscending
     *      If boolean isAscending is true, then the program will sort the actors in an ascending order, otherwise,
     *        they are sorted in a descending manner.
     * @postcondition
     *      Prints the actors sorted in the way the user wants to standard out, in a neatly formatted fashion.
     */
    private static void sortActors(boolean isName, boolean isAscending){
        List<Actor> sortedActors = null;
        toPrint.setLength(0);
        database.updateActors();
        if(isName)
            sortedActors = database.getSortedActors((new NameComparator()), isAscending);
        else
            sortedActors = database.getSortedActors((new CountComparator()), isAscending);
        toPrint.append("Actor                                                                     Number of Movies\n");
        toPrint.append("------------------------------------------------------------------------------------------\n");
        for(int i = 0; i < sortedActors.size(); i++ ){
            toPrint.append(String.format("%-72s%2s%-3d", sortedActors.get(i).getName(), "  ",
              sortedActors.get(i).getCount()));
            toPrint.append("\n");
        }
        toPrint.append("\n");
        System.out.print(toPrint.toString());
    }
    /**
     * This method is used to actually sort the movies if the user wants the program to do so. It takes input in the
     *   form of two boolean values which are used to represent the manner in which the user wants to sort the movies
     *   in the database.
     * @param isTitle
     *      If boolean isTitle is true, then the program will sort the movies by title, otherwise, they are sorted by
     *        the year in which they were created.
     * @param isAscending
     *      If boolean isAscending is true, then the program will sort the actors in an ascending order, otherwise,
     *        they are sorted in a descending manner.
     * @postcondition
     *      Prints the actors soted in the way the user wants to standard out, in a neatly formatted fashion.
     */
    private static void sortMovies(boolean isTitle, boolean isAscending){
        List<Movie> sortedMovies = null;
        toPrint.setLength(0);
        database.updateActors();
        String temp = null;
        if(isTitle)
            sortedMovies = database.getSortedMovies((new TitleComparator()), isAscending);
        else
            sortedMovies = database.getSortedMovies((new YearComparator()), isAscending);
        toPrint.append("Title                                           Year  Actors                              \n");
        toPrint.append("------------------------------------------------------------------------------------------\n");
        for(int i = 0; i < sortedMovies.size(); i++ ){
            if(sortedMovies.get(i).getTitle().length() > 36){
                temp = sortedMovies.get(i).getTitle().substring(0, 43) + "...";
                toPrint.append(String.format("%-46s%2s%4d%2s%-50s", temp, "  ",
                        sortedMovies.get(i).getYear(), "  ", sortedMovies.get(i).actorsToString()));
            }
            else{
                toPrint.append(String.format("%-46s%2s%4d%2s%-50s", sortedMovies.get(i).getTitle(), "  ",
                  sortedMovies.get(i).getYear(), "  ", sortedMovies.get(i).actorsToString()));
            }
            toPrint.append("\n");
        }
        toPrint.append("\n");
        System.out.print(toPrint.toString());
    }
}
