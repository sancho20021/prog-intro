package markup;

import java.util.List;

public abstract class AbstractList implements InListItemPutable {
    protected List<ListItem> list;

    protected AbstractList(List<ListItem> list) {
        this.list = list;
    }

    protected void toHtml(StringBuilder str, String tag1, String tag2) {
        str.append(tag1);
        for (ListItem item : list) {
            item.toHtml(str);
        }
        str.append(tag2);
    }
}
