///SPECIAL STRINGS
/// "END" -> end of finitite state machine
/// "BRANCH" -> Branching state, not gonna look for a match
/// "WILDCARD" = "." -> Matches anything





public class REcompiler {

    //Regular expression phrase
    static char[] p;
    //counter of the index we are up to on the regular expression
    static int j;
    //Lists of states
    static int[] next1, next2;
    //list of chars
    static String[] ch;
    //Current State
    static int state;
    //Branching state
    static int enter = 0;
    static int start = 0;
    static boolean startSet = false;
    static boolean newEnter = false;
    static String currList = "";
	//static final char BR = '!';	
	static final String BR = "BRANCH";

    public static void main(String[] args) {
        p = args[0].toCharArray();
        j = 0;
        next1 = new int[100];
        next2 = new int[100];
        ch = new String[100];
        state = 0;
        parse();

        printMachine();
    }

    public static void parse()
    {
      int initial;

      initial = expression();
       if(j!= p.length){
          error();
        }
      //if (p[j])
      // error(); // In C, zero is false, not zero is true
      setState(state, "END", 0, 0);
	  //setState(state, ' ', 0, 0);
	   
      System.out.println("starting state = " + initial);
    }

    public static void printMachine(){
      System.out.println("s  ch 1 2");
      System.out.println("--+--+-+-+");

      int i = 0;

      //Look into this and why it doesnt work with ints
      while(!(ch[i].equals("END"))){
          System.out.println(String.format("%1s|%10s %3s %4s", i, ch[i], next1[i], next2[i]));
          i++;
      }
        System.out.println(String.format("%1s|%10s %3s %4s", i, ch[i], next1[i], next2[i]));
		



    }

    public static int factor() {
      
        int r = 0;
        if (isVocab(p[j])) {
            setState(state, Character.toString(p[j]), state + 1, state + 1);
            
            j++;
            r = state;
            state++;
            return r;
        }
        //Special case if p[j] is an escape character, move over it and consume the next character no matter what it is. 
        else if(p[j] == '\\'){
            //consume the '\'
            j++;
            //Set the state machine with whatever matches next
            setState(state, Character.toString(p[j]), state + 1, state + 1);
            j++;
            
            r = state;
            state++;
            return r;
        }
        else if (p[j] == '(') {
            j++;
            r = expression();
            //Need to check the length of j here else we will go off the edge. 
            if (j< p.length && p[j] == ')') {
                j++;
            }
            else {
                error();
            }

        }


		//WILDCARD
		else if(p[j] == '.'){
			
			setState(state, "WILDCARD", state + 1, state + 1);
            j++;
           
            r = state;
            state++;
            return r;
    }
    //list

    else if(p[j] == '['){
      j++;
      currList = "LIST" + p[j];
      j++;
      list();

      if (j< p.length && p[j] == ']') {
          j++;
      }
      else {
          error();
      }
      setState(state, currList, state + 1, state + 1);
      r = state;
      state++;
      return r;
    }

    //notlist
    else if(p[j] == '^'){
      j++;
      if(p[j] =='['){
        j++;
        currList = "NOTLIST" + p[j];
        j++;
        list();

        if (j< p.length && p[j] == ']') {
            j++;
        }
        else {
            error();
        }
        setState(state, currList, state + 1, state + 1);
        r = state;
        state++;
        return r;
      }
      else{
        error();
      }
    }

    else{
	
       error();
    }
      

    return r;
    }
    public static void list(){
      
      if((j< p.length) && (p[j]!= ']')){

        currList += p[j];
        j++;

        list();
      }
      return;
    }



    public static int expression(){

        int r;
        int t1;
        r = t1= term();
        if (!startSet){
          start = r;
          startSet = true;
        }
        if (j < p.length) {
          if (isVocab(p[j]) || p[j] == '(' || p[j] =='\\' || p[j] == '.') {
           enter = expression();
           if(newEnter){
            r = enter;
           }


          }
          else if (p[j] == '|') {
            int f, t2;
            
            setState(state, BR, state+1, state+1);
            state++;
            f = state -1;
            //Special case for if it starts with an or statement
            if(f == -1){
    
                setState(state, BR, 1, 1);


                f = 0;
            }

            if (next1[f] == next2[f]) {
                next2[f] = state;
            }

            next1[f] = state;

            f = state - 1;
            j++;
            r = state;
            state++;
            t2 = term();
            setState(r, BR, start, t2);
            
            if (next1[f] == next2[f]) {
                next2[f] = state;

            }
            next1[f] = state;
            enter = r;
            newEnter = true;
          }
        }
        
        return r;
    }


    public static int term() {
        int f, r, t1, t2;
        f = state - 1;

        r = t1 = factor();
        
        //System.out.println(t1);
        if(j<p.length){
          if (p[j] == '*') {
              j++;
              setState(state, BR, state + 1, t1);
              if(f>= 0)
              {
                  
                  next1[f] = next2[f] = state;
              }
              r = state;
              state++;
              
          }
          else if(p[j] == '?'){
            //similar to * but need to change previous state to go to state if it matches,
            j++;
            //set the current state as a simple dummy state, which will skip over the next state to the end
            setState(state, BR, state + 2, state +2);
            state++;
            //set the next state, which will be the statring state of the machine, to branch to the end or the start of the previously set machine. 
            setState(state, BR, state +1, r);
            if(f>= 0)
              {
                  
                  next1[f] = next2[f] = state;
              }
            r = state;
            state++;
          }
          
        }
        return r;
    }


    public static void setState(int s, String c, int s1, int s2) {
        ch[s] = c;
        next1[s] = s1;
        next2[s] = s2;
    }


    public static void error(){
        System.err.println("Regular expression invalid");
        System.exit(0);

    }

    public static boolean isVocab(char c){

        char[] illegalChars = new char[] {'(',')','.','|','*','^','[',']','?','\\'};

        return !String.valueOf(illegalChars).contains(Character.toString(c));

    }



}
