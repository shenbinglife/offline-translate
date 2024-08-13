package i.g.sbl.translate;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Iterator;

public abstract class AbstractTranslator implements Translator {

    @Override
    public String translate(String input, Language targetLanguage, TransMode mode, ErrorMode errorMode) {
        if (mode == TransMode.word) {
            return this.translate_word(input, targetLanguage, errorMode);
        } else {
            StringBuilder builder = new StringBuilder();
            Result parse = ToAnalysis.parse(input);
            Iterator<Term> iterator = parse.iterator();

            while (iterator.hasNext()) {
                Term term = iterator.next();
                String name = term.getName();
                if (name.isBlank()) {
                    continue;
                }
                builder.append(this.translate_word(name, targetLanguage, errorMode));
                if (targetLanguage == Language.en) {
                    builder.append(' ');
                }
            }
            return builder.toString();
        }
    }

    public String translate_word(String word, Language targetLanguage, ErrorMode errorMode) {
        String output = this.translate_word(word, targetLanguage);
        if (output == null) {
            return switch (errorMode) {
                case origin -> word;
                case star -> "#";
                case error -> throw new IllegalArgumentException("Can not translate word: " + word);
                default -> throw new IllegalArgumentException("Unsupported error mode: " + errorMode);
            };
        } else {
            return output;
        }
    }

    public abstract String translate_word(String word, Language targetLanguage);
}
