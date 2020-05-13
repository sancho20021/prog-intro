package markup;


import java.util.List;

public class Paragraph extends AbstractMarkup implements InListItemPutable {

    public Paragraph(List<InParagraphPutable> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        super.toMarkdown(str, "");
    }

    @Override
    public void toHtml(StringBuilder str) {
        super.toHtml(str, "", "");
    }
}
