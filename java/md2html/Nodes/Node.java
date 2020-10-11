package md2html.Nodes;

import java.util.Map;

public abstract class Node {
    protected StringBuilder value;
    private TokenNode parent;

    public Node(String value, TokenNode parent) {
        this.value = new StringBuilder(value);
        this.parent = parent;
    }

    public String getValue() {
        return value.toString();
    }

    protected TokenNode getParent() {
        return parent;
    }

    public abstract Node getStringNode();

    public abstract Node getNewTokenNode(String value);

    public abstract Node getNewStringNode(String value);

    public Node getNewStringNode(StringBuilder value) {
        return getNewStringNode(value.toString());
    }

    protected Node addNode(String value, boolean isString, TokenNode parent) {
        Node newNode = isString ? new StringNode(value, parent) : new TokenNode(value, parent);
        parent.addSon(newNode);
        return newNode;
    }

    public abstract void addToValue(String s);

    public void addToValue(StringBuilder s) {
        addToValue(s.toString());
    }

    public void setZeroValue() {
        value.setLength(0);
    }

    public abstract StringBuilder collectAllStrings(Map<String, Node> usedSymbols);
}
