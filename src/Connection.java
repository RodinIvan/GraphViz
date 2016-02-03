public class Connection {
    String node1;
    String node2;
    double weight;
    public Connection(String node1, String node2, double weight){
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    public String getNode1(){
        return node1;
    }
    public String getNode2(){
        return node2;
    }
    public double getWeight(){
        return weight;
    }
}