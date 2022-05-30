/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sociopath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Lidros
 */

public class TestSociopath {

    /**
     * @param args the command line arguments
     */
    private static SociopathGraph sociopath = new SociopathGraph();
    private static Student mc = new Student(); //mc of the story
    private static Random r = new java.util.Random();
    private static Student strangerEvent1;  //(event 2)
    private static LinkedList<Integer> book_height ;  //list of book's height (event 4)
    private static Stack<Integer> remove_stack ; //list of book that need to be remove (event 4)
    private static LinkedList<Student> knowRumor = new LinkedList<>(); //list of people who know rumor (event 5)
    private static Student rumor = new Student();
    private static Student crush = new Student();
    private static ArrayList<Integer>[] personList; //list of person (event 6)
    private static int num_friend; //(event 6)
    private static int count; //(event 6)
    
    public static void main(String[] args) {
        //creating the students
        //Student(Double divingRate, Integer rep, Integer lunchTime, Integer lunchPeriod)
        //String[] nama = new String[]{"Abu", "Barb", "Chim", "Dar", "Edo", "Fad", "Gotye", "Hal", "Indie", "Jack"};
        Scanner in = new Scanner(System.in);
        
        for (Integer i = 1; i < 11; i++) {
            int rep = r.nextInt(11);    //generate from 0 to 10
            double diverate = 0 + (100 - 0) * r.nextDouble();    //generate from 0 to 100
            int lunchtime = ((r.nextInt(14 - 11) + 11)*100) + r.nextInt(59) ;    //generate from 1100 to 1400
            int lunchperiod = r.nextInt(60 - 5) + 5;    //generate from 5 to 60
            //sociopath.addVertex(new Student(nama[i], diverate, rep, lunchtime, lunchperiod));
            sociopath.addVertex(new Student(i.toString(), diverate, rep, lunchtime, lunchperiod));
        }
        
        System.out.println("*******************\n");
        System.out.println("THE SOCIOPATH\n");
        System.out.println("*******************\n");
        System.out.print("Pick a number(1-10) as your main character : ");
        pickMC();
        System.out.println("Your mc : " + mc.getName());
        System.out.println(" ");

        sociopath.beFriends("1", "7", 4, 3);
        sociopath.beFriends("1", "2", 5, 8);
        sociopath.beFriends("2", "3", 5, 4);
        sociopath.beFriends("2", "5", 6, 2);
        sociopath.beFriends("2", "6", 9, 7);
        sociopath.beFriends("4", "8", 7, 10);
        sociopath.beFriends("4", "10", 7, 7);
        sociopath.beFriends("9", "10", 5, 6);
        System.out.println("");

        //-----Start loop game--------
        int day = 1;
        int week = 1;
        int eventCount;  //2 event per day
        
         //7 days per week
        while (day < 8) {
            //intro
            eventCount = 2;
            System.out.println("Day " + day + ", Week " + week);
            System.out.println("You can do " + eventCount + " event today");
            
            //event loop
            while (eventCount > 0) {
                System.out.println("Choose event that you want to do :");   
                System.out.println("\n*******************\n");
                System.out.println("1. Teaching a stranger to solve lab questions");
                System.out.println("2. Chit-Chat");
                System.out.println("3. Road to glory");
                System.out.println("4. Arranging Books");
                System.out.println("5. Meet Crush");
                System.out.println("6. Friendship");
                System.out.println("Enter '0' to exit game");
                System.out.println("\n*******************\n");
                System.out.print("Your choice : ");
                int choice = in.nextInt();
                
                switch (choice) {
                    case 1: {
                        System.out.println("\n********** Event 1 Started **********\n");
                        event1();
                        System.out.println("");
                        break;
                    }
                    case 2: {
                        System.out.println("\n********** Event 2 Started **********\n");
                        event2();
                        System.out.println("");
                        break;
                    }
                    case 3: {
                        System.out.println("\n********** Event 3 Started **********\n");
                        event3();
                        System.out.println("");
                        break;
                    }
                    case 4: {
                        System.out.println("\n********** Event 4 Started **********\n");
                        event4();
                        System.out.println("");
                        break;
                    }
                    case 5: {
                        System.out.println("\n********** Event 5 Started **********\n");
                        event5();
                        System.out.println("");
                        break;
                    }
                    case 6: {
                        System.out.println("\n********** Event 6 Started **********\n");
                        event6();
                        System.out.println("");
                        break;
                    }
                    case 0: {
                        System.out.println("\n********** Thank You for Playing *********\n");
                        return;
                    }
                }
                eventCount--;
            }
            System.out.println("Day " + day + " has ended");
            System.out.println("*******************************");
            System.out.println("");
            day++;
        }
    }
    
    /**
     * picking random MC
     */
    public static void pickMC(){
        Scanner in = new Scanner(System.in);
        mc = sociopath.getStudent(in.nextInt()-1);
    }
    
    /**
     * MC teaching a stranger
     * gain 10 points for good teaching from the stranger
     * gain 2 points for bad teaching from the stranger
     * the stranger gain 2 points from MC
     * 1. determine who is the stranger to be teach
     * 2. determine bad/good teaching
     * 3. result of rep gain of MC and stranger
     * @param none
     * @return none
     */
    public static void event1(){    //for random stranger
        // 1. determine who is the stranger to teach
        do {
            strangerEvent1 = sociopath.getStudent(r.nextInt(10)); //keep on changing the random student until it's stranger
            //System.out.println(sociopath.isFriends(mc.getName(), stranger.getName()));
        } while (sociopath.isFriends(mc.getName(), strangerEvent1.getName()) || mc.compareTo(strangerEvent1) == 0);
        System.out.println("mc: " + mc.getName());
        System.out.println("stranger: " + strangerEvent1.getName());
        
        //2. determine bad/good teaching
        int quality = r.nextInt(2);
        boolean good;
        if (quality == 0){   //3. bad
            good = false;
            System.out.println("MC done bad teaching");
            sociopath.beFriends(mc.getName(), strangerEvent1.getName(), 2, 2);    //mc gives 2 rep, stranger gives 2 rep
            System.out.println("rating " + mc.getName() + " from " + strangerEvent1.getName() + ": " + sociopath.getEdge(mc.getName(), strangerEvent1.getName()));
            System.out.println("rating " + strangerEvent1.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(strangerEvent1.getName(), mc.getName()));
        }
        else{
            good = true;    //3. good
            System.out.println("MC done a good teaching");
            sociopath.beFriends(mc.getName(), strangerEvent1.getName(), 2, 10);   //mc gives 2 rep, stranger gives 10 rep
            System.out.println("rating " + mc.getName() + " from " + strangerEvent1.getName() + ": " + sociopath.getEdge(mc.getName(), strangerEvent1.getName()));
            System.out.println("rating " + strangerEvent1.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(strangerEvent1.getName(), mc.getName()));
        }
    }
    
    /**
     * 1. identify gossiper = strangerEvent1
     * 2. identify kawan stranger
     * 3. determine good or bad talk (70% good 30% bad)
     * 4. buat edge aka beFriends
     */
    public static void event2(){  
        
        
        // 1.
        Student gossiper = strangerEvent1;
        
        //2.
        int index;
        Student listener;
        do {
            index = r.nextInt(10);
            listener = sociopath.getStudent(index);
            //System.out.println("Gossiper & Listener friends: " + sociopath.isFriends(gossiper.getName(), listener.getName()));
        } while (!sociopath.isFriends(gossiper.getName(), listener.getName()) || listener == mc);
        
        //1st check mc kawan with listener
        //System.out.println("1st check mc kawan with listener: " + sociopath.isFriends(mc.getName(), listener.getName()) + " " + sociopath.getEdge(mc.getName(), listener.getName()));
        System.out.println("Gossiper: " + gossiper.getName());
        System.out.println("Listener: " + listener.getName());
        
        // 3.
        int talk = r.nextInt(10);
        
        // 4.
        int rate = (sociopath.getEdge(mc.getName(), gossiper.getName()));
        if (talk < 7) { //klu cakap baik
            System.out.println(gossiper.getName() + " talk good, so +" + rate/2);
            sociopath.increaseRep(mc.getName(), listener.getName(), rate/2);
        }
        else{
            System.out.println(gossiper.getName() + " talk bad, so " + (rate-(2*rate)));
            sociopath.beFriends(mc.getName(), listener.getName(), 0, rate-(2*rate) );
        }
        
        //2nd check mc kawan with listener
        //System.out.println("2nd check mc kawan with listener: " + sociopath.isFriends(mc.getName(), listener.getName()) + " " + sociopath.getEdge(mc.getName(), listener.getName()));
        System.out.println("rating " + mc.getName() + " from " + listener.getName() + ": " + sociopath.getEdge(mc.getName(), listener.getName()));
        System.out.println("rating " + listener.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(listener.getName(), mc.getName()));
    }
    
    /**
     * 1. criteria of the lunch-with
     *  - friend of mc
     *  - lowest dive rate
     *  - clash
     * 2. effect after lunch
     *  - +1 rep from lunch-with
     */
    public static void event3(){
        //1. determine lunch-with
        ArrayList<Student> friendsMC = sociopath.getFriends(mc.getName());
        //System.out.println(friendsMC);
        System.out.println("MC's friend(s)");
        for (int i = 0; i < friendsMC.size(); i++) {
            System.out.println(friendsMC.get(i).getName());
        }
        ArrayList<Student> clash = new ArrayList<>();
        //System.out.println(clash);
        Student lunchWith = new Student();
        
        
        for (int i = 0; i < friendsMC.size(); i++) {    //loop through mc'friends
            if (mc.clashLunch(friendsMC.get(i))) {  //if clash lunch time insert the student into clash arraylist
                clash.add(friendsMC.get(i));
            }
        }
        if (clash.size() == 1) {
            //System.out.println("here2");
            lunchWith  = clash.get(0);
        }
        else{
            for (int i = 0; i < clash.size()-1; i++) {
                if(clash.get(i).getDivingRate() > clash.get(i+1).getDivingRate()){   //get the lowest diving rate
                    lunchWith = clash.get(i+1);
                }
            }
        }
        System.out.println("lunchWith: " + lunchWith.getName());
        //System.out.println("kat sini");
        
        //2. effect after lunch(give rating)
        if (lunchWith.getName() != null) {  //if there is lunchWith
            sociopath.increaseRep(mc.getName(), lunchWith.getName(), 1);
            System.out.println("rating " + mc.getName() + " from " + lunchWith.getName() + ": " + sociopath.getEdge(mc.getName(), lunchWith.getName()));
            return;
        }
        return;
    }
    
    /**
     * 1. get the number to be generated
     * 2. generate integer (maximum 35cm) with integer count
     * 3. Stack part
     */
    public static void event4(){
        book_height = new LinkedList<>();  //list of book's height
        remove_stack  = new Stack<>();  //list of book that need to be remove
            
        Scanner in = new Scanner(System.in); 
        int count = 0; //set the number of round to 0
        int num_books;
        
        //get user the number of book that need to be arranged
        System.out.println("Enter number of books in a row: ");
        num_books = in.nextInt(); 
        
        //get the height off all the books
        System.out.println("Enter height of all books: ");
        for(int i=0; i<num_books; i++){
            book_height.add(in.nextInt());  //add the input of book's height into the book height list
        }
        
        addRemoveList(); //add all the books that need to be remove into the list
        
        // iterate trough all the book height list to search for the book(s) that need to be removed
        for(int i=0; i<num_books; i++){
            for(int j=0; j<book_height.size()-1; j++){
                if(book_height.get(j+1)>book_height.get(j)){
                    book_height.remove(j+1);  // remove the elements that were arranged in ascending order
                    remove_stack.pop();   // remove the first element in remove list
                    j=0; // reset j=0 to check again for each element in book height list
                    if(remove_stack.isEmpty()){
                        count++;  // increase number of count when remove list is empty showed that a round has ended
                        addRemoveList(); // the book height list will be checked again to make sure every book height was in descending order
                        break; // break loop when there are no book that needed to be remove means that the book height list was in ascending order
                    }
                }
            }
        }
        //ouput
        System.out.println("\nBook arrangement :" + book_height);
        System.out.println("Number of round(s) needed : "+ count + "\n");
    }
    
    /**
     * 1. find the one who starts the rumor and who crush
     * - not in mc's cluster (hence, not mc's friends' friends)
     * 2. rumor finding its path to crush
     * - if rumors tak jumpa crush, return
     * 3. mc finding its way to crush
     * 4. if (2) berjalan && mc cannot find its path to crush == return
     */
    public static void event5(){
        
        // 1. finding the one who starts the rumor
        // 2. rumor finding its path to crush
        // if rumors tak jumpa crush, return
        if (knowRumor.isEmpty()) {
            do {
            Random random = new Random();
                int forRumor;
                int forCrush;
            do {
                forRumor = random.nextInt(sociopath.size);
                forCrush = random.nextInt(sociopath.size);
            } while (forRumor == forCrush);
            rumor = sociopath.getStudent(forRumor);
            crush = sociopath.getStudent(forCrush);
            } while (sociopath.isFriends(mc.getName(), crush.getName()) || sociopath.isFriends(mc.getName(), rumor.getName()) || (rumor==mc) || (crush==mc) || (rumor==crush) || sociopath.isFriends(crush.getName(), rumor.getName()));
        //System.out.println("mc n rumor friends? " + sociopath.isFriends(mc.getName(), rumor.getName()));
        //System.out.println("mc n crush friends? " + sociopath.isFriends(mc.getName(), crush.getName()));
            System.out.println("rumor: " + rumor.getName());
            System.out.println("crush: " + crush.getName());
            knowRumor.add(rumor);
        }
        else{
            System.out.println("crush: " + crush.getName());
            System.out.println("crush know the rumor?: " + knowRumor.contains(crush));
            System.out.print("people who know rumor: ");
            for (int i = 0; i < knowRumor.size(); i++) {
                System.out.print(knowRumor.get(i).getName() + " ");
            }
            System.out.println("");
        }
            
        System.out.println("There are " + knowRumor.size() + " people who know your rumor");
        System.out.println("Are you not lazy enough to stop the rumor? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        switch(input){
            case "Y":{
                knowRumor.remove();
                if (knowRumor.size()==0) {
                    System.out.println("Congratz, the rumor already stopped");
                    break;
                }
                else{
                    knowRumor.remove();
                    for (int i = 0; i < sociopath.getFriends(rumor.getName()).size(); i++) {
                        if (!knowRumor.contains(sociopath.getFriends(rumor.getName()).get(i)) ) {
                            knowRumor.add(sociopath.getFriends(rumor.getName()).get(i));
                            rumor = sociopath.getFriends(rumor.getName()).get(i);
                            if(rumor.equals(crush)){
                                System.out.println("OHH NOOO!! the rumor has reached your crush");
                                sociopath.beFriends(mc.getName(), crush.getName(), 0, -10);
                                break;
                            }
                            break;
                        }
                    }
                    System.out.println("There are " + knowRumor.size() + " people who know your rumor");
                }   
                break;
            }
            case "N":{
                System.out.println("The rumor has spread");
                for (int i = 0; i < sociopath.getFriends(rumor.getName()).size(); i++) {
                    if (!knowRumor.contains(sociopath.getFriends(rumor.getName()).get(i)) ) {
                        knowRumor.add(sociopath.getFriends(rumor.getName()).get(i));
                        rumor = sociopath.getFriends(rumor.getName()).get(i);
                        if(rumor.equals(crush)){
                            System.out.println("OHH NOOO!! the rumor has reached your crush");
                            sociopath.beFriends(mc.getName(), crush.getName(), 0, -10);
                            break;
                        }
                        break;
                    }
                }
                System.out.println("There are " + knowRumor.size() + " people who know your rumor");
                break;
            }
        }
        
    }
    
    /**
     * 1. get number of existing friend of two
     * 2. get the list of existing friend of two
     * 3. calculate all the possible connection that can be made
     * 4. output the number of connection that can be made
     */
    public static void event6(){
        count = 1;
        String[] friend;
        Scanner in=new Scanner(System.in);
        
        System.out.println("Input : ");  //get the number of existing  friendships between 2 individuals
        int num=in.nextInt();
        num_friend = num + 1;
        personList = new ArrayList[num_friend];
        for (int i = 0; i < num_friend; i++) {
            personList[i] = new ArrayList<>(); //make an Arraylist of Arraylist 
        }
        
        int connect ;
        int [] arr0 = new int [num]; // array for the tenth position
        int [] arr1 = new int [num]; // array for the se position
        
        for(int i=0;i<num;i++){
            connect = in.nextInt();  
            arr0[i] = connect / 10; //get the tenth position
            arr1[i] = connect % 10; //get the se position
        }

        for(int i=0;i<num;i++){
            addExistingFriend(arr0[i],arr1[i]);  //add the friendships of 2 individuals
        }
        
        System.out.println("\nYou can form the following friendship(s) :");
        getAllPerson(1);
        System.out.println("\nOutput : " + (count - 1));
    }
    
    //method to check all the book height and the book that need to be removed was put into the remove list (event 4)
    public static void addRemoveList(){
        //iterate throgh all the book height list
        for(int i=0; i<book_height.size()-1; i++){
            if(book_height.get(i+1)>book_height.get(i)){ //check if the book are in ascending order or not
                remove_stack.push(book_height.get(i+1));  // if the book was in ascending order, the book will be added in remove list
            } 
            else if(book_height.get(i+1)== book_height.get(i)){ //check if the book are at the same height or not
                remove_stack.push(book_height.get(i+1));  // if the books have the same height, the book will be added in remove list
            } 
        }  
    }
    
    //method to add person in the list (event 6)
    public static void addExistingFriend(int personA, int personB){
        personList[personA].add(personB); //add person B in person A Arraylist
        personList[personB].add(personA); //add person A in person B Arraylist
    }
    
    //method to get all the person (event 6)
    public static void getAllPerson(int start){
        if(start!=num_friend){
            boolean[] isVisited = new boolean[num_friend];   //to check if the edge/friend has been visited or not
            ArrayList<Integer> pathList = new ArrayList<>();
            pathList.add(start); //start with 1
            for(int end=num_friend; end>start; end--){
                getAllFriend(start, end, isVisited, pathList);
            }
            getAllPerson(++start);
        }
        else return;
    }
    
    //method to print all the friend of a person (event 6)
    public static void getAllFriend(Integer start, Integer end, boolean[] hasVisited, List<Integer> pathList){
        if (start.equals(end)) {
            System.out.println(count+". "+pathList );
            count++;
            return;
        }
  
        hasVisited[start] = true;

        for (Integer i : personList[start]) {
            if (!hasVisited[i]) {
                pathList.add(i);
                getAllFriend(i, end, hasVisited, pathList);
                pathList.remove(i);
            }
        }
        hasVisited[start] = false;
    }
}