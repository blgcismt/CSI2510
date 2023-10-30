/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @author Lachlan Plant
 * @param <E>
 */
public class LinkedBinarySearchTree <E extends Comparable>implements Iterable <E> {

	private class Node <E> {
		E elem;

		Node <E>parent;
		Node <E>left;
		Node <E>right;
		public Node( E e, Node <E>p, Node <E>l, Node <E>r ) {
			elem   = e;
			parent = p;
			left   = l;
			right  = r;
		}


	}

	private Node <E>root;
	private int size;

	/**
	 *
	 */
	public LinkedBinarySearchTree() {
		root = null;
		size = 0;
	}


	/**
	 * Adds elem into BST
	 * @param elem
	 * @return
	 */
	public boolean add( E elem ) {
		if( root == null ) {
			root = new Node <> ( elem, null, null, null );
			size++;
			return true;
		}
		else {
			root = insert ( elem, root, null );
			return true;
		}
	} /* add */


	/**
	 * Recursive BST insertion
	 * @param elem
	 * @param curr
	 * @param from
	 * @return
	 */
	private Node <E>insert( E elem, Node <E>curr, Node <E>from ) {
		if( curr == null ) {
			curr = new Node <> ( elem, from, null, null );
			size++;
			return curr;
		}

		if( elem.compareTo ( curr.elem ) < 0 )
			curr.left = insert ( elem, curr.left, curr );

		if( elem.compareTo ( curr.elem ) > 0 )
			curr.right = insert ( elem, curr.right, curr );
		return curr;
	} /* insert */


	/*****************************************************************
	*
	* Recursive Printing Functions
	*
	*
	*****************************************************************/

	/**
	 * Caller method for preorder recursive printing
	 */
	public void printPreorderRecursive() {
		System.out.print ( "Recursive Preorder Printing: " );
		preorderRecursive ( root );
	} /* printPreorderRecursive */


	/**
	 * preorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void preorderRecursive( Node <E>curr ) {
		
		if(curr == null){
			return;
		}
		System.out.print(curr.elem + ", ");
		preorderRecursive(curr.left);
		preorderRecursive(curr.right);
	} /* preorderRecursive */


	/**
	 * Caller method for inorder recursive printing
	 */
	public void printInorderRecursive() {
		System.out.print ( "Recursive Inorder Printing: " );
		inorderRecursive ( root );
	} /* printInorderRecursive */


	/**
	 * inorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void inorderRecursive( Node <E>curr ) {
		if(curr == null){
			return;
		}
		inorderRecursive(curr.left);
		System.out.print(curr.elem + ", ");
		inorderRecursive(curr.right);
	} /* inorderRecursive */


	/**
	 * Caller method for postorder recursive printing
	 */
	public void printPostorderRecursive() {
		System.out.print ( "Recursive Postorder Printing: " );
		postorderRecursive ( root );
	} /* printPostorderRecursive */


	/**
	 * postorder tree traversal, prints(curr.elem + ", ")
	 * @param curr
	 */
	private void postorderRecursive( Node <E>curr ) {
		if(curr == null){
			return;
		}
		postorderRecursive(curr.left);
		postorderRecursive(curr.right);
		System.out.print(curr.elem + ", ");
	} /* postorderRecursive */


	/*****************************************************************
	*
	* Iterator Functions
	*
	*
	*****************************************************************/
	public Iterator iterator() {
		return new InorderIterator();
	} /* iterator */


	public Iterator inorderIterator() {
		return new InorderIterator();
	} /* inorderIterator */


	public Iterator preorderIterator() {
		return new PreorderIterator();
	} /* preorderIterator */


	/*****************************************************************
	*
	* Iterators
	*
	*
	*****************************************************************/




	/**
	 * Tree Iterator using preorder traversal for ordering
	 */
	private class PreorderIterator implements Iterator<E> {
		Stack<Node<E>> stack = new Stack<>();
		Node<E> next;
	
		public PreorderIterator() {
			next = root;
			if (next != null) {
				stack.push(next);
			}
		}
	
		public boolean hasNext() {
			return next != null;
		}
	
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
	
			Node<E> currentNode = next;
	
			// If there's a left child, it becomes the next node.
			// Otherwise, we pop from the stack.
			if (currentNode.left != null) {
				next = currentNode.left;
				stack.push(currentNode.right);
			} else if (!stack.isEmpty()) {
				next = stack.pop();
			} else {
				next = null;
			}
	
			return currentNode.elem;
		}/* next */


		public void remove() {
			
		} /* remove */


	}

	/**
	 * Tree Iterator using inorder traversal for ordering
	 */
	private class InorderIterator implements Iterator<E> {
		Stack<Node<E>> stack = new Stack<>();
		Node<E> next;
	
		public InorderIterator() {
			next = root;
			pushLeft(next);
		}
	
		private void pushLeft(Node<E> node) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}
	
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
	
			next = stack.pop();
			Node<E> currentNode = next;
	
			if (currentNode.right != null) {
				pushLeft(currentNode.right);
			}
	
			// Return the current node's data but set next to null to indicate it's exhausted.
			next = null;
			return currentNode.elem;
		}
	

		public void remove() {
			// not implemented
		} /* remove */


	}
}
