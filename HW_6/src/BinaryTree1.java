public class BinaryTree1{
    /**
     *@param inorder : A list of integers that inorder traversal of a tree
     *@param postorder : A list of integers that postorder traversal of a tree
     *@return : Root of a tree
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public static TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd,
                                    int[] postorder, int postStart, int postEnd){
        if (inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int mid = findMid(inorder, inStart, inEnd, postorder[postEnd]);
        root.left = buildTreeHelper(inorder, inStart, mid - 1,
                postorder, postStart, postStart + (mid - inStart) - 1);
        root.right = buildTreeHelper(inorder, mid + 1, inEnd,
                postorder, postStart + (mid - inStart), postEnd - 1);
        //System.out.println(root.left);
        //System.out.println(root.right);
        System.out.println(root);
        return root;
    }

    public static int findMid(int[] arr, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == key) {
                //System.out.println(key);
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] inorder = new int [8];
        inorder [0] = 4;
        inorder [1] = 2;
        inorder [2] = 5;
        inorder [3] = 1;
        inorder [4] = 6;
        inorder [5] = 7;
        inorder [6] = 3;
        inorder [7] = 8;
        int[] postorder = new int [8];
        postorder [0] = 4;
        postorder [1] = 5;
        postorder [2] = 2;
        postorder [3] = 6;
        postorder [4] = 7;
        postorder [5] = 8;
        postorder [6] = 3;
        postorder [7] = 1;
        buildTree(inorder,postorder);
    }

}