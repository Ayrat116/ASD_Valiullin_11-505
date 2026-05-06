package Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int info;
    List<TreeNode> children;

    public TreeNode(int info){
        this.info = info;
        this.children = new ArrayList<>();
    }

    public TreeNode(int info, List<TreeNode> children){
        this.info = info;
        this.children = children != null ? children : new ArrayList<>();
    }
}
