/*
HW3-1
輸入函數'ans'：
（（（1 +（12 * 5）） - （3 * 4））+（4/5））
函數'ans'的返回值：
49.80
/
Note:
1. The judge system will construct an object by calling Calculator cct = new Calculator().
2. We won't execute your main function in Calculator.java.
3. You do not need to worry about the format of printing the Double value.
4. All the numbers, operators, and brackets are seperated by 'single space', so you don't have to spend much time on parsing string.
5. You only need to handle with the operator such as (+, -, *, /)
File to be submitted to the judge system:
Calculator.java

//處理IOexceptation
//處理 whether is num or not
//處理 whether is operator or not
*/
import java.util.Stack;

public class Calculator {
    public Double ans (String e) {
        // please replace the following null to the answer you calculated.
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        //while( e != null){
        //while ( System.in != null ) {
            //Scanner scanner = new Scanner(e);
            //Scanner scanner = new Scanner(System.in);
            String s[] = e.split(" ");
            //String[] s = scanner.nextLine().split(" ");
            //upword okok
            for ( int i = 0 ; i <= (s.length-1); i++ ) {
                //System.out.println(s[i]);
                if(s[i].equals("("))
                    ;
                    //System.out.println("get (");
                else if(s[i].equals("+")) {
                    String tmp = s[i];
                    ops.push(tmp);
                    //System.out.println( "get" + ops.push(tmp) );
                }
                else if (s[i].equals("*")) {
                    String tmp = s[i];
                    ops.push(tmp);
                   // System.out.println( "get" + ops.push(tmp) );
                }
                else if (s[i].equals("-")) {
                    String tmp = s[i];
                    ops.push(tmp);
                   // System.out.println( "get" + ops.push(tmp) );
                }
                else if (s[i].equals("/")) {
                    String tmp = s[i];
                    ops.push(tmp);
                    //System.out.println( "get" + ops.push(tmp) );
                }

                else if (s[i].equals(")")) {
                    String op = ops.pop();
                    //System.out.println("ready to get answer" + ops.pop());
                    if(op.equals("+")){
                        vals.push(vals.pop() + vals.pop());
                        //System.out.println(vals.push(vals.pop() + vals.pop()));
                    }
                    else if (op.equals("-")){
                        vals.push(-(vals.pop() - vals.pop()));
                        //System.out.println(vals.push(vals.pop() - vals.pop()));
                    }
                    else if (op.equals("*")){
                        vals.push(vals.pop() * vals.pop());
                        //System.out.println(vals.push(vals.pop() * vals.pop()));
                    }
                    else if (op.equals("/")){
                        vals.push(1/(vals.pop() / vals.pop()));
                        //System.out.println(vals.push(vals.pop() / vals.pop()));
                    }
                }
                else {
                    String tmp = s[i];
                    vals.push(Double.parseDouble(tmp));
                    //System.out.println( "hohoho" );
                }
            }
        //}
       // System.out.println(vals.pop());
        return vals.pop();
    }


    public static void main(String[] args) {
        //String s ;
        String s  = "( 1 / 3 )";
        Calculator C = new Calculator();
        System.out.println(C.ans(s));

    }
}
