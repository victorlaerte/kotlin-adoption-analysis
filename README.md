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

| Topics | Weight | Words | Top 5 Phrases |
|---|---|---|---|
|6|0.31324|test(0.03087) android(0.01847) wai(0.01767) work(0.0173) kotlin(0.01650) code(0.01584) question(0.01151) someth(0.01110) make(0.00971) exampl(0.00891)|unit test(0.0087) instrument test(0.0022) android kotlin(0.0017) junit test(0.0014) espresso test(0.0012)|
|13|0.20037|error(0.0666) code(0.0641) kotlin(0.0482) android(0.0375) work(0.0308) app(0.02572) crash(0.0230) line(0.0147) follow(0.0142) problem(0.0134)|android studio(0.0091) app crash(0.0087) work fine(0.0054) android kotlin(0.0052) kotlin android(0.0052)|
|9|0.15475|kotlin(0.0784) class(0.0682) type(0.0356) function(0.0354) method(0.0277) error(0.0272) java(0.0237) code(0.0219) gener(0.0184) paramet(0.0175)|type mismatch(0.0068) kotlin(0.0050) kotlin class(0.0048) class kotlin(0.0037) extens function(0.0028)|
|14|0.14456|android(0.0607) kotlin(0.0563) project(0.0431) build(0.0425) gradl(0.0404) studio(0.0325) error(0.0312) file(0.0251) version(0.0196) app(0.0161)|android studio(0.0218) build gradl(0.0076) unresolv refer(0.0057) kotlin android(0.0035) android kotlin(0.0034)|
|3|0.12359|view(0.0575) layout(0.0334) item(0.0331) recyclerview(0.0302) adapt(0.0237) xml(0.0171) set(0.0167) custom(0.0165) list(0.0135) chang(0.0130)|custom view(0.007) recyclerview adapt(0.0072) recycl view(0.0067) view holder(0.0022) inflat layout(0.0022)|
|8|0.11134|fragment(0.0822) activ(0.0675) button(0.0347) click(0.0278) app(0.0202) navig(0.0186) show(0.0169) main(0.0136) back(0.0134) dialog(0.0122)|main activ(0.0091) click button(0.0080) button click(0.0068) fragment activ(0.0045) fragment fragment(0.0043)|
|0|0.09814|call(0.0556) observ(0.0271) return(0.0229) rxjava(0.0226) data(0.0215) request(0.0210) api(0.0184) method(0.0180) thread(0.0167) retrofit(0.0152)|api call(0.0036) rxjava retrofit(0.0033) network call(0.0024) http request(0.0021) call call(0.0021)|
|12|0.09293|activ(0.0520) class(0.0463) object(0.0378) method(0.0366) kotlin(0.0354) call(0.0304) null(0.0290) valu(0.0273) variabl(0.0261) access(0.0248)|companion object(0.0107) memori leak(0.0075) lateinit properti(0.0055) main activ(0.0039) static method(0.0035)|
|2|0.08466|valu(0.0420) string(0.0402) text(0.0238) code(0.0206) kotlin(0.0180) time(0.0173) number(0.0171) edittext(0.0163) chang(0.0151) date(0.0138)|string arrai(0.0027) string resourc(0.0023) kotlin android(0.0023)byte arrai(0.0023) string valu(0.0019)|
|1|0.08301|data(0.0756) list(0.0404) object(0.0373) json(0.0354) class(0.0306) kotlin(0.0184) model(.0169) pars(0.0163) retrofit(0.0158) api(0.0154)|data class(0.0091) pars json(0.0060) list object(0.0034) json data(0.0034)firebas firestor(0.0031)|
|10|0.08209|app(0.0454) android(0.0289) user(0.0226) servic(0.0214) devic(0.0213) work(0.0202) googl(0.0176) connect(0.0154) notif(0.0148) applic(0.0121)|googl map(0.0068) android app(0.0039) broadcast receiv(0.0031) googl plai(0.0019) plai music(0.0017)|
|7|0.057|imag(0.0719) file(0.0714) code(0.0275) load(0.0242) android(0.0205) url(0.0183) download(0.0182) app(0.0158) open(0.0146) camera(0.0142)|upload imag(0.0073)load imag(0.0060) download file(0.0050) csv file(0.0036) imag load(0.0032)|
|11|0.05344|kotlin(0.1682) java(0.1359) android(0.0586) code(0.0491) convert(0.0490) org(0.0191) execut(0.0183) gradl(0.0161) intern(0.0152) studio(0.0131)|java kotlin(0.0279) kotlin android(0.0213) convert java(0.0197) android studio(0.0188) kotlin java(0.0180)|
|4|0.04974|dagger(0.0643) inject(0.0473) viewmodel(0.0377) class(0.0341) bind(0.0324) compon(0.0321) modul(0.0318) data(0.0260) provid(0.0251) activ(0.0208)|data bind(0.0303) architectur compon(0.0176) dagger inject(0.0126) dagger modul(0.0055) dagger provid(0.0055)|
|5|0.03653|databas(0.0721) room(0.0627) android(0.0474) firebas(0.0418) java(0.0389) tabl(0.0285) queri(0.0251) data(0.0249) insert(0.0235) entiti(0.0206)|room databas(0.0422) firebas databas(0.0265) android room(0.0125) room persist(0.0117) sqlite databas(0.0086)|

### Topics Classification

| Topics | Weight | Topic | Possible Categories | Words | Notes |
|---|---|---|---|---|---|
|||||||
