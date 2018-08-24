// This is a generated file. Not intended for manual editing.
package ru.adelf.idea.dotenv.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ru.adelf.idea.dotenv.psi.DotEnvTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class DotEnvParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == KEY) {
      r = key(b, 0);
    }
    else if (t == PROPERTY) {
      r = property(b, 0);
    }
    else if (t == VALUE) {
      r = value(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return dotEnvFile(b, l + 1);
  }

  /* ********************************************************** */
  // item_*
  static boolean dotEnvFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "dotEnvFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "dotEnvFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // property|COMMENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = property(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // KEY_CHARS
  public static boolean key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "key")) return false;
    if (!nextTokenIs(b, KEY_CHARS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_CHARS);
    exit_section_(b, m, KEY, r);
    return r;
  }

  /* ********************************************************** */
  // (key SEPARATOR value? COMMENT?) | key COMMENT?
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, KEY_CHARS)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_0(b, l + 1);
    if (!r) r = property_1(b, l + 1);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  // key SEPARATOR value? COMMENT?
  private static boolean property_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && property_0_2(b, l + 1);
    r = r && property_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // value?
  private static boolean property_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_2")) return false;
    value(b, l + 1);
    return true;
  }

  // COMMENT?
  private static boolean property_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_3")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // key COMMENT?
  private static boolean property_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = key(b, l + 1);
    r = r && property_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT?
  private static boolean property_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_1_1")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  /* ********************************************************** */
  // VALUE_CHARS | QUOTE VALUE_CHARS? QUOTE?
  public static boolean value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value")) return false;
    if (!nextTokenIs(b, "<value>", QUOTE, VALUE_CHARS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE, "<value>");
    r = consumeToken(b, VALUE_CHARS);
    if (!r) r = value_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // QUOTE VALUE_CHARS? QUOTE?
  private static boolean value_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE);
    r = r && value_1_1(b, l + 1);
    r = r && value_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // VALUE_CHARS?
  private static boolean value_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_1_1")) return false;
    consumeToken(b, VALUE_CHARS);
    return true;
  }

  // QUOTE?
  private static boolean value_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_1_2")) return false;
    consumeToken(b, QUOTE);
    return true;
  }

}
