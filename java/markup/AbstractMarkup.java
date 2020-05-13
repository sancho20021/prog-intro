package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markdownable {
    private List<InParagraphPutable> list;

    protected AbstractMarkup(List<InParagraphPutable> list) {
        this.list = list;
    }

    protected void toMarkdown(StringBuilder str, String mark) {
        str.append(mark);
        for (InParagraphPutable element : list) {
            element.toMarkdown(str);
        }
        str.append(mark);
    }

    protected void toHtml(StringBuilder str, String tag1, String tag2) {
        str.append(tag1);
        for (InParagraphPutable element : list) {
            element.toHtml(str);
        }
        str.append(tag2);
    }
}
