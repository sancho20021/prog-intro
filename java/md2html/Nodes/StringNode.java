package md2html.Nodes;

import java.util.Map;

public class StringNode extends Node {
    public StringNode(String value, TokenNode parent) {
        super(value, parent);
    }

    @Override
    public Node getStringNode() {
        return this;
    }

    @Override
    public Node getNewTokenNode(String value) {
        return addNode(value, false, this.getParent());
    }

    @Override
    public Node getNewStringNode(String value) {
        return addNode(value, true, this.getParent());
    }

    @Override
    public void addToValue(String s) {
        value.append(s);
    }

    @Override
    public StringBuilder collectAllStrings(Map<String, Node> usedSymbols) {
        return value;
    }
}
