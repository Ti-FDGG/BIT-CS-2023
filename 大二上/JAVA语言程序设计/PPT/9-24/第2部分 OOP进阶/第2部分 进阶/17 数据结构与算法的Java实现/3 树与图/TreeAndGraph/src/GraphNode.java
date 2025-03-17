import java.util.ArrayList;
import java.util.List;
//一个有向图节点类
public class GraphNode<T> {
    private T data;
    private final List<GraphNode<T>> inNodes=new ArrayList<>();
    private final List<GraphNode<T>> outNodes=new ArrayList<>();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GraphNode<T>> getInNodes() {
        return inNodes;
    }
    public List<GraphNode<T>> getOutNodes() {
        return outNodes;
    }

    public void addNodeToInNodes(GraphNode<T> node){
        inNodes.add(node);
    }
    public void addNodeToOutNodes(GraphNode<T> node){
        outNodes.add(node);
    }

}
