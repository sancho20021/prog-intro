package markup;


public class Text implements InParagraphPutable {
    private String str;

    public Text(String str) {
        this.str = str;
    }

    @Override
    public void toMarkdown(StringBuilder str) {
        str.append(this.str);
    }

    @Override
    public void toHtml(StringBuilder str) {
        str.append(this.str);
    }
}