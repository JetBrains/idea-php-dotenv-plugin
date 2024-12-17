package ru.adelf.idea.dotenv.inspections;

import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.adelf.idea.dotenv.DotEnvBundle;
import ru.adelf.idea.dotenv.DotEnvPsiElementsVisitor;
import ru.adelf.idea.dotenv.models.KeyValuePsiElement;
import ru.adelf.idea.dotenv.psi.DotEnvFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateKeyInspection extends LocalInspectionTool {
    // Change the display name within the plugin.xml
    // This needs to be here as otherwise the tests will throw errors.
    @NotNull
    @Override
    public String getDisplayName() {
        return DotEnvBundle.message("inspection.message.duplicate.key");
    }

    @Override
    public boolean runForWholeFile() {
        return true;
    }

    @Nullable
    @Override
    public ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        if(!(file instanceof DotEnvFile)) {
            return null;
        }

        return analyzeFile(file, manager, isOnTheFly).getResultsArray();
    }

    @NotNull
    private static ProblemsHolder analyzeFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        DotEnvPsiElementsVisitor visitor = new DotEnvPsiElementsVisitor();
        file.acceptChildren(visitor);

        ProblemsHolder problemsHolder = new ProblemsHolder(manager, file, isOnTheFly);

        Map<String, PsiElement> existingKeys = new HashMap<>();
        Set<PsiElement> markedElements = new HashSet<>();
        for(KeyValuePsiElement keyValue : visitor.getCollectedItems()) {
            final String key = keyValue.getKey();

            if(existingKeys.containsKey(key)) {
                problemsHolder.registerProblem(keyValue.getElement(), DotEnvBundle.message("inspection.message.duplicate.key"));

                PsiElement markedElement = existingKeys.get(key);
                if(!markedElements.contains(markedElement)) {
                    problemsHolder.registerProblem(markedElement, DotEnvBundle.message("inspection.message.duplicate.key"));
                    markedElements.add(markedElement);
                }
            } else {
                existingKeys.put(key, keyValue.getElement());
            }
        }

        return problemsHolder;
    }
}
