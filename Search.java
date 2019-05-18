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
			if(checkDeque(new Deque(startIndex), i)){
				return true;
			}
		}

		return false;


	}



	public Boolean checkDeque(Deque d, int charIndex){

		while(d.head != null){
			int head = d.get();

			if (stateString[head].equals("END")) {
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
				continue;
			}

			if(stateString[head].contains("NOTLIST")){
				if(!(checkList(stateString[head].split("IST")[1], charIndex))){
					charIndex ++;
					d.enque(stateData[head][0]);
					if (stateData[head][0] != stateData[head][1]) {
						d.enque(stateData[head][1]);
					}
				}
				continue;
			}



			if(stateString[head].contains("LIST")){
				if(checkList(stateString[head].split("IST")[1], charIndex)){
					charIndex ++;
					d.enque(stateData[head][0]);
					if (stateData[head][0] != stateData[head][1]) {
						d.enque(stateData[head][1]);
					}
				}
				continue;
			}




			if(Character.toString(input[charIndex]).equals(stateString[head]) || stateString[head].equals("WILDCARD")){
				charIndex++;
				d.enque(stateData[head][0]);
				if (stateData[head][0] != stateData[head][1]) {
					d.enque(stateData[head][1]);
				}
				continue;
			}





		}

		return false;

	}

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