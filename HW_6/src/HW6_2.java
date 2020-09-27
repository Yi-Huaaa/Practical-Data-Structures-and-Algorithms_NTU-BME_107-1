/*
HW6-2:
Assignment: Print the postorder traversal from given inorder and preorder traversals.
Description: Please upload HW6_2.java that contains the following function:
public void BTprint(String inString, String preString) {
// print the postorder string inside this function
}

Example input:
4 2 5 1 3 6 //in
1 2 4 5 3 6 //pre

Note 2: the first line is the inorder traversal and the second line is the preorder traversal.

Example output:
4 5 2 6 3 1

Note 3: For both HW6-1 and HW6-2, only positive integers are used in the test data.
The numbers in one test file are distinct, but please do take care of multi-digit integers.
 */

public class HW6_2 {

    private int [] inorder, preorder;
    public Node BTrebuild(String inString, String preString) {
        //已確認
        //array size = inString.length() /2 + 1 = 8
        String[] tmp_1 = inString.split(" ");
        String[] tmp_2 = preString.split(" ");
        inorder =  new int [inString.length()/2 + 1];
        preorder = new int [preString.length()/2 + 1];
        //將 string 讀入 array
        for (int i = 0; i < inString.length() / 2 + 1; i++ ){
            inorder[i] = Integer.parseInt(tmp_1[i]);
        }
        for ( int i = 0; i < preString.length() / 2 + 1;i++){
            preorder[i] = Integer.parseInt(tmp_2[i]);
        }
        if (inorder.length != preorder.length) {
            return null;
        }
        //array的長度
        int instart = 0, inend = inString.length() / 2 + 1,
                prestart = 0, preend = preString.length() / 2 + 1;
        //以上確認過沒問題
        return (buildTreeHelper(instart,inend,prestart,preend));
    }
    public Node buildTreeHelper(int inStart, int inEnd, int preStart, int preEnd)
    {

        int key = 2, root_value = preorder[preStart], root_pos = 0, left_num = 0, right_num = 0;
        //System.out.println(root_value);

        for(int i = inStart; i < inEnd; i++)
        {
            if(inorder[i] == root_value)
            {
                root_pos = i;
                left_num = i - inStart;
                //System.out.println(inStart+" "+ inEnd +" " + preStart+" " + preEnd + " " +root_pos);
                break;
            }
        }
        ///////////////////////////////////////////
        Node root = new Node(inStart + root_pos, root_value);
        if(root_pos - inStart >= 1)
        {
            //System.out.println("call left");
            root.setLeft(buildTreeHelper(inStart, root_pos, preStart + 1, preStart + left_num + 1));

        }
        else
            root.setLeft(null);
        if(inEnd - (root_pos + 1) >= 1)
        {
            //System.out.println("call right");
            root.setRight(buildTreeHelper(root_pos + 1, inEnd, preStart + left_num + 1, preEnd));

        }
        else
            root.setRight(null);
        return root;
    }

    public void BTprint(String inString, String preString) {
        // print the postorder string inside this function
        Node head = BTrebuild(inString, preString);
        String ans = traver(head);
        System.out.println(ans.substring(0, ans.length() - 1));


    }

    public String traver (Node root)
    {
        //System.out.println(root.getValue()+"haha");
        String ans = "";
        if (root.getLeft() != null)
            ans += traver(root.getLeft());
        if (root.getRight() != null)
            ans += traver(root.getRight());
        ans += root.getValue().toString() + " ";
        return ans;
    }

    public static void main(String[] args) {
        String a = "4 2 5 1 3 6";
        String b = "1 2 4 5 3 6";
        HW6_2 test = new HW6_2();
        test.BTprint(a,b);
    }


}
