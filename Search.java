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
		if(charIndex >= input.length){
			return false;
		}

		if((stateData[stateIndex][0] + stateData[stateIndex][1]) != 0){
			if(Character.toString(input[charIndex]).equals(stateString[stateIndex]) || stateString[stateIndex].equals("BRANCH")){
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