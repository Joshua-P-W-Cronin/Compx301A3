expression()
{
  term();
  if(isvocab(p[j])||p[j]=='(')
	expression();
}

term()
{
  factor();
  if(p[j]=='*') j++;
  if(p[j]=='+'){    j++;     term(); }
}

factor()
{
  if(isvocab(p[j])) j++;
  else
    if(p[j]=='('){
      j++;    expression();
      if(p[j]==')') j++;
      else error();
    }
    else error();
}

parse()
{
   expression();
   if(p[j] != '\0') error();
}

public class REparser{


    public static void main(String[] args) {
        parse();

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
      setState(state, '\0', 0, 0);
	  //setState(state, ' ', 0, 0);
	   
      System.out.println("starting state = " + initial);
    }

  
    public static void error(){
        System.err.println("Regular expression invalid");
        System.exit(0);

    }
    public static void expression()
    {
        term();
        if(isvocab(p[j])||p[j]=='('){
	       expression();
        }
        else if(p[j] == '?'){
            j++;
        }
        else if(p[j] == '*'){
            j++;
        }
        else if(p[j] == '|'){
            j++;
            expression();
        }
    }  
    public static void term(){
    
    } 

    public static boolean isVocab(char c){

        char[] illegalChars = new char[] {'(',')','.','|','*','^','[',']','?','\\'};

        return !String.valueOf(illegalChars).contains(Character.toString(c));

    }



}
