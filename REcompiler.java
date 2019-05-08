public class REcompiler {

    //Regular expression phrase
    static char[] p;
    //counter of the index we are up to on the regular expression
    static int j;
    //Lists of states
    static int[] next1, next2;
    //list of chars
    static char[] ch;
    //Current State
    static int state;
    //Branching state
    static final char BR = '\0';

    public static void main(String[] args) {
        p = args[0].toCharArray();
        j = 0;
        next1 = new int[100];
        next2 = new int[100];
        ch = new char[100];
        state = 0;
        parse();
        printMachine();
    }

    public static void parse()
    {
      int initial;

      initial = expression();
      //if (p[j])
      // error(); // In C, zero is false, not zero is true
      setState(state, ' ', 0, 0);
      System.out.println("starting state = " + initial);
    }

    public static void printMachine(){
      System.out.println("s  ch 1 2");
      System.out.println("--+--+-+-+");
      for(int i = 0; i<100; i++){
        System.out.println(String.format("%1s | %2s %3s %4s", i, ch[i], next1[i], next2[i]));
      }
    }

    public static int factor() {
        int r = 0;
        if (isVocab(p[j])) {
            setState(state, p[j], state + 1, state + 1);
            j++;
            r = state;
            state++;
            return r;
        }
        if (p[j] == '(') {
            j++;
            r = expression();
            if (p[j] == ')') {
                j++;
            }
            else {
                error();
            }
        }
        return r;
    }


    public static int expression() {

        int r;
        r = term();
        if (j < p.length) {
          if (isVocab(p[j]) || p[j] == '(') {
            expression();
          }
        }
        return r;
    }


    public static int term() {
        int f, r, t1, t2;
        f = state - 1;
        r = t1 = factor();
        if(j<p.length){
          if (p[j] == '*') {
              j++;
              setState(state, BR, state + 1, t1);
              r = state;
              state++;
          }
          if (p[j] == '|') {
              if (next1[f] == next2[f]) {
                  next2[f] = state;
              }

              next1[f] = state;
              f = state - 1;
              j++;
              r = state;
              state++;
              t2 = term();
              setState(r, BR, t1, t2);
              if (next1[f] == next2[f]) {
                  next2[f] = state;

              }
              next1[f] = state;
          }
        }
        return r;
    }


    public static void setState(int s, char c, int s1, int s2) {
        ch[s] = c;
        next1[s] = s1;
        next2[s] = s2;
    }


    public static void error(){
        System.out.println("Regular expression invalid");
        System.exit(0);

    }

    public static boolean isVocab(char c){

        char[] illegalChars = new char[] {'(',')','.','|','*','^','[',']','?','\\'};

        return !String.valueOf(illegalChars).contains(Character.toString(c));

    }



}
