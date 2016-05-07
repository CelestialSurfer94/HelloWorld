/*
 Kalvin Suting
 2/28/2016
 Assignment 7
 Grace Chen
 This program asks a series of questions to determine the users object. If object isn't currently
 stored in the question tree, asks user for a new question that distinguishes its object from ones
 already in the tree and then updates the set of questions within the tree. 
 User has the option of reading in from an old tree or creating a brand new one. Once game is over, 
 writes the tree in pre-ordered form to an output file.  
 */
 
import java.util.*;
import java.io.*;

public class QuestionTree{
   private Scanner console;
   private QuestionNode overallRoot;
   
   //post:  constructs a QuestionTree object.
   public QuestionTree(){
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   //pre:  input file must be listed in pre-ordered form.
   //      each line separated by either Q: or A: accordingly. 
   //post: reads the given input file, and updates tree to match tree from given
   //	   input file. 
   public void read(Scanner input){
      overallRoot = readHelper(input, overallRoot);    
   }
   
   //post:	performs all necessary actions to read the given input file and update
   //		the current question tree to the one listed. Old tree will no longer
   //		exist unless otherwise specified in QuestionMain.
   private QuestionNode readHelper(Scanner input, QuestionNode root){    
      if(input.hasNextLine()){                                           
         String type = input.nextLine();
         String data = input.nextLine();
         root = new QuestionNode(data);
         
         if(type.equals("Q:")){
            root.left = readHelper(input, root);
            root.right = readHelper(input, root);
           
         } 
      }
         return root;
   }
   
   //post:	writes current tree to the output file in standard form. Standard form is as follows:
   //		A single line of either Q: or A: corresponding to a question or an answer followed by 
   //		a line containing the question or answer. Written in pre-ordered form of a tree 
   //		traversal. 
   public void write(PrintStream output){
      writeHelper(overallRoot, output);   
   }
   
   //pre:	must pass in valid root. 
   //post:	performs all necessary actions to write the current tree to the given output. Printed
   //		in standard form which is outlined above in the write method.
   private void writeHelper(QuestionNode root, PrintStream output){ 
      if(root.isLeaf()){
         output.println("A:");
         output.println(root.data);
      }else{
         output.println("Q:");
         output.println(root.data);
         writeHelper(root.left, output);
         writeHelper(root.right, output);      
      }
   }
   
   //post:	asks the user a series of questions following the algorithm of the question tree.
   //		User must answer either 'y' or 'n' (case insensitive) to each question. 
   //		If tree does not contain the users object, user must input a new yes or no
   //		question to be added to the tree. If the answer is contained in the tree no new
   //		question will be created and instead maintains trees current status. 
   public void askQuestions(){
      overallRoot = helperAskQuestions(overallRoot);
      
   }
   
   //pre:	must pass in valid root. 
   //post:	performs all necessary actions to ask user questions based on tree and read input from
   //		user till correct answer is found. If tree does not contain users object, asks user
   //		for a new question that distinguishes it from other options in the tree. If new
   //		question is created, updates tree to add new question. 	
   private QuestionNode helperAskQuestions(QuestionNode root){ //root
      QuestionNode result = root;
      if(root.isLeaf()){	//if current node is an answer (leaf)
         if(!yesTo("Would your object happen to be " + root.data + "?")){     //if the answer is no 
            System.out.print("What is the name of your object? ");
            String newAnswer = console.nextLine();
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguises between your object");
            System.out.print("and mine--> ");
            String newQuestion = console.nextLine();	// the question of the new question node
            if(yesTo("And what is the answer for your object? ")){	 //answer = yes to new question.
               result = new QuestionNode(newQuestion, new QuestionNode(newAnswer), root);
            }else{
               result = new QuestionNode(newQuestion, root, new QuestionNode(newAnswer));
            }
            root = result;
         }else{		// guessed correctly!
            System.out.println("Great, I got it right!"); 
         }
      }else{
         if(yesTo(root.data)){	//if user answers yes to question in tree. 
            root.left = helperAskQuestions(root.left);
         }else{	//user answered no to question in tree. 
            root.right = helperAskQuestions(root.right);
         }
      }
      return result;
   }
   
   // post: asks the user a question, forcing an answer of "y " or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}