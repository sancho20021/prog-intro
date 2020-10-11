package md2html;

import md2html.Nodes.Node;
import md2html.Nodes.TokenNode;

import java.util.HashMap;
import java.util.Map;

class ToHtmlConverter {
    private StringBuilder block;
    private int pos, end;
    private final static Map<String, String> OPENING_TAGS = Map.of(
            "*", "<em>",
            "_", "<em>",
            "**", "<strong>",
            "__", "<strong>", "--", "<s>", "`", "<code>");
    private final static Map<String, String> CLOSING_TAGS = Map.of("*", "</em>", "_", "</em>",
            "**", "</strong>", "__", "</strong>", "--", "</s>", "`", "</code>");
    private final static Map<Character, String> specialSymbols = Map.of(
            '<', "&lt;", '>', "&gt;", '&', "&amp;");

    public String getHtml(StringBuilder block) {
        if (block.length() == 0) {
            return "";
        }

        this.block = block;
        block.setCharAt(block.length() - 1, '\0');
        pos = 0;
        end = block.length();
        StringBuilder html = new StringBuilder();
        String tag = processHeader();
        html.append('<').append(tag).append('>');
        html.append(emParse('\u0000'));
        html.append("</").append(tag).append('>');
        html.append('\n');
        return html.toString();
    }

    private String processHeader() {
        int level = 0;
        while (block.charAt(pos) == '#') {
            pos++;
            level++;
        }
        if (level == 0 || pos == end || !Character.isWhitespace(block.charAt(pos))) {
            pos = 0;
            return "p";
        } else {
            pos++;
        }
        return "h" + level;
    }

    private StringBuilder emParse(char closingSymbol) {
        Map<String, Node> usedSymbols = new HashMap<>();
        Node current = new TokenNode("", null);
        final Node root = current;
        for (; pos < end; ) {
            if (block.charAt(pos) == closingSymbol) {
                pos++;
                break;
            }
            String s;
            if (pos < end - 1 && OPENING_TAGS.containsKey(block.substring(pos, pos + 2))) {
                s = block.substring(pos, pos + 2);
                pos += 2;
            } else {
                s = block.substring(pos, pos + 1);
                s = specialSymbols.getOrDefault(s.charAt(0), s);
                pos++;
            }
            if (s.equals("[")) {
                current = current.getStringNode();
                current.addToValue(hrefParse());
                continue;
            }
            if (OPENING_TAGS.containsKey(s)) {
                if (!usedSymbols.containsKey(s)) {
                    current = current.getNewTokenNode(s);
                    usedSymbols.put(s, current);
                } else {
                    current = usedSymbols.get(s);
                    usedSymbols.remove(current.getValue());
                    current.setZeroValue();
                    current = current.getNewStringNode(new StringBuilder(OPENING_TAGS.get(s))
                            .append(current.collectAllStrings(usedSymbols))
                            .append(CLOSING_TAGS.get(s)));
                }
            } else {
                current = current.getStringNode();
                if (s.equals("\\") && pos < end && (block.charAt(pos) == '*' || block.charAt(pos) == '_')) {
                    current.addToValue(block.substring(pos, pos + 1));
                    pos++;
                } else {
                    current.addToValue(s);
                }
            }
        }
        return root.collectAllStrings(usedSymbols);
    }

    private StringBuilder hrefParse() {
        StringBuilder description = emParse(']');
        pos++;
        StringBuilder ref = new StringBuilder();
        char c;
        while ((c = block.charAt(pos)) != ')') {
            ref.append(c);
            pos++;
        }
        pos++;
        StringBuilder res = new StringBuilder("<a href='").append(ref).append("'>");
        res.append(description).append("</a>");
        return res;
    }
}
