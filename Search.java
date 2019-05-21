// Joshua Cronin 1212942
// Luke Weston 1336265


public class Search{
	//Array that holds index of current state and indexs of branching states
	public int [][] stateData;
	//String array that has the string/char each state is looking for
	public String[] stateString;
	//Starting state of the searcher
	public int startIndex;
	//input line split into a char array
	char[] input;
	
	
	
	public Search(int[][] states, String[] strings, int startI){
		//Assign passed in values to class variables
		stateData = states;
		stateString = strings;
		startIndex = startI;	
		input = new char[0];
		
	}


	//Search a passed string with a deque, return true if match, false if not
	public Boolean searchWithDeque(String s){

		//convert passed string to char array
		input = s.toCharArray();

		//check the string by starting at the first char and then incrementing
		for(int i = 0; i <= input.length; i++){
			//Create a Deque with only the intial state added to it
			if(checkDeque(new Deque(startIndex), i)){
				//If match found, return true
				return true;
			}
			//If no match, try starting at next char
		}

		return false;


	}


	//Check if the Deque finds a match, start searching input at the charIndex given
	public Boolean checkDeque(Deque d, int charIndex){

		
		while(d.head != null){
			int head = d.get();
			//If end of finite state maching found return true
			if (stateString[head].equals("END")) {
				return true;
			}
			//If out of input, return false
			if (charIndex >= input.length) {
				return false;
			}

			//If state is a branching state
			if (stateString[head].equals("BRANCH")) {
				//Then push the states to branch to to the front of the deque
				d.push(stateData[head][1]);
				//If states are unique, push both to the head
				if (stateData[head][0] != stateData[head][1]) {
					d.push(stateData[head][0]);
				}
				continue;
			}
			
			//Check for NOTLIST
			if(stateString[head].contains("NOTLIST")){
				//If no match found, increase char Index by one and enque 1 or 2 states if next states are unique
				if(!(checkList(stateString[head].split("IST")[1], charIndex))){
					charIndex ++;
					d.enque(stateData[head][0]);
					if (stateData[head][0] != stateData[head][1]) {
						d.enque(stateData[head][1]);
					}
				}
				continue;
			}


			//Check if LIST
			if(stateString[head].contains("LIST")){
				//If a match is found, increase char Index by one and enque 1 or 2 states if next states are unique 
				if(checkList(stateString[head].split("IST")[1], charIndex)){
					charIndex ++;
					d.enque(stateData[head][0]);
					if (stateData[head][0] != stateData[head][1]) {
						d.enque(stateData[head][1]);
					}
				}
				continue;
			}



			//If literal matches literal at index, or are looking for a wild card,, increase char Index by one and enque 1 or 2 states if next states are unique 
			if(Character.toString(input[charIndex]).equals(stateString[head]) || stateString[head].equals("WILDCARD")){
				charIndex++;
				d.enque(stateData[head][0]);
				if (stateData[head][0] != stateData[head][1]) {
					d.enque(stateData[head][1]);
				}
				continue;
			}





		}
		//If deque empty - not able to branch. Then no match found, return false
		return false;

	}
	
	
	//Checks char from input at charIndex matches any of the chars in the string list, returns true if a match is found. False if not
	public Boolean checkList(String list, int charIndex){

		char[] listChars = list.toCharArray();

		for (char c: listChars) {
			if(c == input[charIndex]){
				return true;
			}

		}

		return false;

	}


}