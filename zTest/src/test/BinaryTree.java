package test;

public class BinaryTree {

    private TreeNode root=null;  
      
    public BinaryTree(){  
        root=new TreeNode(1,"rootNode(A)");  
    }  
	public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();  
        bt.inOrder(bt.root);  
	}
    
  //中序遍历  
  public void inOrder(TreeNode subTree){  
      if(subTree!=null){  
          inOrder(subTree.leftChild);  
          visted(subTree);  
          inOrder(subTree.rightChild);  
      }  
  }  
  
  public void visted(TreeNode subTree){  
      //subTree.isVisted=true;  
      System.out.println("key:"+subTree.key+"--name:"+subTree.data);;  
  }  

  private class  TreeNode{  
      private int key=0;  
      private String data=null;  
      //private boolean isVisted=false;  
      private TreeNode leftChild=null;  
      private TreeNode rightChild=null;  
        
      /** 
       * @param key  层序编码 
       * @param data 数据域 
       */  
      public TreeNode(int key,String data){  
          this.key=key;  
          this.data=data;  
          this.leftChild=null;  
          this.rightChild=null;  
      }  
  }  
  
}
