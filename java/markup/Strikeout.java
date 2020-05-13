package markup;


import java.util.List;

public class Strikeout extends AbstractMarkup implements InParagraphPutable {

    public Strikeout(List<InParagraphPutable> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        toMarkdown(str, "~");
    }

    @Override
    public void toHtml(StringBuilder str) {
        toHtml(str, "<s>", "</s>");
    }
}
