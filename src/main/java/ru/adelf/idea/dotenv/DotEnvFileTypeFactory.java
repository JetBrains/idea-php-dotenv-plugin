package ru.adelf.idea.dotenv;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class DotEnvFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(DotEnvFileType.INSTANCE, new ExactFileNameMatcher(".env"));
        fileTypeConsumer.consume(DotEnvFileType.INSTANCE, new ExtensionFileNameMatcher("env"));
        fileTypeConsumer.consume(DotEnvFileType.INSTANCE, new WildcardFileNameMatcher(".env.*"));
    }
}
