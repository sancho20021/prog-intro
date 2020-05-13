package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> list) {
        super(list);
    }

    @Override
    public void toHtml(StringBuilder str) {
        super.toHtml(str, "<ul>", "</ul>");
    }
}
