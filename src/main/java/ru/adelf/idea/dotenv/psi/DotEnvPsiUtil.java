package ru.adelf.idea.dotenv.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class DotEnvPsiUtil {

    private static final TokenSet VALUE_TOKEN_SET = TokenSet.create(
        DotEnvTypes.VALUE_CHARS,
        DotEnvTypes.NESTED_VARIABLE_KEY,
        DotEnvTypes.NESTED_VARIABLE_START,
        DotEnvTypes.NESTED_VARIABLE_END
    );

    public static String getKeyText(DotEnvProperty element) {
        // IMPORTANT: Convert embedded escaped spaces to simple spaces
        return element.getKey().getText().replaceAll("\\\\ ", " ");
    }

    public static String getValueText(DotEnvProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(DotEnvTypes.VALUE);
        if (valueNode != null) {
            return Arrays.stream(valueNode.getChildren(VALUE_TOKEN_SET))
                .map(ASTNode::getText)
                .collect(Collectors.joining(""));
        } else {
            return "";
        }
    }

    public static String getName(DotEnvProperty element) {
        return getKeyText(element);
    }

    public static PsiElement setName(DotEnvProperty element, @NotNull String newName) {
        ASTNode keyNode = element.getNode().findChildByType(DotEnvTypes.KEY);
        if (keyNode != null) {
            DotEnvProperty property = DotEnvElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(DotEnvProperty element) {
        return element.getKey();
    }
}
