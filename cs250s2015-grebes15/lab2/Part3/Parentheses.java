import java.util.*;
import java.io.*;


public class Parentheses {
    private static final char L_PAREN    = '('; //this is the declaration for the left parantheses (
    private static final char R_PAREN    = ')'; //this is the declaration for the right parantheses )
    private static final char L_BRACE    = '{'; //this is the declaration for the left brace {
    private static final char R_BRACE    = '}'; //this is the declaration for the right brace }
    private static final char L_BRACKET  = '['; //this is the declaration for the left bracket )
    private static final char R_BRACKET  = ']'; //this is the declaration for the right bracket )

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>(); //declartion of a stack
        for (int i = 0; i < s.length(); i++) {

            if      (s.charAt(i) == L_PAREN)   stack.push(L_PAREN); //if a left parantheses is found, then push it to the top of the stack

            else if (s.charAt(i) == L_BRACE)   stack.push(L_BRACE); //if a left brace is found, then push it to the top of the stack

            else if (s.charAt(i) == L_BRACKET) stack.push(L_BRACKET); //if a left bracket is found, then push it to the top of the stack

            else if (s.charAt(i) == R_PAREN) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_PAREN) return false;
                //is the right parantheses and left parntheses cannot be matched, then return false
            } //if

            else if (s.charAt(i) == R_BRACE) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_BRACE) return false;
                //if a right brace cannot be matched with a left brace, then return false
            } //if

            else if (s.charAt(i) == R_BRACKET) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_BRACKET) return false;
                //if a right bracket and left bracket cannot be matched, then return false. 
            } //if

            // ignore all other characters

        } //for
        return stack.isEmpty(); 
    } //isBalanced


    public static void main(String[] args) {
        String s = StdIn.readAll();
        System.out.println(isBalanced(s)); //print line statement to call the method isBalanced with a string as a parameter
    } //main

} //Parantheses.java