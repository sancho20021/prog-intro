package markup;

import java.util.List;

public class ListItem implements Element {
    private List<InListItemPutable> list;

    public ListItem(List<InListItemPutable> list) {
        this.list = list;
    }

    @Override
    public void toHtml(StringBuilder str) {
        str.append("<li>");
        for (Element element : list) {
            element.toHtml(str);
        }
        str.append("</li>");
    }
}
