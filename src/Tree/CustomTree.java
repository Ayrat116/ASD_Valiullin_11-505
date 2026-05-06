package Tree;

import java.util.*;

public class CustomTree {
    TreeNode node;

    public CustomTree(int info){
        this.node = new TreeNode(info);
    }

    public CustomTree(int info, List<TreeNode> children){
        this.node = new TreeNode(info, children);
    }

    //Обход в ширину
    public void bfs(){
        if(node == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            System.out.print(current + " ");
            queue.addAll(current.children);
        }
    }

    //Обход в глубину
    public void dfs(){
        if(node == null){
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(node);

        while (!deque.isEmpty()){
            TreeNode current = deque.pop();
            System.out.println(current + " ");

            for (int i = current.children.size(); i > 0; i--){
                deque.push(current.children.get(i));
            }
        }
    }
}