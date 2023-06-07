import java.util.Queue;

//was given structure of MyArrayQueue{}
//had to implement enqueue and dequeue method independantly
//accessed 29-04-2023
//https://learn-eu-central-1-prod-fleet01-xythos.content.blackboardcdn.com/5fd150846bae0/18917467?X-Blackboard-S3-Bucket=learn-eu-central-1-prod-fleet01-xythos&X-Blackboard-Expiration=1682737200000&X-Blackboard-Signature=%2FUQkt2Smzeh13lT53akMU3vDD1JH6VxPZzfg9Lwqbd4%3D&X-Blackboard-Client-Id=179260&X-Blackboard-S3-Region=eu-central-1&response-cache-control=private%2C%20max-age%3D21600&response-content-disposition=inline%3B%20filename%2A%3DUTF-8%27%27MyArrayQueue.java&response-content-type=text%2Fx-java-source&X-Amz-Security Token=IQoJb3JpZ2luX2VjEF8aDGV1LWNlbnRyYWwtMSJGMEQCIG5dkyotoWwOuSPIOeU%2FQZ7h06kIUE%2BTjOtH2WU%2BHUs8AiBwzqqEA38olNTDatofBCIhrz72I447CLj39R0PEM10YCq9BQhoEAMaDDYzNTU2NzkyNDE4MyIMiIP1a4m2TLdb6JBJKpoFVetWzkWL2HXXIvF3eMu1CfQ%2B3p10tvNchfxu8Y0gqSUuu9OBTEjC1rQtfmNwgXv9SODdj8FBhYHlbhahTD79JI4YOaKy%2Bo8tPhKVL7cy%2BGZB1I1Wvk0%2BOzB5LH2KtRszwQf1WWXC6qHQ6pg23Gv3lSPEbuGmnFJZ96FEQSm9s33PFmQxTRXI%2BKn%2BdasoklQkzw5thfn5tkw%2BREE0RNgN0qyZ%2BPL9NNHuU1S6ofpGOpY3X75JxxdQSzS%2FEk5yIYCDw9QZNLUWb7%2Bzpk%2Bo4QqpvJB4TXZkIxYOmzQc6I4ekn28TqVLvVTEy%2BZg18mTYW8tQIBxj8M%2F9WhAqtkKa%2FxIjXC6OM8ZxAV1LjraL8LSjOf9btRklHqr0tI2QutddbUmt5abL%2B%2FkV%2BAtpakWw%2BtPPbcYifGnelKhJsBt%2BOVvM5qx5NF973qd3Hh68eqWFaNEhoziUwEtKM4OMIGbic2mBEf6LQNiyravuvm%2F9Gw0HAGRt8k8J%2BozEuNqNkUxnfC04fhgpGWwR2hAshYbNlDopm0qNweys4750A9xMPr%2BJJTpC0xiBcOvMvQJZ%2FXJmmW1VtGGCSKXiitxw7J1jTkQ7kdks8Jljst7rEq2p4G6qPxCpzDJAkVISjYUNiuTprPB9MTNJ3GulqZviu8uVrDb7RPTD84vRvWFNxAAcwwo1uNLIAcPMEYPRETYZIik%2FTymg%2FKUypfuggi9Hx4enkMj0gTwmxl3%2FK%2FaM6%2BqpVT7SKA%2BtwoSo%2B2T5tYt%2FDMB1dxx2k3eOZm97odumu3l9KS06eH%2B5uQKh6sePE97GRyYjcdhC7jgZgGe77CbiELXmNkuyvP8vwkC4MJIeZKXCYcVfxsfASyWfY7vCOHDoAJbUezeGe3SNs0juKnhMMGcsaIGOrIBefGH%2B8m3YBJwq5iuoRLFgGwnyI1vydLEmbS%2FIrXp1PYlypKdudmrWsGxveC3PDcJ9%2FfTR2FJOdXuSmi8zNW55CbLClYYhPE8iPcAMXOZW7RrkFeyqevg%2Bj65XAloAP2Am97w2duQr7PippyNOXMPjll1UtR8gbY6OLvsX458Y19ObbRDmQ0bmSq6GaGrNh73YEC0x%2Bb%2BP3OaI0OYMHZG4%2FpBBlmIRmXF9g93Cr1wmlp6eA%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230428T210000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=21600&X-Amz-Credential=ASIAZH6WM4PL2GI6SLNO%2F20230428%2Feu-central-1%2Fs3%2Faws4_request&X-Amz-Signature=fb884987442ce137d0de011f98a518c69b62e54a0adcb8abf42288eca27740a6
public class MyArrayQueue {
    // data members
    int front;          // one counterclockwise from first element
    int rear;           // position of rear element of queue
    Object[] queue;     // element array

    // constructors
    /** create a queue with the given initial capacity */
    public MyArrayQueue(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        }
        queue = new Object[initialCapacity + 1];
    }

    /** create a queue with initial capacity 5 */
    public MyArrayQueue() {
        this(5);
    }

    // methods
    /** @return true iff queue is empty */
    public boolean isEmpty() {
        return front == rear;
    }

    /** @return front element of queue
     * @return null if queue is empty */
    public Object getFrontElement() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[(front + 1) % queue.length];
        }
    }

    /** @return rear element of queue
     * @return null if the queue is empty */
    public Object getRearElement() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[rear];
        }
    }

    /** insert theElement at the rear of the queue */
    public void enqueue(Object theElement) {
        if ((rear + 1) % queue.length == front) {
            // queue is full, resize the array
            Object[] newQueue = new Object[2 * queue.length];
            int j = 1;
            for (int i = (front + 1) % queue.length; i != rear; i = (i + 1) % queue.length) {
                newQueue[j++] = queue[i];
            }
            newQueue[j] = queue[rear];
            front = 0;
            rear = j;
            queue = newQueue;
        }
        // put the new element at the rear of the queue
        rear = (rear + 1) % queue.length;
        queue[rear] = theElement;
    }

    /** remove an element from the front of the queue
     * @return removed element */
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        // remove and return the element at the front
        front = (front + 1) % queue.length;
        Object removedElement = queue[front];
        queue[front] = null;
        return removedElement;
    }

    /** @return the number of elements in the queue */
    public int size() {
        return ((rear - front + queue.length) % queue.length);
    }

   /** test program */
   public static void main(String[] args) {
      MyArrayQueue q = new MyArrayQueue(3);
      // add a few elements
      q.enqueue("element1");
      q.enqueue("element2");
      q.enqueue("element3");
      q.enqueue("element4");

      // remove and add to test wraparound array doubling
      q.dequeue();
      q.dequeue();
      q.enqueue("element5");
      q.enqueue("element6");
      q.enqueue("element7");
      q.enqueue("element8");
      q.enqueue("element9");
      q.enqueue("element10");
      q.enqueue("element11");
      q.enqueue("element12");

      // delete all elements
      while (!q.isEmpty()) {
         System.out.println("Rear element   : " + q.getRearElement());
         System.out.println("Front element  : " + q.getFrontElement());
         System.out.println("Removed element: " + q.dequeue() + "\n");
      }
      if (q.isEmpty())
         System.out.println("empty queue");
   }
}
