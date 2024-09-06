import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key; int val;
        Node next; Node prev;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public void removeNode(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        public void addNodeToHead(Node node){
            node.next = head.next;
            node.prev = head;
            head.next = node;
            node.next.prev = node;
        }

    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.map = new HashMap<>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        // If there is no key in map, we don't have it in cache
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        // remove node from its position and add it to most recent i.e. head of LL
        removeNode(node);
        addNodeToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        // If value already exists, we need to update it
        if(map.containsKey(key)){
            Node node = map.get(key);
            // need to move it to most recent 
            node.val = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            if(capacity == map.size()) {
                //remove least recent 
                Node lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            //If capacity is not full, just add the node to head
            Node newNode = new Node(key,value);
            addNodeToHead(newNode);
            map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */