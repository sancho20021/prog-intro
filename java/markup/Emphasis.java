package markup;


import java.util.List;

public class Emphasis extends AbstractMarkup implements InParagraphPutable {

    public Emphasis(List<InParagraphPutable> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        super.toMarkdown(str, "*");
    }

    @Override
    public void toHtml(StringBuilder str) {
        super.toHtml(str, "<em>", "</em>");
    }
}