package dk.mk.tools;

import java.util.Stack;

//https://www.codewars.com/kata/52f78966747862fc9a0009ae/train/java
//https://en.wikipedia.org/wiki/Reverse_Polish_notation

public class Calc {

    public double evaluate(String expr) {
        if(expr.length() == 0)
            return 0;

        String[] splitted = expr.split(" ");
        Stack<String> mainStack = new Stack<>();

        for(String s : splitted){
            if(s.compareTo("*") == 0){
                double secondVal = Double.valueOf(mainStack.pop());
                double firstVal = Double.valueOf(mainStack.pop());
                double result = firstVal * secondVal;
                mainStack.push(String.valueOf(result));

            }else if(s.compareTo("/") == 0){
                double secondVal = Double.valueOf(mainStack.pop());
                double firstVal = Double.valueOf(mainStack.pop());
                double result = firstVal / secondVal;
                mainStack.push(String.valueOf(result));

            }else if(s.compareTo("-") == 0){
                double secondVal = Double.valueOf(mainStack.pop());
                double firstVal = Double.valueOf(mainStack.pop());
                double result = firstVal - secondVal;
                mainStack.push(String.valueOf(result));

            }else if(s.compareTo("+") == 0){
                double secondVal = Double.valueOf(mainStack.pop());
                double firstVal = Double.valueOf(mainStack.pop());
                double result = firstVal + secondVal;
                mainStack.push(String.valueOf(result));

            }else{
                mainStack.push(s);
            }
        }

        return Double.valueOf(mainStack.pop());
    }

    /*
    public double evaluateARRAY(String expr) {
        // TODO: Your awesome code here
        //Stack<String> st = new Stack<>();
        String[] splitted = expr.split(" ");
        for(String s : splitted)
            System.out.println(s);


        //i = current read pos
        //i++ until operator is found
        //Move back and use operator on the two first numbers found.
        //


        int lastSeenValIndex = 1;
        int secondLastSeenValIndex = 0;
        for(int i = 2; i < splitted.length; i++){

            //OPERATOR IS FOUND
            if(){
                String currentOperator = splitted[i];
                String.valueOf()

                double result = calculate(splitted[secondLastSeenValIndex], splitted[lastSeenValIndex], currentOperator);
                splitted[i] =



            }else{
                secondLastSeenValIndex = lastSeenValIndex;
                lastSeenValIndex = i;
            }

        }


        return 0;
    }

    private double calculate(String a, String b, String operator){
        return 0;
    }*/
}