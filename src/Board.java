import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board implements IBoard {

	Square[][] board;
	Bag bag;
	Tile[] rack;

    Board(){
		initializeSquares();
		this.bag = new Bag();
	}
	
	/**
	 * Initializes the board with the appropriate squares.
	 */
	public void initializeSquares() {
		int[][] nums = {{4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4},
						{0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
						{0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
						{1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
						{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
						{0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
						{0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
						{4, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 4},
						{0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0},
						{0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0},
						{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
						{1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 1},
						{0, 0, 3, 0, 0, 0, 1, 0, 1, 0, 0, 0, 3, 0, 0},
						{0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 3, 0},
						{4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 1, 0, 0, 4}};
		this.board = new Square[15][15];
		for(int i = 0; i < nums.length; i++) {
			for(int j = 0; j < nums[i].length; j++) {
				this.board[i][j] = new Square(nums[i][j]);
			}
		}
	}
	
	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}
	
	public Bag getBag() {
	    return this.bag;
	}
	
    public Tile[] getRack() {
        return rack;
    }

    public void setRack(Tile[] rack) {
        this.rack = rack;
    }	
    
    public Tile getTile(int row, int col) {
        return board[row][col].getTile();
    }
    
    public Square getSquare(int row, int col) {
        return board[row][col];
    }    

	public void printBoard() {
		for(Square[] sqArr : board) {
			for(Square sq : sqArr) {
				System.out.print(sq.getSquareString() + " | ");
			}
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------");
		}
	}
	
	public void placeTile(Tile t, int row, int col) {
		board[row][col].setTile(t);
	}
	
    /**
     * Creates a random board
     */
    public void createRandomBoard() {
        File f = new File("scrabbleDictionary.txt");
        try {
            Scanner s = new Scanner(f);
            //obtain all words from the dictionary with <=7 letters
            ArrayList<String> words = new ArrayList<String>();
            while(s.hasNextLine()) {
                String currWord = s.nextLine();
                if(currWord.length() <= 7) {
                    words.add(currWord);
                }
            }
            //get a random word from that list
            int size = words.size();
            int randIndex = (int)Math.floor((Math.random() * size));
            String word = words.get(randIndex);
            while(!this.bag.hasLettersForWord(word)) {
                randIndex = (int)Math.floor((Math.random() * size));
                word = words.get(randIndex);
            }
            System.out.println("Got the word " + word);
            //place the word in the starting spot
            for(int i = 0; i < word.length(); i++) {
                Tile curr = this.bag.getTileByLetter(word.charAt(i));
                this.bag.removeTile(curr);
                placeTile(curr, 7, 7 + i);
            }
            //get all words with the same starting letter
            char first = word.charAt(0);
            ArrayList<String> sameLetterList = new ArrayList<String>();
            for(String currWord : words) {
                if(currWord.charAt(0) == first) {
                    sameLetterList.add(currWord);
                }
                else if(currWord.charAt(0) > first) {
                    break;
                }
            }
            int size2 = sameLetterList.size();
            int randIndex2 = (int)Math.floor((Math.random() * size2));
            String word2 = sameLetterList.get(randIndex2);
            String modifiedWord2 = word2.substring(1);
            while(!this.bag.hasLettersForWord(modifiedWord2)) {
                randIndex2 = (int)Math.floor((Math.random() * size2));
                word2 = sameLetterList.get(randIndex2);
                modifiedWord2 = word2.substring(1);
            }
            System.out.println("Got the second word: " + word2);
            for(int i = 1; i < word2.length(); i++) {
                Tile curr = this.bag.getTileByLetter(word2.charAt(i));
                this.bag.removeTile(curr);
                placeTile(curr, 7 + i, 7);            
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();		
		b.printBoard();
		b.createRandomBoard();
		b.printBoard();
	}
}
