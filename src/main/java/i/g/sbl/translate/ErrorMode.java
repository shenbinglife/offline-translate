package i.g.sbl.translate;

/**
 * 翻译失败的处理模式
 */
public enum ErrorMode {
    /**
     * 返回原始未翻译的单词
     */
    origin,
    /**
     * 使用#代替返回
     */
    star,
    /**
     * 抛出异常
     */
    error
}
