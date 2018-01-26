# kotlin-stackoverflow-data-analysis

## First Step
- **[kotlin-posts-0.csv](/kotlin-posts-0.csv) - First extraction getting all posts with tag kotlin, excluding that one releated with js or javascript. - DONE**
- **[kotlin-posts-1.csv](/kotlin-posts-1.csv) - Remove content inside the tags ```<code>, <pre> and <blockquotes>``` - DONE**
- **[kotlin-posts-2.csv](/kotlin-posts-2.csv) - Remove html tags - DONE**
- **[kotlin-posts-3.csv](/kotlin-posts-3.csv) - Remove URL's - DONE**
- **[kotlin-posts-4.csv](/kotlin-posts-4.csv) - Remove punctuation - DONE**
- **[kotlin-posts-5.csv](/kotlin-posts-5.csv) - Remove stop words and one-letter words- DONE**
- **[kotlin-posts-6.csv](/kotlin-posts-6.csv) - Apply Porter Stemming Algorithm - [Porter Stemming Repo](https://github.com/victorlaerte/java-porter-stemming) - DONE**

## Second Step
- **[output.mallet](/output.mallet)** - File generated with following command: 
```
// For test-1 and test-2
mallet import-dir --input mallet-input --output output.mallet --keep-sequence --remove-stopwords
```
```
//For test-3 and test-4
mallet import-file --input mallet-input/kotlin-posts-i.txt --output output.mallet --keep-sequence --remove-stopwords
```
- **[mallet-output](/mallet-output)** - In this directory you can find four test rounds. ```test-1``` and ```test-3``` one was done with the [output.mallet](/output.mallet) generated with [kotlin-posts-6.csv](/kotlin-posts-6.csv). ```test-2``` and ```test-4``` was generated with [kotlin-posts-5.csv](/kotlin-posts-5.csv).
- **[test-1](/mallet-output/test-1)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```
- **[test-2](/mallet-output/test-2)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```
- **[test-3](/mallet-output/test-3)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```
- **[test-4](/mallet-output/test-4)** - In this directory you can find tests for i-topics respectively generated with following command:
```
mallet train-topics --input output.mallet --num-topics i --optimize-interval 20 --output-state topic-state-i.gz --output-topic-keys output-keys-i-topics.txt --output-doc-topics output-composition-i-topics.txt
```

## Third Step

After some [literature review](/documents/SteyversGriffithsLSABookFormatted.pdf) we decided to use the file [test-3](/mallet-output/test-3) in the remaining of our research, mainly because each line in [kotlin-posts-6.csv](/kotlin-posts-6.csv) is an Stack Overflow question and can be seen as a instance of mallet. [You can see more in mallet docs](http://mallet.cs.umass.edu/import.php).

> MALLET represents data as lists of "instances". All MALLET instances include a data object. An instance can also include a name and (in classification contexts) a label. For example, if the application is guessing the language of web pages, an instance might consist of a vector of word counts (data), the URL of the page (name) and the language of the page (label).

> There are two primary methods for importing data into MALLET format, first when the source data consists of many separate files, and second when the data is contained in a single file, with one instance per line.

> In this case, the first token of each line (whitespace delimited, with optional comma) becomes the instance name, the second token becomes the label, and all additional text on the line is interpreted as a sequence of word tokens. Note that the data in this case will be a vector of feature/value pairs, such that a feature consists of a distinct word type and the value is the number of times that word occurs in the text.

## Fourth Step

We have chosen 15 topics based on some [literature review](http://psiexp.ss.uci.edu/research/papers/SteyversGriffithsLSABookFormatted.pdf), and because in our analysis with more than 15 topics, there were many topics with too low weight. The files were duplicated in folder [final-topics](/final-topics)

### Topics

| Topics | Weight | Words |
|---|---|---|
| 4 | 0.39689 | kotlin code work exampl question someth make differ java solut time answer find write implement realli read languag |
| 5 | 0.24295 | kotlin type class error java gener code compil paramet interfac implement follow constructor method work function call problem cast lambda |
| 2 | 0.24089 | kotlin project android file error gradl build run java studio work class compil code plugin intellij version app modul librari |
| 3 | 0.1804 | function kotlin class method java extens call object code access refer creat instanc static exampl pass work paramet companion |
| 8 | 0.15882 | properti kotlin valu null class field initi wai variabl nullabl return setter deleg check default declar set call object code |
| 9 | 0.15596 | code activ android call work fragment data observ item view app rxjava problem button user anko return method request click |
| 1 | 0.10448 | kotlin list string arrai oper map valu element collect defin fun convert int iter java function number return |
| 6 | 0.0916 | test class annot spring data kotlin inject dagger work model applic unit mock object boot problem creat follow run fail |
| 13 | 0.02668 | databas app room tokenhelp foo queri bar tabl firebas helper date getinst companion persist entiti sql error insert kapt exampl |
| 7 | 0.02615 | android java view app layout activitythread support widget content lang wrap main width height activ method textview viewgroup linearlayout zygoteinit |
| 0 | 0.01811 | android version kotlin compil jar plugin gradl depend build support googl lib annot maven artifactid appli groupid idea dagger |
| 10 | 0.01164 | val fun var privat overrid int return context event color step button size textur class super bundl null set bigdecim |
| 11 | 0.00737 | java releas jar spring springframework core web junit servlet invok method reflect main apach coroutin tomcat sun intern server |
| 14 | 0.00619 | java gradl execut intern task api jetbrain kotlin tool invok compil executeactionstaskexecut android run sun defaultgradlelaunch reflect lang project |
| 12 | 0.0037 | java jetbrain kotlin intern lang jet resolv expressioncodegen util call info thread codegen concurr servic callresolv jvm subscriptionlist lazi |

### Topic's Naming Draft

| Topics | Possible Topics | Possible Category |
|---|---|---|
| 4 | General Errors - Language Examples | Basics |
| 5 | Type Checks and Cast - Functions - Lambdas | Classes and Objects - Functions and Lambdas - Java Interop |
| 2 | Android Studio - Kotlin Plugins | Tools - Java Interop |
| 3 | Extensions - Language Examples - Java Interop | Classes and Objects - Java Interop - Basics |
| 8 | Type Checks and Cast - Null Safety - Properties and Fields | Classes and Objects - Other |
| 9 | Libraries - UI Actions | Tools - Android Platform |
| 1 | Basic Types - Collection - Java Interop | Basic - Other - Java Interop |
| 6 | Tests - IoC/DI | Tests |
| 13 | Data Storage - Kotlin Annotation Processor | Tools - Android Platform |
| 7 | UI Components - Widgets | Android Platform |
| 0 | Gradle - Maven - Build/Dependencies Tools - Third Libraries | Tools |
| 10 | Basic Syntax - UI Components - Classes and Inheritance | Basics - Android Platform - Classes and Objects |
| 11 | Coroutines - Java Interop | Concurrence - Functions and Lambdas - Java Interop |
| 14 | Multithread/Schedulers - Java Interop | Concurrence - Java Interop |
| 12 | Delegated Properties - Concurrence - Java Interop | Classes and Objects - Java Interop - Concurrence |
