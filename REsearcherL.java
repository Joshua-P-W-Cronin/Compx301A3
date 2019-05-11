import java.util.Scanner;

public class REsearcherL{
	static int[][] stateData;
	static int startingState;
	
	public static void main(String[] args){
		
		stateData = new int[100][3];
		makeStateArray();
		
		
		for(int[] state : stateData){
			System.out.println(state[0] + ", " +state[1]+ ", " + state[2]);
		}
		
		Search s = new Search(stateData, startingState);
		
		String pattern = "Hellozzzjacd";
		
		if(s.searchString(pattern)){
			System.out.println(pattern);
		}
		else{
			System.out.println("Not found");
		}
		

		
		
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
					//System.out.println(input);
					stateData[indexArray][0] = (int)(input.split(" ")[2].charAt(0));
					stateData[indexArray][1] = Integer.parseInt(input.split(" ")[3]);
					stateData[indexArray][2] = Integer.parseInt(input.split(" ")[4]);
					indexArray ++;
				}
				
				
	
				
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Trim the state array
		int size = 0;
		for(int[] state : stateData){
			size ++;
			if(state[0] + state[1] + state[2]  == 0){
				break;
			}
		}
		
		int[][] temp = new int[size][3];
		
		for(int i =0; i < size; i++){
			temp[i] = stateData[i];
		}
		stateData = temp;
		

	}
			
	
}


		