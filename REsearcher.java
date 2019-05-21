// Joshua Cronin 1212942
// Luke Weston 1336265


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class REsearcherL{
	//Array that holds index of current state and indexs of branching states
	static int[][] stateData;
	//String array that has the string/char each state is looking for
	static String[] stateString;
	//Starting state of the searcher
	static int startingState;
	
	public static void main(String[] args){
		//check a file has been passed in
		if(args.length != 1){
			System.out.println("Please enter a file to search");
			return;
		}

		//init arrays, size 100
		stateData = new int[100][2];
		stateString = new String[100];
		makeStateArray();

		//if no complied regular expression found, return
		if(stateString[0] == null){
			return;
		}
		
		//Print out the parsed complied regular expression
		for(int i =0; i < stateData.length; i++){
			System.out.println(stateString[i] + ", " +stateData[i][0]+ ", " + stateData[i][1]);
		}

		//create a new Search object with parsed state data
		Search s = new Search(stateData, stateString, startingState);

		//read in the file line by line
		try{
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String pattern = br.readLine();
			while(pattern != null){
				//See if compiled regular expression finds a match on any line, if so print the line
				if(s.searchWithDeque(pattern)){
					System.out.println(pattern);
				}
				pattern = br.readLine();
			}

			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}


		
		
	}
	
	
	//Makes state arrays by reading in the output of REcomplier from System.in
	public static void makeStateArray(){
		
		//keeps track of where items are going to be put in array
		int indexArray = 0;

		//Make scanner and read system.in line by line
		try{
			Scanner scanner = new Scanner(System.in);
			String input = "";
			
			while(scanner.hasNextLine()){
				input = scanner.nextLine();
				//Lines used for formatting, skip
				if(input.equals("--+--+-+-+") || input.equals("s  ch 1 2")){
					continue;
				}
				//Get starting index
				else if(input.split(" ")[0].equals("starting")){
					startingState = Integer.parseInt(input.split(" ")[3]);
					
				}
				//else parse data into arrays
				else{
					//format input so data is separated by a single space
					input = input.replaceAll("\\s{2,}", " ");
					//Put char part into stateString array
					stateString[indexArray] = (input.split(" ")[1]);
					//Put next states into state array
					stateData[indexArray][0] = Integer.parseInt(input.split(" ")[2]);
					stateData[indexArray][1] = Integer.parseInt(input.split(" ")[3]);
					//Increment index of array
					indexArray ++;
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		if(stateString[0] == null){
			return;
		}
		
		//Trim the state array
		int size = 0;
		for(String state : stateString){
			size ++;			
			if(state.equals("END")){
				break;
			}
		}

		
		int[][] temp = new int[size][2];
		String[] temp2 = new String[size];
		
		for(int i =0; i < size; i++){
			temp[i] = stateData[i];
			temp2[i] = stateString[i];
		}
		stateData = temp;
		stateString = temp2;
		

	}
			
	
}


		