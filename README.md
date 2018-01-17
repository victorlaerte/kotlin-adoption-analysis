# kotlin-stackoverflow-data-analysis

## First Step 
- **[kotlin-posts-0.csv](/kotlin-posts-0.csv) - First extraction getting all posts with tag kotlin, excluding that one releated with js or javascript. - DONE**
- **[kotlin-posts-1.csv](/kotlin-posts-1.csv) - Remove content inside the tags ```<code>, <pre> and <blockquotes>``` - DONE**
- **[kotlin-posts-2.csv](/kotlin-posts-2.csv) - Remove html tags - DONE**
- **[kotlin-posts-3.csv](/kotlin-posts-3.csv) - Remove URL's - DONE**
- **[kotlin-posts-4.csv](/kotlin-posts-4.csv) - Remove punctuation - DONE**
- **[kotlin-posts-5.csv](/kotlin-posts-5.csv) - Remove stop words and one-letter words- DONE**
- **[kotlin-posts-6.csv](/kotlin-posts-6.csv) - Apply Porter Stemming Algorithm - [Porter Stemming Repo](https://github.com/victorlaerte/java-porter-stemming) - DONE**

## Second step
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

## Third step

After some [literature review](/documents/SteyversGriffithsLSABookFormatted.pdf) we decided to use the file [test-3](/mallet-output/test-3) in the remaining of our research, mainly because each line in [kotlin-posts-6.csv](/kotlin-posts-6.csv) is an Stack Overflow question and can be seen as a instance of mallet. [You can see more in mallet docs](http://mallet.cs.umass.edu/import.php).

> MALLET represents data as lists of "instances". All MALLET instances include a data object. An instance can also include a name and (in classification contexts) a label. For example, if the application is guessing the language of web pages, an instance might consist of a vector of word counts (data), the URL of the page (name) and the language of the page (label).

> There are two primary methods for importing data into MALLET format, first when the source data consists of many separate files, and second when the data is contained in a single file, with one instance per line.

> In this case, the first token of each line (whitespace delimited, with optional comma) becomes the instance name, the second token becomes the label, and all additional text on the line is interpreted as a sequence of word tokens. Note that the data in this case will be a vector of feature/value pairs, such that a feature consists of a distinct word type and the value is the number of times that word occurs in the text.