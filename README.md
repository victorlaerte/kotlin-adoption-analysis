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
| 1 | 0.46881 | work code android kotlin wai make don question tri someth time exampl problem follow app creat solut doesn find issu |
| 9 | 0.20464 | android kotlin build project gradl error studio file version app librari modul compil run plugin follow fail class work updat |
| 4 | 0.1689 | kotlin code java android error convert studio class file app work string lang project languag method exampl follow activ line |
| 14 | 0.15408 | class kotlin type error method gener paramet object implement creat interfac constructor java pass follow call function code problem parcel |
| 11 | 0.15254 | kotlin function valu null properti variabl class call method return object access wai code extens initi field type declar android |
| 13 | 0.10564 | fragment activ view layout xml anko chang main work navig set bind android tab custom menu bar code insid mainact |
| 8 | 0.10203 | activ button click user app code edittext show text dialog set press back login android firebas open start work select |
| 3 | 0.09935 | data list json object string arrai valu class realm pars firebas gson kotlin field model return add kei arraylist databas |
| 10 | 0.09868 | view item recyclerview imag adapt list anim scroll load chang set layout color code click show work select screen problem |
| 0 | 0.09223 | call thread method run function time coroutin log code event work callback activ block execut start android insid problem complet |
| 2 | 0.08641 | app file android devic code work servic imag connect googl locat permiss applic notif send plai creat receiv save api |
| 7 | 0.08431 | data call observ request api rxjava retrofit return respons method error model server fetch subscrib result updat servic local viewmodel |
| 12 | 0.03947 | dagger inject modul provid compon viewmodel depend activ class error present implement applic instanc app annot architectur work problem mvp |
| 6 | 0.0329 | test run unit mock class mockito android method espresso fail instrument present coverag function junit follow simpl code write androidtest |
| 5 | 0.02865 | room databas execut java gradl org queri tabl entiti insert intern dao sqlite persist task api data error column record |

### Topics Classification

| Topics | Weight | Topic | Possible Categories | Words | Notes |
|---|---|---|---|---|---|
| 1 | 0.46881 | Error - General | General Problems | work code android kotlin wai make don question tri someth time exampl problem follow app creat solut doesn find issu | No Notes |
| 9 | 0.20464 | Compilation/Build | Android Development Environment Tools | android kotlin build project gradl error studio file version app librari modul compil run plugin follow fail class work updat | Kapt errors, Android Studio version, Plugin versions, Gradle Plugin  |
| 4 | 0.1689 | Code Convertion/Java Interop. | Kotlin Syntax | kotlin code java android error convert studio class file app work string lang project languag method exampl follow activ line | Android Studio Code converter, Chunck of Java codes in Kotlin Syntax, Better Kotlin Syntax |
| 14 | 0.15408 | Classes and Objects | Kotlin Syntax | Class and inheritance, Generics, Constructors and functions | class kotlin type error method gener paramet object implement creat interfac constructor java pass follow call function code problem parcel |
| 11 | 0.15254 | Class Members (Properties and Functions) | Kotlin Syntax | Properties Initialization, Setters and getters, Optionals, Lambdas Highorder Functions | kotlin function valu null properti variabl class call method return object access wai code extens initi field type declar android |
| 13 | 0.10564 | View Layouts | User Interface |fragment activ view layout xml anko chang main work navig set bind android tab custom menu bar code insid mainact | Fragments, Anko |
| 8 | 0.10203 | User Interaction (UI Actions) | User Interface | activ button click user app code edittext show text dialog set press back login android firebas open start work select |Alert Dialogs, Buttons and EditTexts, show or hide inputs, enable or disable interaction, start or close new activities |
| 3 | 0.09935 | Parsing and Object Transformations | Classes and Objects | data list json object string arrai valu class realm pars firebas gson kotlin field model return add kei arraylist databas | Gson, JSON, Lists and Arrays, Serialization and Deserialization, Realm |
| 10 | 0.09868 | RecyclerView  | User Interface | view item recyclerview imag adapt list anim scroll load chang set layout color code click show work select screen problem | No Notes |
| 0 | 0.09223 | Multithread/Coroutines | ?? | call thread method run function time coroutin log code event work callback activ block execut start android insid problem complet | coroutines, rxjava, anko, threads |
| 2 | 0.08641 | Multimedia Handling | input/output? | app file android devic code work servic imag connect googl locat permiss applic notif send plai creat receiv save api | Camera, media, file explorer, open urls, maps |
| 7 | 0.08431 | Remote Requests?/Cloud? | Networking?? | data call observ request api rxjava retrofit return respons method error model server fetch subscrib result updat servic local viewmodel | Data Requests |
| 12 | 0.03947 | Dagger | Dependency Injection | dagger inject modul provid compon viewmodel depend activ class error present implement applic instanc app annot architectur work problem mvp |
| 6 | 0.0329 | Test Frameworks | Testing | test run unit mock class mockito android method espresso fail instrument present coverag function junit follow simpl code write androidtest | ***Mockito***, Junit, espresso, roboletric |
| 5 | 0.02865 | SQLite/Room | Persistence |room databas execut java gradl org queri tabl entiti insert intern dao sqlite persist task api data error column record | No Notes |
