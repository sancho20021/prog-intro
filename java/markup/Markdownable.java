package markup;

public interface Markdownable extends Element {
    void toMarkdown(StringBuilder str);
}
