package ru.adelf.idea.dotenv.tests.dotenv

import com.intellij.openapi.application.PathManager
import com.intellij.platform.testFramework.junit5.codeInsight.fixture.lexerFixture
import com.intellij.testFramework.junit5.fixture.TestFixtures
import org.junit.jupiter.api.Test
import ru.adelf.idea.dotenv.grammars.DotEnvLexerAdapter

@TestFixtures
class DotEnvLexerTest {
  private val lexer by lexerFixture(
    "${PathManager.getCommunityHomePath()}/plugins/env-files-support/tests/testResources/ru/adelf/idea/dotenv/tests/dotenv/fixtures",
    trimTestData = false,
  ) { DotEnvLexerAdapter() }

  @Test
  fun testLexerComments() = lexer.doFileTest("env")

  @Test
  fun testLexerProperties() = lexer.doFileTest("env")

  @Test
  fun testLexerQuotes() = lexer.doFileTest("env")

  @Test
  fun testLexerNestedVariables() = lexer.doFileTest("env")

  @Test
  fun testLexerCompletionTokens() = lexer.doFileTest("env")

  @Test
  fun testLexerAllowsEmptyNestedVariables() = lexer.doFileTest("env")

  @Test
  fun testLexerAllowsNonGrammaticalDollarSymbols() = lexer.doFileTest("env")

}