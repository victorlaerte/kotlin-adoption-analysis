# kotlin-stackoverflow-data-analysis

## First Step - DONE
- **[kotlin-posts-0.csv](/kotlin-posts-0.csv) - First extraction getting all posts related with kotlin and Android - DONE**
- **[kotlin-posts-1.csv](/kotlin-posts-1.csv) - Remove content inside the tags ```<code>, <pre> and <blockquotes>``` - DONE**
- **[kotlin-posts-2.csv](/kotlin-posts-2.csv) - Remove html tags - DONE**
- **[kotlin-posts-3.csv](/kotlin-posts-3.csv) - Remove URL's - DONE**
- **[kotlin-posts-4.csv](/kotlin-posts-4.csv) - Remove punctuation - DONE**
- **[kotlin-posts-5.csv](/kotlin-posts-5.csv) - Remove stop words and one-letter words- DONE**
- **[kotlin-posts-6.csv](/kotlin-posts-6.csv) - Apply Porter Stemming Algorithm - [Porter Stemming Repo](https://github.com/victorlaerte/java-porter-stemming) - DONE**

## Second Step - DONE
- **[output.mallet](/output.mallet)** - File generated with following command: 
```
//For test-1 and test-2
mallet import-file --input mallet-input/kotlin-posts-i.txt --output output.mallet --keep-sequence --remove-stopwords
```
- **[mallet-output](/mallet-output)** - In this directory you can find four test rounds. ```test-1``` was generated with the [output.mallet](/output.mallet) generated with [kotlin-posts-6.csv](/kotlin-posts-6.csv). ```test-2``` was generated with [kotlin-posts-5.csv](/kotlin-posts-5.csv).
- **[test-1](/mallet-output/test-1)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```
- **[test-2](/mallet-output/test-2)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```

## Third Step - DONE

After some [literature review](/documents/SteyversGriffithsLSABookFormatted.pdf) we decided to use the file [test-2](/mallet-output/test-2) in the remaining of our research, mainly because each line in [kotlin-posts-6.csv](/kotlin-posts-6.csv) is an Stack Overflow question and can be seen as a instance of mallet. [You can see more in mallet docs](http://mallet.cs.umass.edu/import.php).

> MALLET represents data as lists of "instances". All MALLET instances include a data object. An instance can also include a name and (in classification contexts) a label. For example, if the application is guessing the language of web pages, an instance might consist of a vector of word counts (data), the URL of the page (name) and the language of the page (label).

> There are two primary methods for importing data into MALLET format, first when the source data consists of many separate files, and second when the data is contained in a single file, with one instance per line.

> In this case, the first token of each line (whitespace delimited, with optional comma) becomes the instance name, the second token becomes the label, and all additional text on the line is interpreted as a sequence of word tokens. Note that the data in this case will be a vector of feature/value pairs, such that a feature consists of a distinct word type and the value is the number of times that word occurs in the text.

## Fourth Step [DONE]

Define the number topics to be used (5, 10, 15 or 20) and name it.

We have chosen 15 topics based on some [literature review](http://psiexp.ss.uci.edu/research/papers/SteyversGriffithsLSABookFormatted.pdf), and because in our analysis with more than 15 topics, there were many topics with too low weight. The files were duplicated in folder [final-topics](/final-topics)

### Topics

| Topics | Weight | Words |
|---|---|---|
| 12 | 0.44832 | work code android wai time make app kotlin problem tri question don someth doesn follow solut exampl chang find issu |
| 0 | 0.20305 | kotlin function error type code valu null return method call paramet variabl class string pass follow java extens refer compil |
| 9 | 0.1766 | android kotlin build project gradl error studio file app run version modul test librari compil plugin fail follow work updat |
| 11 | 0.17069 | kotlin java android code class convert studio file error work creat project find write languag activ import librari wai develop |
| 4 | 0.12621 | class kotlin dagger object method inject error constructor implement creat interfac gener activ provid compon instanc properti parcel modul initi |
| 10 | 0.12181 | button click activ text chang show xml set edittext anko code select dialog item work color android user layout view |
| 8 | 0.10953 | data list object databas class json room model queri arrai string realm gson valu field pars firebas creat retrofit return |
| 7 | 0.10766 | call test observ method rxjava data return retrofit api request thread run coroutin callback result function mock execut code unit |
| 6 | 0.10342 | app android code servic devic user work connect firebas log googl applic send call activ api messag login run notif |
| 14 | 0.09193 | view recyclerview adapt layout item xml list android anim custom scroll bind code set recycl data height listview insid implement |
| 1 | 0.05982 | fragment activ navig tab main app intent code android back ingredi call layout mainact click drawer content menu viewpag uri |
| 5 | 0.05298 | file imag load android code save app url camera open download record creat folder intent glide plai content bitmap upload |
| 2 | 0.02349 | val fun string var overrid int null class return date context privat text mode valu cursor add respons savedinstancest super |
| 13 | 0.01191 | kotlin android googl org support compil jetbrain dagger version alpha room persist arch stdlib annot implement guava common jre app |
| 3 | 0.00786 | java android intern org activitythread execut gradl app main lang experi zygoteinit system task method run handler metavers api reflect |