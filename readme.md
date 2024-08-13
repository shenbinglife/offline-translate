# offline-translate
实现常用词汇中英文之间的离线翻译

## 使用方式

```java
Translator translator = new CnTranslator();
// 传递要翻译的文本和目标语言
String output = translator.translate("文本", Language.en);
```

### 示例
```java
public static void main(String[] args) {
    String input = "洁面仪配合洁面深层清洁毛孔,清洁鼻孔面膜碎觉使劲挤才能出一点点皱纹,脸颊毛孔修复的看不见啦,草莓鼻历史遗留问题没辙,脸和脖子差不多颜色的皮肤才是健康的,长期使用安全健康的比同龄人显小五到十岁,28岁的妹子看看你们的鱼尾纹";
    String output = new CnTranslator().translate(input, Language.en);
    System.out.println(output);

    // 输出：clean noodle 仪 cooperate clean noodle deep clean pores , clean nostril noodle membrane broken sleep try your best squeeze talent able out a little wrinkle , cheek pores repair of look no see la , strawberry nose history legacy question no way , face and neck almost color of skin talent yes healthy of , long use safety healthy of compare peers show small five arrive 十岁 , 28岁 of girl have a look you of crow's feet

    output = new EnTranslator().translate(output, Language.cn);
    System.out.println(output);
    // 输出：干净的面条仪合作干净的面条深的干净的毛孔,干净的nostril面条膜broken睡觉尝试你的最好的挤天赋有能力的出去一个小的皱纹,脸颊毛孔维修的看不看la,草莓鼻子历史遗产问题不方式,脸和脖子几乎颜色的皮肤天赋是的健康的,长的使用安全健康的比较peers展示小的五到达十岁,28岁的女孩有一个看你的crow'sfeet

    // 谷歌译文：虽然事实本身并不是什么新鲜事，但更令人惊讶的是潘文斌借此机会指出了这一点。中国的环境问题不是一个专业问题，而是一个政治问题——问题的根源在于政治，而政治源于整个社会单方面追求经济快速发展
    input = "While the fact itself was hardly a revelation, more surprising was Pan’s use of this special occasion to point it out. China’s environment is not a professional issue but a political issue — politics is the very root of the problem, which stems from an entire society’s unilateral pursuit of rapid economic development";
    output = new EnTranslator().translate(input, Language.cn);
    System.out.println(output);
    // 输出：尽管这事实本身是几乎没有一个启示,更多的surprising是平底锅’s使用的这特别的场合到观点它出去.中国’s环境is不是一个专业的问题但一个政治的问题—政治is这非常根的这问题,哪个stems从一个全部的社会’s单方面追求的迅速的经济的发展
}
```

### 高级接口
```java
    /**
     * 执行翻译
     *
     * @param input          输入文本
     * @param targetLanguage 目标语言：支持cn或en
     * @param mode           翻译模式有2种：单词翻译；长句子分词翻译
     * @param errorMode      失败模式有3种：返回源单词；返回'#'作为翻译结果；抛出异常
     * @return 翻译后的文本
     */
    String translate(String input, Language targetLanguage, TransMode mode, ErrorMode errorMode);
```

## 原理
1. 内置2个基于字典的翻译器，根据字典直接将单词替换成对应语言的词汇
   - CnTranslator：善于将中文翻译成英文，也支持反向翻译
   - EnTranslator：善于将英文翻译成中文，也支持反向翻译
2. 针对句子，使用cws_evaluation分词器后，进行单词翻译

## 字典优化
源码内置了2个中英文单词字典文件，用户可以自定义更多的词汇翻译到字典中，来提高翻译的精确度
- cn-2-en.txt
- en-2-cn.txt


## 参考
- [现代汉语常用词表 · GitHub](https://gist.github.com/indiejoseph/eae09c673460aa0b56db)
- [GitHub - mahavivo/english-wordlists: 常用英语词汇表](https://github.com/mahavivo/english-wordlists)
- [NLPchina/ansj_seg: ansj分词.ict的真正java实现.分词效果速度都超过开源版的ict. 中文分词,人名识别,词性标注,用户自定义词典](https://github.com/NLPchina/ansj_seg)
- [ysc/cws_evaluation: Java开源项目cws_evaluation：中文分词器分词效果评估对比](https://github.com/ysc/cws_evaluation?tab=readme-ov-file)