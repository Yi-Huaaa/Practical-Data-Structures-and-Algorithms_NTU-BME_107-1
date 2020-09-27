/*
HW6-1:
Assignment: Construct the binary tree from inorder and postorder traversals.
Description: Please upload HW6_1.java that contains the following function:
public Node BTrebuild(String inString, String postString) {
//return the root of the binary tree constructed
}
Example input:
4 2 5 1 6 7 3 8
4 5 2 6 7 8 3 1
Note 1: the first line is the inorder traversal and the second line is the postorder traversal.
Note 3: For both HW6-1 and HW6-2, only positive integers are used in the test data.
The numbers in one test file are distinct, but please do take care of multi-digit integers.
 */
public class HW6_1{
        private int [] inorder, postorder;
    public Node BTrebuild(String inString, String postString) {
        //已確認
        //array size = inString.length() /2 + 1 = 8
        String[] tmp_1 = inString.split(" ");
        String[] tmp_2 = postString.split(" ");
        inorder =  new int [inString.length()/2 + 1];
        postorder = new int [postString.length()/2 + 1];
        //將 string 讀入 array
        for (int i = 0; i < inString.length() / 2 + 1; i++ ){
            inorder[i] = Integer.parseInt(tmp_1[i]);
        }
        for ( int i = 0; i < postString.length() / 2 + 1;i++){
            postorder[i] = Integer.parseInt(tmp_2[i]);
        }
        if (inorder.length != postorder.length) {
            return null;
        }
        //array的長度
        int instart = 0, inend = inString.length() / 2 + 1,
                poststart = 0, postend = postString.length() / 2 + 1;
        //以上確認過沒問題
        return (buildTreeHelper(instart,inend,poststart,postend));
    }
    public Node buildTreeHelper(int inStart, int inEnd, int postStart, int postEnd)
    {
        int key = 2, root_value = postorder[postEnd - 1], root_pos = 0;

        for(int i = inStart; i < inEnd; i++)
        {
            if(inorder[i] == root_value)
            {
                root_pos = i;
                break;
            }
        }
        Node root = new Node(inStart + root_pos, root_value);
        if(root_pos - inStart >= 1)
            root.setLeft(buildTreeHelper(inStart, root_pos, postStart, root_pos));
        if(inEnd - (root_pos + 1) >= 1)
            root.setRight(buildTreeHelper(root_pos + 1, inEnd, root_pos, postEnd - 1));
        return root;
    }


    public static void main(String[] args) {
        String inString = "4 2 5 1 6 7 3 8";
        String postString = "4 5 2 6 7 8 3 1";
        HW6_1 test = new HW6_1();
        test.BTrebuild(inString,postString);

    }

}