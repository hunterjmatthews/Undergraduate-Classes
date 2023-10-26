/*
	@author Hunter Matthews - HJM210004
	@date 09/29/23
*/

package hjm210004;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/** Class to store a node of expression tree
    For each internal node, element contains a binary operator
    List of operators: +|*|-|/|%|^
    Other tokens: (|)
    Each leaf node contains an operand (long integer)
*/

public class Expression {
    public enum TokenType {  // NIL is a special token that can be used to mark bottom of stack
	PLUS, TIMES, MINUS, DIV, MOD, POWER, OPEN, CLOSE, NIL, NUMBER
    }
    
    public static class Token {
	TokenType token;
	int priority; // for precedence of operator
	Long number;  // used to store number of token = NUMBER
	String string;

	Token(TokenType op, int pri, String tok) {
	    token = op;
	    priority = pri;
	    number = null;
	    string = tok;
	}

	// Constructor for number.  To be called when other options have been exhausted.
	Token(String tok) {
	    token = TokenType.NUMBER;
	    number = Long.parseLong(tok);
	    string = tok;
	}
	
	boolean isOperand() { return token == TokenType.NUMBER; }

	public long getValue() {
	    return isOperand() ? number : 0;
	}

	public String toString() { return string; }
    }

    Token element;
    Expression left, right;

    // Create token corresponding to a string
    // tok is "+" | "*" | "-" | "/" | "%" | "^" | "(" | ")"| NUMBER
    // NUMBER is either "0" or "[-]?[1-9][0-9]*
    static Token getToken(String tok) {  // To do
	Token result;
	switch(tok) {
	// Level 99 Precedence (Highest Precedence)
	case "(":
		result = new Token(TokenType.OPEN, 99, tok);
		break;
	case ")":
		result = new Token(TokenType.CLOSE, 99, tok);
		break;
	// Level 3 Precedence ("^")
	case "^":
		result = new Token(TokenType.POWER, 3, tok);
		break;
	// Level 2 Precedence ("*","/", and "%")
	case "*":
		result = new Token(TokenType.TIMES, 2, tok);
		break;
	case "/":
		result = new Token(TokenType.DIV, 2, tok);
		break;
	case "%":
		result = new Token(TokenType.MOD, 2, tok);
		break;
	// Level 1 Precedence ("+" and "-")
	case "+":
		result = new Token(TokenType.PLUS, 1, tok);
		break;
	case "-":
		result = new Token(TokenType.MINUS, 1, tok);
		break;
	default:
	    result = new Token(tok);
	    break;
	}
	return result;
    }
    
    private Expression() {
	element = null;
    }
    
    private Expression(Token oper, Expression left, Expression right) {
	this.element = oper;
	this.left = left;
	this.right = right;
    }

    private Expression(Token num) {
	this.element = num;
	this.left = null;
	this.right = null;
    }

   	/*
   	 * Method to convert an infix expression given as a list of tokens into an expression tree:
     * Given an infix expression as a list of tokens, return its corresponding expression tree.
     * Signature: public static Expression infixToExpression(List<Token> exp) { ... }
     *
     * @param exp List of tokens that represent the infix expression that we want converted.
     *
     * @return The Expression object which represents the converted expression.
   	 */
    public static Expression infixToExpression(List<Token> exp) {
		ArrayDeque<Expression> outputStack = new ArrayDeque<>();
		ArrayDeque<Token> operatorStack = new ArrayDeque<>();
		operatorStack.push(new Token(TokenType.NIL,-1,""));
		Iterator<Token> tempIterator = exp.listIterator();
		Token tempToken;
		Expression operationOne, operationTwo;
		while (tempIterator.hasNext()) {
			tempToken = tempIterator.next();
			if (tempToken.isOperand()) {
				outputStack.push(new Expression(tempToken));
			} else {
				if (tempToken.token == TokenType.CLOSE) {
					while (operatorStack.peek().token != TokenType.NIL && operatorStack.peek().token != TokenType.OPEN) {
						// Has to be like this otherwise will produce wrong result (i.e. (5*2)+3-1 produces -12 of operationOne comes before operationTwo)
						operationTwo = outputStack.pop();
						operationOne = outputStack.pop();
						outputStack.push(new Expression(operatorStack.pop(), operationOne, operationTwo));
					}
					// Trash the parentheses
					operatorStack.pop();
				} else {
					while (operatorStack.peek().token != TokenType.NIL && operatorStack.peek().priority >= tempToken.priority && operatorStack.peek().token != TokenType.OPEN) {
						// Has to be like this otherwise will produce wrong result (i.e. (5*2)+3-1 produces -12 of operationOne comes before operationTwo)
						operationTwo = outputStack.pop();
						operationOne = outputStack.pop();
						outputStack.push(new Expression(operatorStack.pop(), operationOne, operationTwo));
					}
					operatorStack.push(tempToken);
				}
			}
		}
		while (operatorStack.peek().token != TokenType.NIL) {
			// Has to be like this otherwise will produce wrong result (i.e. (5*2)+3-1 produces -12 of operationOne comes before operationTwo)
			operationTwo = outputStack.pop();
			operationOne = outputStack.pop();
			outputStack.push(new Expression(operatorStack.pop(), operationOne, operationTwo));
		}
		// Our final result is now at the top of the output stack.
		return outputStack.pop();
    }

    /*
     * Method to convert an infix expression into a postfix expression:
     * Given an infix expression as a list of tokens, return its equivalent postfix expression.
     * Signature: public static List<Token> infixToPostfix(List<Token> exp) { ... }
     *
     * @param exp List of tokens that represent the infix expression that we want converted.
     *
	 * @return The converted list of tokens representing the expression in postfix notation.
     */
    public static List<Token> infixToPostfix(List<Token> exp) {
		ArrayDeque<Token> stack = new ArrayDeque<>();
		stack.push(new Token(TokenType.NIL, -1, ""));
		List<Token> resultExpression = new LinkedList<>();
		Iterator<Token> tempIterator = exp.listIterator();
		Token tempToken;
		while (tempIterator.hasNext()) {
			tempToken = tempIterator.next();
			if (tempToken.isOperand()) {
				resultExpression.add(tempToken);
			} else {
				if (tempToken.token == TokenType.CLOSE) {
					while (stack.peek().token != TokenType.NIL && stack.peek().token != TokenType.OPEN) { resultExpression.add(stack.pop()); }
					// Now, we can trash the parentheses.
					stack.pop();
				} else {
					while (stack.peek().token != TokenType.NIL && stack.peek().priority >= tempToken.priority && stack.peek().token!=TokenType.OPEN) { resultExpression.add(stack.pop()); }
					stack.push(tempToken);
				}
			}
		}
		while (stack.peek().token != TokenType.NIL) { resultExpression.add(stack.pop()); }
		return resultExpression;
    }

    /*
     * Method to evaluate a postfix expression:
     * Given a postfix expression, evaluate it and return its value.
     * Signature: public static long evaluatePostfix(List<Token> exp) { ... }
     *
     * @param exp List of tokens that represents the postfix expression that we want to be evaluated.
     *
     * @return Evaluated postfix expression expressed as a long integer.
     */
    public static long evaluatePostfix(List<Token> exp) {
		ArrayDeque<Token> stack = new ArrayDeque<>();
		ListIterator<Token> tempListIterator = exp.listIterator();
		Token tempToken;
		long operationOne, operationTwo;
		while (tempListIterator.hasNext()) {
			tempToken = tempListIterator.next();
			if (tempToken.isOperand()) {
				stack.push(tempToken);
			} else {
				// Has to be like this otherwise will produce wrong result (i.e. (5*2)+3-1 produces -12 of operationOne comes before operationTwo)
				operationTwo = stack.pop().getValue();
				operationOne = stack.pop().getValue();
				switch (tempToken.token) {
					// Level 3 Precedence ("^")
					case POWER:
						stack.push(getToken(((long)Math.pow(operationOne,operationTwo))+""));
						break;
					// Level 2 Precedence ("*","/", and "%")
					case MOD:
						stack.push(getToken((operationOne % operationTwo)+""));
						break;
					case DIV:
						stack.push(getToken((operationOne / operationTwo)+""));
						break;
					case TIMES:
						stack.push(getToken((operationOne * operationTwo)+""));
						break;
					// Level 1 Precedence ("+" and "-")
					case MINUS:
						stack.push(getToken((operationOne - operationTwo)+""));
						break;
					case PLUS:
						stack.push(getToken((operationOne + operationTwo)+""));
						break;
				}
			}
		}
		return stack.pop().getValue();
    }

    /*
     * Method to evaluate an expression tree:
     * Given an expression tree, evaluate it and return its value.
     * Signature: public static long evaluateExpression(Expression tree) { ... }
     *
     * @param tree The beginning or "root" of the expression tree that we want evaluated.
     *
 	 * @return Once the expression tree is evaluated as a long integer, we return the result.
 	 *
 	 * @throws IllegalArgumentException if an operator encountered is unsupported.
     */
    public static long evaluateExpression(Expression tree) {
		if (tree.element.isOperand()) {
			return tree.element.getValue();
		} else {
			long operationOne = evaluateExpression(tree.left);
			long operationTwo = evaluateExpression(tree.right);
			switch (tree.element.token) {
				// Level 3 Precedence ("^")
				case POWER:
					return (long) Math.pow(operationOne, operationTwo);
				// Level 2 Precedence ("*","/", and "%")
				case MOD:
					return operationOne % operationTwo;
				case DIV:
					return operationOne / operationTwo;
				case TIMES:
					return operationOne * operationTwo;
				// Level 1 Precedence ("+" and "-")
				case MINUS:
					return operationOne - operationTwo;
				case PLUS:
					return operationOne + operationTwo;
				// Default Precedence (Note: If this statement is executed, an error has occurred.)
				default:
					throw new IllegalArgumentException("Encountered Unsupported Operator: " + tree.element.token);
			}
		}
    }

    // sample main program for testing
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	
	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} else {
	    in = new Scanner(System.in);
	}

	int count = 0;
	while(in.hasNext()) {
	    String s = in.nextLine();
	    List<Token> infix = new LinkedList<>();
	    Scanner sscan = new Scanner(s);
	    int len = 0;
	    while(sscan.hasNext()) {
		infix.add(getToken(sscan.next()));
		len++;
	    }
	    if(len > 0) {
		count++;
		System.out.println("Expression number: " + count);
		System.out.println("Infix expression: " + infix);
		Expression exp = infixToExpression(infix);
		List<Token> post = infixToPostfix(infix);
		System.out.println("Postfix expression: " + post);
		long pval = evaluatePostfix(post);
		long eval = evaluateExpression(exp);
		System.out.println("Postfix eval: " + pval + " Exp eval: " + eval + "\n");
	    }
	}
    }
}
