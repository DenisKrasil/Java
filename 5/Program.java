
public class Program {
public static void main(String[] args) {
    
    Node root = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);
    Node n8 = new Node(8);
    Node n9 = new Node(9);
    Node n11 = new Node(11);
    Node n20 = new Node(20);
    

    root.left = n2;
    root.right = n3;

    n2.left = n4;
    n3.left = n5;
    n3.right = n6;

    n4.left = n7;
    n4.middle = n8;
    n4.right = n9;
    n6.left = n11;
    n6.right = n20;
    order(root, "");
}

static void order(Node root, String space) {
    if (root == null)
    return;
    System.out.println(space + root.value);
    order(root.left, space + "  ");
    order(root.middle, space + "  ");
    order(root.right, space + "  ");
}

}

class Node {
int value;

public Node(int value) {
    this.value = value;
}

Node left;
Node middle;
Node right;
}