public class Search{
	
	public int [][] stateData;
	public int startIndex;
	public int j;
	char[] input;
	
	
	
	public Search(int[][] states, int startI){
		
		stateData = states;
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

		if((stateData[stateIndex][1] + stateData[stateIndex][2]) != 0){
			if((int)input[charIndex] == stateData[stateIndex][0] || stateData[stateIndex][0] == 0){
				if(checkState(stateData[stateIndex][1], charIndex++)){
					return true;
				}
				if(checkState(stateData[stateIndex][2], charIndex++)){
					return true;
				}
				return false;
				
			}
			else{
				return false;
			}
		}
		else if(stateData[stateIndex][0] == 0 && stateData[stateIndex][1] == 0 && stateData[stateIndex][2] == 0){
			System.out.println("match");
			return true;
		}
	
		return false;
		
	}
}