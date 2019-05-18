import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class REsearcherL{
	static int[][] stateData;
	static String[] stateString;
	static int startingState;
	
	public static void main(String[] args){

		if(args.length != 1){
			System.out.println("Please enter a file to search");
			return;
		}

		stateData = new int[100][2];
		stateString = new String[100];
		makeStateArray();

		if(stateString[0] == null){
			return;
		}

		for(int i =0; i < stateData.length; i++){
			System.out.println(stateString[i] + ", " +stateData[i][0]+ ", " + stateData[i][1]);
		}
		// for(int[] state : stateData){
			// System.out.println(state[0] + ", " +state[1]+ ", " + state[2]);
		// }
		
		Search s = new Search(stateData, stateString, startingState);


		try{
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String pattern = br.readLine();
			while(pattern != null){
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
//
//
//		String pattern = "Hellozzzjacd";
//
//		if(s.searchString(pattern)){
//			System.out.println(pattern);
//		}
//		else{
//			System.out.println("Not found");
//		}
//
//		System.out.println("##########################################################################################");
//		System.out.println("chech with dick");
//		System.out.println("##########################################################################################");
//
//		if(s.searchWithDeque(pattern)){
//			System.out.println(pattern);
//		}
//		else{
//			System.out.println("Not found");
//		}
		

		
		
	}
	
	public static void makeStateArray(){
		

		int indexArray = 0;

		
		try{
			Scanner scanner = new Scanner(System.in);
			String input = "";
			
			while(scanner.hasNextLine()){
				input = scanner.nextLine();
				if(input.equals("--+--+-+-+") || input.equals("s  ch 1 2")){
					continue;
				}
				else if(input.split(" ")[0].equals("starting")){
					startingState = Integer.parseInt(input.split(" ")[3]);
					
				}
				else{
					input = input.replaceAll("\\s{2,}", " ");
					// System.out.println(input);
					stateString[indexArray] = (input.split(" ")[1]);
					// System.out.println((input.split(" ")[1]));
					stateData[indexArray][0] = Integer.parseInt(input.split(" ")[2]);
					stateData[indexArray][1] = Integer.parseInt(input.split(" ")[3]);
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


		