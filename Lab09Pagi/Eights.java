import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;
    private static ArrayList<Player> arrayPlayer = new ArrayList<>(); 

    /**
     * Initializes the state of the game.
     */
    public Eights(ArrayList<Player> arr) {
        this.arrayPlayer = arr;
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // deal cards to each player
        for(Player player : arrayPlayer){
            deck.deal(player.getHand(), 5);
        }

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner 
        // we'll use to wait for the user to press enter
        in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        for(Player player : arrayPlayer){
            if(player.getHand().isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        for(int i = 0; i<arrayPlayer.size();i++){
            if(arrayPlayer.get(i).equals(current)){
                if(i==arrayPlayer.size()-1){
                    return arrayPlayer.get(0);
                }else{
                    return arrayPlayer.get(i+1);
                }
            }
        }
        return null;
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for(Player player : arrayPlayer){
            player.display();
        }

        discardPile.display();
        System.out.println("Draw pile: " + drawPile.size() + " cards");
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Scanner s = new Scanner(System.in);

        Card prev=null;

        if (player instanceof HumanPlayer){
            System.out.println("Pilih Ranks: "); //minta indexnya
            int Ranksnya = s.nextInt();
            s.nextLine();
            System.out.println("Pilih Suits:");  //minta indexnya
            int Suitsnya = s.nextInt();
            s.nextLine();

            prev = new Card(Ranksnya, Suitsnya);
        }
        else if (player instanceof AIPlayerLast) {
            prev = discardPile.lastCard();
        }
        else if (player instanceof AIPlayerAdvanced) {
            prev = player.searchForMax();
        }


        Card next = player.play(this, prev);


        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
        
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        Player player = arrayPlayer.get(0);

        // keep playing until there's a winner
        while (!isDone()) {
            displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

    }

    /**
     * Creates the game and runs it.
     */
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Mau main dengan berapa player: ");
        int jumlahpemain = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahpemain; i++) {
            System.out.println("Kamu tuh apa?: ");
            String jenisMahkluk = input.nextLine();

            if (jenisMahkluk.equalsIgnoreCase("manusia")) {
                System.out.println("Nama kamu siapa?: ");
                String nama = input.nextLine();
                HumanPlayer manusia = new HumanPlayer(nama);
                arrayPlayer.add(manusia);

            }
            else if (jenisMahkluk.equalsIgnoreCase("ai")) {
                System.out.println("Kamu AI jenis apa: ");
                String AIapa = input.nextLine();
                if (AIapa.equalsIgnoreCase("aicanggih")) {
                    AIPlayerAdvanced AIcanggih = new AIPlayerAdvanced("BOT CANGGIH");
                    arrayPlayer.add(AIcanggih);
                }
                else if (AIapa.equalsIgnoreCase("aibiasa")) {
                    AIPlayerLast AIbiasa = new AIPlayerLast("BOT BIASA");
                    arrayPlayer.add(AIbiasa);
                }
            }


        }

        // Player one = new Player("Allen");
        // Player two = new Player("Chris");
        // Player three = new Player("Alif");
        // Player four = new Player("Hafiz");
        // Player five = new Player("Grady");

    
        System.out.println("Berapa kali kamu mau main: ");
        int bermain = input.nextInt();
        input.nextLine();
        HashMap<String,Integer> skor = new HashMap<>();

        int hitungmain = 0;
        while (hitungmain < bermain) {
            System.out.println(arrayPlayer.get(0).getName());
            Eights game = new Eights(arrayPlayer);
            game.playGame();
            hitungmain++;
        }

        // winner
        Player winner = null;

        for (int i = 0; i < arrayPlayer.size(); i++) {
            arrayPlayer.get(i).displayScore();
            if(arrayPlayer.get(i).score()> winner.score() ){
                winner = arrayPlayer.get(i);
                if (skor.containsKey(winner.getName())){
                    skor.put(winner.getName(), skor.get(winner.getName()) + 1);
                }
                skor.put(winner.getName(), 1);
            }
        }

        File highscores = new File("highscores-eight.txt");
        Map<String, String> readFile = new HashMap<>();

        try {
            Scanner scanner = new Scanner(highscores);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] arr = line.split(" ");
                if ( arr.length == 2 ) {
                    readFile.put(arr[0], arr[1]);
                    int angka = Integer.parseInt(arr[1]);
                    if ( skor.containsKey(arr[0]) ){
                        skor.put(arr[0], skor.get(arr[0]) + angka );
                        
                    } else{
                        skor.put(arr[0],angka);
                    }
                    

                }
                else {throw new ExceptionFormatTidakSesuai("Satu Baris lebih dari 2 nilai");

            }
        }
    } catch (Exception e){

    }


        // try{
        //     File txt= new File("highscores-eight.txt");
        //     Scanner Reader = new Scanner (txt);
        //     HashMap<Integer, String> hm = new HashMap<>();
        //     int counter = 0;
        //     while (Reader.hasNextLine()){
        //        String data = Reader.nextLine();
        //        if (data.contains(winner.getName())){
        //         String[] arr = data.split("");
        //         arr[1] = String.valueOf(winner.score());
        //         data = arr[0]+arr[1];
        //        }
        //        hm.put(counter,data);
        //     }
        //     FileWriter Writer = new FileWriter("highscores-eight.txt") ;
        //     for(int i = 0; i < hm.size(); i++ ){
        //         Writer.write(hm.get(i));
        //     }

        // } catch (IOException e){
        //     System.out.println(" errorr");
        // }



        // // display the final score
        // // one.displayScore();
        // // two.displayScore();
        // // three.displayScore();
        // // four.displayScore();
        // // five.displayScore();

        input.close();

        try{
            FileWriter writer = new FileWriter("highscores-eight.txt");
            for (Map.Entry<String, Integer > entry : skor.entrySet()){
                if(entry.getKey().equalsIgnoreCase(winner.getName())){
                    String scoreStr = String.valueOf(skor.get(entry.getKey()));
                    writer.write(entry.getKey()+" "+scoreStr);
                }
                



                }
                
            
            
        } catch (Exception ex) {
                System.out.println("Exception: File not found");

            }
            
    }

}