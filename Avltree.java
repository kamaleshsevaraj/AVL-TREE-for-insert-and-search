import java.util.*;

class Node {
    int key, h;
    Node left, right;

    public Node(int data) {
        key = data;
        h = 0;
        left = right = null;
    }
}

class Avltree {
    Node root;

    Avltree() {
        root = null;
    }

    public void insert(int key) {
        root = insertrt(root, key);
    }

    Node insertrt(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
        {
            root.left = insertrt(root.left, key);
        }
        else if (key > root.key)
        {
            root.right = insertrt(root.right, key);
        } 
        root.h = 1 + Math.max(getHeg(root.left), getHeg(root.right));//height
        int b =getbalancefactor(root);//balance factor;
       if (b > 1 && key < root.left.key) {
          root = rightRotate(root);
       } 
       else if (b < -1 && key > root.right.key)
       {
         root = leftRotate(root);
       } 
       else if (b > 1 && key > root.left.key) 
       {
        root.left = leftRotate(root.left);
        root = rightRotate(root);
       } 
       else if (b < -1 && key < root.right.key)
       {
        root.right = rightRotate(root.right);
        root = leftRotate(root);
        }
        return root;
    }
    Node rightRotate(Node y) 
    {
    Node x = y.left;
    Node T2 = x.right;
    
    x.right = y;
    y.left = T2;
    
    y.h = 1 + Math.max(getHeg(y.left), getHeg(y.right));
    x.h = 1 + Math.max(getHeg(x.left), getHeg(x.right));
    
    return x;
    }

    Node leftRotate(Node x) 
    {
    Node y = x.right;
    Node T2 = y.left;
    
    y.left = x;
    x.right = T2;
    
    x.h = 1 + Math.max(getHeg(x.left), getHeg(x.right));
    y.h = 1 + Math.max(getHeg(y.left), getHeg(y.right));
    
    return y;
}

   public int getbalancefactor(Node g)
   {
       return getHeg(g.left) - getHeg(g.right);
   }
    public int getHeg(Node ref) {
        if (ref == null) {
            return -1;
        }
        return ref.h;
    }
    public boolean search(int data)
    {
        Node temp=root;
        while(temp!=null)
        {
            if(temp.key==data)
            {
                return true;
            }
            else if(data<temp.key)
            {
                temp=temp.left;
            }
            else if(data>temp.key)
            {
                temp=temp.right;
            }
        }
        return false;
    }
  

    public void inorder(Node node) {
        if (node != null) 
        {
            System.out.print(node.key + " | ");
            System.out.print(node.h+" |=>| ");
            inorder(node.left);
            inorder(node.right);
        }
    }
    
}
class Main {
    public static void main(String[] args) {
        Avltree t = new Avltree();
        t.insert(10);
        t.insert(9);
        t.insert(8);
        t.insert(30);
        t.insert(40);
        t.insert(60);
        t.inorder(t.root);
        System.out.println();
        System.out.println(t.search(40));
    }
}
