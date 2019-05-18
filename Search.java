public class Search{
	
	public int [][] stateData;
	public String[] stateString;
	public int startIndex;
	public int j;
	char[] input;
	
	
	
	public Search(int[][] states, String[] strings, int startI){
		
		stateData = states;
		stateString = strings;
		startIndex = startI;	
		input = new char[0];
		
	}


	public Boolean searchWithDeque(String s){

		input = s.toCharArray();


		for(int i = 0; i <= input.length; i++){
			System.out.println("new deque");
			if(checkDeque(new Deque(startIndex), i)){
				return true;
			}
		}

		return false;


	}



	public Boolean checkDeque(Deque d, int charIndex){

		d.print();
		while(d.head != null){
			int head = d.get();

			if (stateString[head].equals("END")) {
				System.out.println("Match");
				return true;
			}

			if (charIndex >= input.length) {
				return false;
			}


			if (stateString[head].equals("BRANCH")) {
				d.push(stateData[head][1]);
				if (stateData[head][0] != stateData[head][1]) {
					d.push(stateData[head][0]);
				}
				d.print();
				continue;
			}


//			if (stateString[head].equals("WILDCARD")) {
//				charIndex++;
//				d.enque(stateData[head][0]);
//				if (stateData[head][0] != stateData[head][1]) {
//					d.enque(stateData[head][1]);
//				}
//				d.print();
//				continue;
//			}


			if(Character.toString(input[charIndex]).equals(stateString[head]) || stateString[head].equals("WILDCARD")){
				charIndex++;
				d.enque(stateData[head][0]);
				if (stateData[head][0] != stateData[head][1]) {
					d.enque(stateData[head][1]);
				}
				d.print();
				continue;
			}



		}

		return false;

	}


	
	
	public Boolean searchString(String s){
		input = s.toCharArray();
			
		
		
		for(int i =0; i <= input.length; i++){

			if(checkState(startIndex, i)){
				return true;
			}
			
		}
		return false;
			
	}
	
	public Boolean checkState(int stateIndex, int charIndex){
		if(stateString[stateIndex].equals("WILDCARD")){
			charIndex += 1;
		}
		if(charIndex >= input.length){
			return false;
		}

		if((stateData[stateIndex][0] + stateData[stateIndex][1]) != 0){
			if(Character.toString(input[charIndex]).equals(stateString[stateIndex]) || stateString[stateIndex].equals("BRANCH") ||stateString[stateIndex].equals("WILDCARD") ){	
				if(checkState(stateData[stateIndex][0], charIndex++)){
					return true;
				}
				if(checkState(stateData[stateIndex][1], charIndex++)){
					return true;
				}
				return false;
				
			}
			else{
				return false;
			}
		}
		else if(stateString[stateIndex].equals("END") && stateData[stateIndex][0] == 0 && stateData[stateIndex][1] == 0){
			System.out.println("match");
			return true;
		}
	
		return false;
		
	}
}