public class Deque{

    DNode head;

    public Deque(int state){

        head = new DNode(state);

    }


    public int get(){
        int state = head.getState);
        head = head.next();
        return state;
    }


    public void push(int state){
        DNode cur = head;
        while(cur.next() != null){
            cur = cur.next();
        }
        cur.setNext(new DNode(state));

    }

    public void pop(int state){
        Dnode next = new Dnode(state);
        next.setNext(head);
        head = next;
    }

    public void print(){

        String p = "";
        Dnode cur= head;
        while(cur.next != null){
            p += Integer.toString(cur.getState()) + ",";
            cur = cur.next();
        }
        p += Integer.toString(cur.getState());
        System.out.println(p);


    }




}

class DNode{

    int state;
    DNode next;

    public DNode(int state){
        this.state = state;
        next = null;
    }


    public int getState(){
        return state;
    }

    public void setNext(Dnode d){
        next = d;
    }


    public DNode next(){
        return next;
    }






}
