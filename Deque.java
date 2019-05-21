// Joshua Cronin 1212942
// Luke Weston 1336265


//Deque is a Deque of DNodes, DNodes can be removed from and added to the front of the queue and DNodes can be 
public class Deque{

    DNode head;

	//Constructs new Deque
    public Deque(int state){

        head = new DNode(state);

    }

	//Returns the state of the head and sets the head to the next item in deque
    public int get(){
        int state = head.getState();
        head = head.next();
        return state;
    }

	//Adds an new DNode to the end of the deque
    public void enque(int state){
        if (head == null){
            head = new DNode(state);
            return;
        }
        DNode cur = head;
        while(cur.next() != null){
            cur = cur.next();
        }
        cur.setNext(new DNode(state));

    }
	
	//Adds state dataa to a new DNode at the front of the queue
    public void push(int state){
        DNode next = new DNode(state);
        next.setNext(head);
        head = next;
    }
	
	//Prints all the states in the order that they are in in the Deque
    public void print(){

        String p = "";
        DNode cur = head;
        while(cur.next != null){
            p += Integer.toString(cur.getState()) + ", ";
            cur = cur.next();
        }
        p += Integer.toString(cur.getState());
        System.out.println(p);


    }




}

//Class DNode, is a node in the Deque, keeps track of the state that the node is for and the next DNode in the Queue
class DNode{

    int state;
    DNode next = null;

    public DNode(int state){
        this.state = state;
        next = null;
    }


    public int getState(){
        return state;
    }

    public void setNext(DNode d){
        next = d;
    }


    public DNode next(){
        return next;
    }






}
