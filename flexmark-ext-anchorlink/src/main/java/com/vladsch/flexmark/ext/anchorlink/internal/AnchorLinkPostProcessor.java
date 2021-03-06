package com.vladsch.flexmark.ext.anchorlink.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.anchorlink.AnchorLink;
import com.vladsch.flexmark.parser.block.DocumentPostProcessor;
import com.vladsch.flexmark.parser.block.DocumentPostProcessorFactory;
import com.vladsch.flexmark.util.ast.*;
import org.jetbrains.annotations.NotNull;

public class AnchorLinkPostProcessor extends DocumentPostProcessor {
    private final AnchorLinkOptions options;
    private final NodeVisitor myVisitor;

    public AnchorLinkPostProcessor(Document document) {
        this.options = new AnchorLinkOptions(document);
        myVisitor = new NodeVisitor(
                new VisitHandler<>(Heading.class, this::visit)
        );
    }

    @NotNull
    @Override
    public Document processDocument(@NotNull Document document) {
        myVisitor.visit(document);
        return document;
    }

    private void visit(Heading node) {
        if (!node.isOrDescendantOfType(DoNotDecorate.class)) {
            processNode(node);
        }
    }

    private void processNode(Heading node) {
        if (node.getText().isNotNull()) {
            Node anchor = new AnchorLink();

            if (!options.wrapText) {
                if (node.getFirstChild() == null) {
                    node.appendChild(anchor);
                } else {
                    node.getFirstChild().insertBefore(anchor);
                }
            } else {
                anchor.takeChildren(node);
                node.appendChild(anchor);
            }
        }
    }

    public static class Factory extends DocumentPostProcessorFactory {
        @NotNull
        @Override
        public DocumentPostProcessor apply(@NotNull Document document) {
            return new AnchorLinkPostProcessor(document);
        }
    }
}
