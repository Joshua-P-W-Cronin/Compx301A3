public class REcompiler {

    public static void main(String[] args) {

        //Do stuff
    }


    public int factor() {
        int r;
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
            } else {
                error()
            }

        }
    }


    public int expression() {

        int r;
        r = term();
        if (Vocab(p[j]) || p[j] == '(') {
            expression();
        }
        return r;
    }


    public int term() {
        f = state - 1;
        r = t1 = factor();
        if (p[j] == '*') {
            j++;
            setState(state, BR, t1, state + 1);
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
        return r;
    }


    public void setState(int s, char c, int s1, int s2) {
        ch[s] = c;
        next1[s] = s1;
        next2[s] = s2;
    }
}


