package md2html.Nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TokenNode extends Node {
    private List<Node> sons;

    public TokenNode(String value, TokenNode parent) {
        super(value, parent);
        sons = new ArrayList<>();
    }

    @Override
    public Node getStringNode() {
        return getNewStringNode("");
    }

    @Override
    public Node getNewTokenNode(String value) {
        return addNode(value, false, this);
    }

    @Override
    public Node getNewStringNode(String value) {
        return addNode(value, true, this);
    }

    @Override
    public void addToValue(String s) {
    }

    protected void addSon(Node node) {
        sons.add(node);
    }

    @Override
    public StringBuilder collectAllStrings(Map<String, Node> usedSymbols) {
        usedSymbols.remove(value.toString());
        StringBuilder s = new StringBuilder();
        s.append(value);
        for (Node son : sons) {
            s.append(son.collectAllStrings(usedSymbols));
        }
        sons.clear();
        return s;
    }
}
