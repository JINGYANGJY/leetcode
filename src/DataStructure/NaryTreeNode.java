package DataStructure;

import java.util.ArrayList;
import java.util.List;

public class NaryTreeNode {
    public int value;
    public List<NaryTreeNode> children;

    public NaryTreeNode(int value) {
        this.value = value;
        children = new ArrayList<>();
    }

}
