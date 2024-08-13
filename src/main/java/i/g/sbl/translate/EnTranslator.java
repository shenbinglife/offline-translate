package i.g.sbl.translate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 专注于将中文词汇翻译成英文
 */
public class EnTranslator extends AbstractTranslator implements Translator {

    public static final Map<String, String> cn2en = new HashMap<>();
    public static final Map<String, String> en2cn = new HashMap<>();

    static {
        try (
                InputStream enStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("en-2-cn.txt");

                BufferedReader enReader = new BufferedReader(new InputStreamReader(enStream, StandardCharsets.UTF_8));
        ) {
            while (enReader.ready()) {
                String line = enReader.readLine();
                String[] split = line.split("=");
                String en = split[0];
                String cn = split[1];
                cn2en.put(cn, en);
                en2cn.put(en, cn);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can not read cn translator", e);
        }
    }


    @Override
    public String translate_word(String word, Language targetLanguage) {
        return switch (targetLanguage) {
            case cn -> en2cn.get(word);
            case en -> cn2en.get(word);
            default -> throw new IllegalArgumentException("Unsupported target language: " + targetLanguage);
        };
    }
}
