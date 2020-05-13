package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> list) {
        super(list);
    }

    @Override
    public void toHtml(StringBuilder str) {
        super.toHtml(str, "<ol>", "</ol>");
    }
}
