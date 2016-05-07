/*Kalvin Suting
 *2/28/2016
 *Assignment 7
 *Grace Chen
 *This class creates a single QuestionNode to be used to form an entire QuestionTree.
 *Contains two separate constructors. One specifically for a single node, and an other 
 *for a "branch" of the tree.  
 * 
 */

public class QuestionNode{
   public QuestionNode left;   //the answer is no 
   public QuestionNode right;  //the answer is yes
   public String data;   //the question itself.
   
   //post: constructs a leaf node with the given answer. This is usually an answer
   public QuestionNode(String data){
      this(data, null, null);     
   }
   
   //post: constructs a "branch". 
   public QuestionNode(String data, QuestionNode left, QuestionNode right){
      this.data = data;
      this.left = left;
      this.right = right; 
   }
   
   //post: returns true if QuestionNode is a leaf, false otherwise. 
   public boolean isLeaf(){
	   return this.left == null && this.right == null; 
   }
}