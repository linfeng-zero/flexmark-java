package com.vladsch.flexmark.samples;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.vladsch.flexmark.youtrack.converter.YouTrackConverterExtension;

import java.util.Arrays;

public class MarkdownToYouTrack {
    static final MutableDataSet OPTIONS = new MutableDataSet()
            .set(Parser.EXTENSIONS, Arrays.asList(
                    TablesExtension.create(),
                    StrikethroughExtension.create(),
                    YouTrackConverterExtension.create()
            ))
            ;

    static final Parser PARSER = Parser.builder(OPTIONS).build();
    static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    // use the PARSER to parse and RENDERER to parse pegdown indentation rules and render CommonMark

    public static void main(String[] args) {
        final String markdown = "Heading\n" +
                "-----\n" +
                "paragraph text \n" +
                "lazy continuation\n" +
                "\n" +
                "* list item\n" +
                "  > block quote\n" +
                "  lazy continuation\n" +
                "\n" +
                "~~~info\n" +
                "        with uneven indent\n" +
                "           with uneven indent\n" +
                "     indented code\n" +
                "~~~ \n" +
                "\n" +
                "        with uneven indent\n" +
                "           with uneven indent\n" +
                "     indented code\n" +
                "1. numbered item 1   \n" +
                "1. numbered item 2   \n" +
                "1. numbered item 3   \n" +
                "   - bullet item 1   \n" +
                "   - bullet item 2   \n" +
                "   - bullet item 3   \n" +
                "     1. numbered sub-item 1   \n" +
                "     1. numbered sub-item 2   \n" +
                "     1. numbered sub-item 3   \n" +
                "    \n" +
                "    ~~~info\n" +
                "            with uneven indent\n" +
                "               with uneven indent\n" +
                "         indented code\n" +
                "    ~~~ \n" +
                "    \n" +
                "            with uneven indent\n" +
                "               with uneven indent\n" +
                "         indented code\n" +
                "";
        System.out.println("markdown\n");
        System.out.println(markdown);

        Node document = PARSER.parse(markdown);
        String text = RENDERER.render(document);

        System.out.println("\n\nYouTrack\n");
        System.out.println(text);
    }
}