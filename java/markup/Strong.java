package markup;

import java.util.List;

public class Strong extends AbstractMarkup implements InParagraphPutable {

    public Strong(List<InParagraphPutable> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        super.toMarkdown(str, "__");
    }

    @Override
    public void toHtml(StringBuilder str) {
        super.toHtml(str, "<strong>", "</strong>");
    }
}
