# kotlin-stackoverflow-data-analysis

## First Step
- **[kotlin-posts-0.csv](/kotlin-posts-0.csv) - First extraction getting all posts related with kotlin and Android -**
- **[kotlin-posts-1.csv](/kotlin-posts-1.csv) - Remove content inside the tags ```<code>, <pre> and <blockquotes>```**
- **[kotlin-posts-2.csv](/kotlin-posts-2.csv) - Remove html tags**
- **[kotlin-posts-3.csv](/kotlin-posts-3.csv) - Remove URL's**
- **[kotlin-posts-4.csv](/kotlin-posts-4.csv) - Remove punctuation**
- **[kotlin-posts-5.csv](/kotlin-posts-5.csv) - Remove stop words and one-letter words**
- **[kotlin-posts-6.csv](/kotlin-posts-6.csv) - Apply Porter Stemming Algorithm - [Porter Stemming Repo](https://github.com/victorlaerte/java-porter-stemming)**

## Second Step
- **[output.mallet](/output.mallet)** - File generated with following command: 
```
//For test-1 and test-2
mallet import-file --input mallet-input/kotlin-posts-i.txt --output output.mallet --keep-sequence --remove-stopwords
```
- **[mallet-output](/mallet-output)** - In this directory you can find four test rounds. ```test-1``` was generated with the [output.mallet](/output.mallet) generated with [kotlin-posts-6.csv](/kotlin-posts-6.csv). ```test-2``` was generated with [kotlin-posts-5.csv](/kotlin-posts-5.csv).
- **[test-1](/mallet-output/test-1)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics 15 --optimize-interval 20 --output-state topic-state-15.gz --output-topic-keys output-keys-15-topics.txt --output-doc-topics output-composition-15-topics.txt --num-iterations 2000 --num-top-words 10 --topic-word-weights-file topic-word-weights-15.txt --xml-topic-report xml-topic-report.xml --xml-topic-phrase-report xml-topic-phrase-report.xml
```
- **[test-2](/mallet-output/test-2)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```

## Third Step

After some [literature review](/documents/SteyversGriffithsLSABookFormatted.pdf) we decided to use the file [test-2](/mallet-output/test-2) in the remaining of our research, mainly because each line in [kotlin-posts-6.csv](/kotlin-posts-6.csv) is an Stack Overflow question and can be seen as a instance of mallet. [You can see more in mallet docs](http://mallet.cs.umass.edu/import.php).

> MALLET represents data as lists of "instances". All MALLET instances include a data object. An instance can also include a name and (in classification contexts) a label. For example, if the application is guessing the language of web pages, an instance might consist of a vector of word counts (data), the URL of the page (name) and the language of the page (label).

> There are two primary methods for importing data into MALLET format, first when the source data consists of many separate files, and second when the data is contained in a single file, with one instance per line.

> In this case, the first token of each line (whitespace delimited, with optional comma) becomes the instance name, the second token becomes the label, and all additional text on the line is interpreted as a sequence of word tokens. Note that the data in this case will be a vector of feature/value pairs, such that a feature consists of a distinct word type and the value is the number of times that word occurs in the text.

## Fourth Step

Define the number topics to be used (5, 10, 15 or 20) and name it.

We have chosen 15 topics based on some [literature review](http://psiexp.ss.uci.edu/research/papers/SteyversGriffithsLSABookFormatted.pdf), and because in our analysis with more than 15 topics, there were many topics with too low weight. The files were duplicated in folder [final-topics](/final-topics)

### Topics

| LDAvis ID | Topic Name | Weight | Words | Top 5 Phrases |
|---|---|---|---|---|
|1|14|0.36189|android(0.0216) wai(0.0198) kotlin(0.0173) code(0.0166) work(0.0153) question(0.0133) make(0.0107) someth(0.0104) exampl(0.0103) app(0.0096)|android kotlin(0.0024) everi time(0.0022) kotlin android(0.0011) correct wai(0.0011) react nativ(0.0011)|
|3|13|0.23409|kotlin(0.1101) code(0.0758) android(0.0581) error(0.0556) java(0.0422) work(0.0214) studio(0.0199) convert(0.0166) class(0.0155) app(0.0150)|android studio(0.0200) android kotlin(0.0126) kotlin android(0.0107) kotlin code(0.0086) kotlin java(0.0069)|
|4|4|0.19012|class(0.0738) kotlin(0.0588) method(0.0366) function(0.0301) type(0.0265) object(0.0243) call(0.0220) error(0.0167) java(0.0160) paramet(0.0142)|extens function(0.0034) companion object(0.0032) kotlin class(0.0028) class kotlin(0.0026) class extend(0.0023)|
|2|10|0.13145|android(0.0523) kotlin(0.0514) gradl(0.0466) build(0.0443) project(0.0435) error(0.0351) studio(0.0282) file(0.0245) version(0.0189) app(0.0161)|android studio(0.0163) build gradl(0.0094) unresolv refer(0.0037) gradl file(0.0037) kotlin android(0.0034)|
|5|7|0.10986|view(0.0373) button(0.0362) layout(0.0331) set(0.0218) xml(0.0212) chang(0.0211) custom(0.0186) text(0.0182) click(0.0180) color(0.0144)|custom view(0.0059) button click(0.0046) click button(0.0041) radio button(0.0036) text color(0.0027)|
|7|11|0.0956|fragment(0.1055) activ(0.1034) app(0.0242) navig(0.0226) main(0.0192) call(0.0165) button(0.0162) click(0.0154) back(0.0149) mainact(0.0145)|main activ(0.0120) activ fragment(0.0089) fragment activ(0.0067) fragment fragment(0.0061) activ activ(0.0047)|
|9|5|0.09439|valu(0.0560) string(0.0423) kotlin(0.0264) list(0.0216) code(0.0208) arrai(0.0162) return(0.0153) time(0.0148) number(0.0140) date(0.0132)|return valu(0.0034) string valu(0.0030) string string(0.0026) mutabl list(0.0023) string arrai(0.0023)|
|6|1|0.08868|app(0.0472) android(0.0306) devic(0.0230) work(0.0219) user(0.0210) servic(0.0195) connect(0.0148) code(0.0145) googl(0.0143) notif(0.0136)|googl map(0.0055) android app(0.0039) broadcast receiv(0.0031) work fine(0.0023) googl plai(0.0021)|
|8|6|0.08586|call(0.0575) observ(0.0320) return(0.0235) rxjava(0.0233) thread(0.0219) method(0.0193) data(0.0191) coroutin(0.0167) function(0.0139) time(0.0127)|main thread(0.0026) method call(0.0026) network call(0.0026) api call(0.0026) live data(0.0023)|
|10|3|0.07408|item(0.0715) recyclerview(0.0570) view(0.0536) adapt(0.0501) list(0.0415) data(0.0209) scroll(0.0197) layout(0.0166) recycl(0.0164) click(0.0162)|recycl view(0.0156) recyclerview adapt(0.012) list item(0.0085) list view(0.0053) data bind(0.0049)|
|11|0|0.06363|data(0.0558) json(0.0436) retrofit(0.0379) api(0.0364) class(0.0306) respons(0.0283) object(0.0280) request(0.0266) gson(0.0188) pars(0.0187)|data class(0.0097) pars json(0.0067) json data(0.0041) json object(0.0033) api call(0.0033)|
|13|2|0.05532|imag(0.0810) file(0.0763) load(0.0236) code(0.0218) download(0.0193) url(0.0173) android(0.0162) app(0.0160) upload(0.0153) camera(0.0153)|load imag(0.0078) download file(0.0068) upload file(0.0058) upload imag(0.0058) imag imag(0.0043)|
|12|12|0.05353|databas(0.0615) room(0.0456) firebas(0.0451) data(0.0423) android(0.0360) queri(0.0280) java(0.0277) tabl(0.0212) realm(0.0179) insert(0.0168)|room databas(0.0287) firebas databas(0.018) room persist(0.0089) android room(0.0074) firebas firestor(0.0069)|
|14|8|0.04669|dagger(0.0650) inject(0.0494) viewmodel(0.0391) class(0.0368) modul(0.0328) compon(0.0305) provid(0.0259) activ(0.0254) bind(0.0227) depend(0.0214)|architectur compon(0.0192) data bind(0.0161) dagger inject(0.0119) dagger compon(0.0067) dagger modul(0.0041)|
|15|9|0.02913|test(0.2208) run(0.0505) unit(0.0419) mock(0.0383) class(0.0337) method(0.0234) android(0.0197) mockito(0.0194) call(0.0191) espresso(0.0134)|unit test(0.0777) run test(0.0264) instrument test(0.0148) espresso test(0.0115) test test(0.0082)|
