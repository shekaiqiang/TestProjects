package test;

import java.util.LinkedList;
/**
 * 用java代码实现递归的方法将二叉树镜面对称并以先序遍历输出
 */
public class MirrorBinaryTreeRecursion {
	private BinaryTreeNode<Integer> root;

	public MirrorBinaryTreeRecursion() {
		root = new BinaryTreeNode<Integer>();
	}

	public MirrorBinaryTreeRecursion(int[] values) {
		System.out.print("构造二叉树:");
		for (int i : values) {
			System.out.print(i);
		}
		System.out.println();
		boolean isLeft = true;
		int length = values.length;
		if (length == 0)
			return;
		LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		root = new BinaryTreeNode<Integer>(values[0]);
		queue.addLast(root);
		BinaryTreeNode<Integer> parent = null;
		BinaryTreeNode<Integer> current = null;
		for (int i = 1; i < length; i++) {
			current = new BinaryTreeNode<Integer>(values[i]);
			queue.addLast(current);
			if (isLeft)
				parent = queue.getFirst();
			else
				parent = queue.removeFirst();
			if (isLeft) {
				parent.setLeftChild(current);
				isLeft = false;
			} else {
				parent.setRightChild(current);
				isLeft = true;
			}
		}
	}

	public void mirrorRecursion() {
		System.out.print("二叉树递归镜像:");
		mirrorRecursionExecute(root);
		System.out.println();
	}

	private static void mirrorRecursionExecute(BinaryTreeNode<Integer> node) {
		//No.1
		//开始写代码，递归将二叉树镜面对称并先序遍历
		if ( node != null) {
			mirrorRecursionExecute(node.getLeftChild());
			mirrorRecursionExecute(node.getRightChild());
			System.out.println(node.getValue());
		}
		
		//end_code
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void swapChild(BinaryTreeNode node) {
		BinaryTreeNode left = node.getLeftChild();
		node.setLeftChild(node.getRightChild());
		node.setRightChild(left);
	}

	public static void main(String[] args) {
		MirrorBinaryTreeRecursion mirrorBinaryTreeRecursion = new MirrorBinaryTreeRecursion(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		mirrorBinaryTreeRecursion.mirrorRecursion();
	}

}

class BinaryTreeNode<value> {
	private value value;
	private BinaryTreeNode<value> leftChild;
	private BinaryTreeNode<value> rightChild;

	public BinaryTreeNode() {
	};

	public BinaryTreeNode(value value) {
		this.value = value;
		leftChild = null;
		rightChild = null;
	}

	public void setLeftChild(BinaryTreeNode<value> leftNode) {
		this.leftChild = leftNode;
	}

	public void setRightChild(BinaryTreeNode<value> rightNode) {
		this.rightChild = rightNode;
	}

	public value getValue() {
		return value;
	}

	public void setValue(value value) {
		this.value = value;
	}

	public BinaryTreeNode<value> getLeftChild() {
		return leftChild;
	}

	public BinaryTreeNode<value> getRightChild() {
		return rightChild;
	}
}