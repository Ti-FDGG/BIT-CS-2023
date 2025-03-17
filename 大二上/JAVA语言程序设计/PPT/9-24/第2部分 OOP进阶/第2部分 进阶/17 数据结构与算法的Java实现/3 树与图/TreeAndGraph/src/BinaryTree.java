
public class BinaryTree {
    public static void main(String[] args) {
        var tree = createTree();
        System.out.println("\n---中序遍历---");
        inorderVisitTree(tree);
        System.out.println("\n---前序遍历---");
        preorderVisitTree(tree);
        System.out.println("\n---后序遍历---");
        postorderVisitTree(tree);
    }

    //创建有五个节点的二叉树
    static TreeNode<String> createTree() {
        //创建树中所有的节点对象
        var root = new TreeNode<String>("A");
        var bNode = new TreeNode<String>("B");
        var cNode = new TreeNode<String>("C");
        var dNode = new TreeNode<String>("D");
        var eNode = new TreeNode<String>("E");
        //建立节点之间的关联，以便构建出一棵树
        root.setLchild(bNode);
        root.setRchild(cNode);
        cNode.setLchild(dNode);
        cNode.setRchild(eNode);
        return root;
    }

    //中序遍历（递归实现）
    static void inorderVisitTree(TreeNode<String> root) {
        if (root != null) {
            inorderVisitTree(root.getLchild());
            System.out.print(root.getData() + ",");
            inorderVisitTree(root.getRchild());
        }
    }

    //前序遍历
    static void preorderVisitTree(TreeNode<String> root) {
        if (root != null) {
            System.out.print(root.getData() + ",");
            preorderVisitTree(root.getLchild());
            preorderVisitTree(root.getRchild());
        }
    }

    //后序遍历
    static void postorderVisitTree(TreeNode<String> root) {
        if (root != null) {
            postorderVisitTree(root.getLchild());
            postorderVisitTree(root.getRchild());
            System.out.print(root.getData() + ",");
        }
    }

}

//二叉树节点
class TreeNode<T> {
    private T data;
    private TreeNode<T> lchild;
    private TreeNode<T> rchild;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLchild() {
        return lchild;
    }

    public void setLchild(TreeNode<T> lchild) {
        this.lchild = lchild;
    }

    public TreeNode<T> getRchild() {
        return rchild;
    }

    public void setRchild(TreeNode<T> rchild) {
        this.rchild = rchild;
    }
}