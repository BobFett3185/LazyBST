public class LazyBinarySearchTree {

    // creating our treeNode class
    private class treeNode{
        int key;
        boolean deleted; // flag for lazy deletion
        treeNode leftChild, rightChild;

        //constructor for treeNode
        public treeNode(int key) {
            this.key = key;
            this.deleted = false;
            this.leftChild = null;
            this.rightChild = null;
        }

    }

	//attribute root
    treeNode root = null;

	// Insert function
	public boolean insert(int key) {
        if(key> 99 || key < 1){
            throw new IllegalArgumentException("Key out of bounds"); // out of bounds
        }

        // if tree is empty we can just insert the node
        if(root == null){ 
            root = new treeNode(key);
            return true;
        }
        
        treeNode current = root; // use a temp node to traverse
        while(current != null){
            if(key == current.key){ // if we find the node we are trying to insert
                if(current.deleted){ // if it's been deleted then just undelete
                    current.deleted = false;
                    return true;
                } 
                else { // otherwise it's already there
                    return false;
                }
            }

            else if(key < current.key){ // if less we go left
                if(current.leftChild == null){ // insert at the first null spot we find
                    current.leftChild = new treeNode(key);
                    return true;
                }
                current = current.leftChild;
            } 
            else { // otherwise we have to go right
                if(current.rightChild == null){ // similarly we insert when we find null
                    current.rightChild = new treeNode(key);
                    return true;
                }
                current = current.rightChild;
            }
        }

		return false;
	}

	// lazy deletion
    // basically a search and then delete 
	public boolean delete(int key) {
        if(key > 99 || key < 1) {
            throw new IllegalArgumentException("Key out of bounds"); // out of bounds
        }

        if(root == null)
            return false; 

        treeNode current = root; // use a temp node to traverse
        while(current != null){
            if(key == current.key){
                if(current.deleted)
                    return false; 
                else{
                    current.deleted = true; 
                    return true; 
                }   
            }
            else if(key < current.key) // if less we go left
                current = current.leftChild;
            else // otherwise we have to go right
                current = current.rightChild;
            
        }
		return false;
	}

	// find min and max
	public int findMin() {
        // The spec says return 1 if no non-deleted element exists [cite: 34]
        return findMinRecursive(root);
    }

    private int findMinRecursive(treeNode current) { // this has to be an inorder traverasal bc we wanna check left, then root then right 
        if (current == null) 
            return -1;

        //search the left subtree
        int leftResult = findMinRecursive(current.leftChild);
        if (leftResult != -1) 
            return leftResult;

        // if it didn't return then we check the current node, if it's not deleted we return it
        if (!current.deleted) 
            return current.key;

        // then search the right subtree
        return findMinRecursive(current.rightChild);

    }




    public int findMax() {
        // The spec says return 1 if no non-deleted element exists [cite: 34]
        return findMaxRecursive(root);
    }

    private int findMaxRecursive(treeNode current) { // this has to be an inorder traverasal bc we wanna check left, then root then right 
        if (current == null) 
            return -1;

        //search the right subtree
        int rightResult = findMaxRecursive(current.rightChild);
        if (rightResult != -1) 
            return rightResult;

        // if it didn't return then we check the current node, if it's not deleted we return it
        if (!current.deleted) 
            return current.key;

        // then search the left subtree
        return findMaxRecursive(current.leftChild);
        
    }

    //contains 
    public boolean contains(int key) {

        if(key > 99 || key < 1) {
            throw new IllegalArgumentException("Key out of bounds"); // out of bounds
        }
        

        treeNode current = root; // use a temp node to traverse
        while(current != null){
            if(key == current.key){
                if (current.deleted)
                    return false; // if we find the node but it's deleted then we return false
                else 
                    return true;
            }
            else if(key < current.key) // if less we go left
                current = current.leftChild;
            else // otherwise we have to go right
                current = current.rightChild;
        }
        return false;
    }

	// printing
	public String toString() { // preorder so root left right 
		return toString(root); // call helper
	}

    // need this so we can do it recursively and build the string as we go
    private String toString(treeNode current){ // helper function for toString that takes a node as an argument
        if (current == null)
            return ""; // if we hit null we return an empty string so it doesn't mess up our final string

        String s = "";
        
        // print the root, left and right recursively
        if(current.deleted)
            s+= '*';
        s += Integer.toString(current.key); 

        // call recursively on left and right children
        if(current.leftChild != null)
            s += toString(current.leftChild);
        if(current.rightChild != null)
            s += toString(current.rightChild);

        return s;

    }

    //height of tree
    public int height() {
        return height(root); // call helper
    }

    private int height(treeNode node){ // helper function for height that takes a node as an argument
        if(node == null)
            return -1; // if we hit null we return -1 so that the height of a leaf node is 0
        // recursively find the height of the left and right children and return the max + 1 for the current node
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    public int size() {
        return size(root); // call helper
    }

    private int size(treeNode node){ // helper function for size that takes a node as an argument
        if(node == null)
            return 0;
        // recursively count the left and right children and add 1 for the current node 
        return 1 + size(node.leftChild) + size(node.rightChild);
    }



}
