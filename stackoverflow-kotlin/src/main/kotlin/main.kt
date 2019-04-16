import org.jsoup.Jsoup
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

fun main() {
	removeHtml()
	removeURLs()
	removePunctuation()
	removeStopWordsAndOneLetterWords()
	stem()
	nameAndLabel()
//	getQuestionSample()
}

fun getReadFile(fileName: String): File = File(fileName)
fun getOrCreateWriteFile(fileName: String): File {
	val file = File(fileName)
	if (!file.exists()) {
		file.createNewFile()
	}
	return file
}

fun removeHtml() {
	val readFile = getReadFile("kotlin-posts-1.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()))
		val newFile = getOrCreateWriteFile("kotlin-posts-2.csv")

		lines.forEach {
			val text = Jsoup.parse(it).text()

			val newText = text.replace("\n", "").replace("\r", "")
			newFile.appendText("$newText\n")
		}
	}
}

fun removeURLs() {

	val readFile = getReadFile("kotlin-posts-2.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()))
		val newFile = getOrCreateWriteFile("kotlin-posts-3.csv")
		val regex = Regex(
			"((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?+-=\\\\.&]*)")

		lines.forEach {
			val text = it.replace(regex, "")
			newFile.appendText("$text\n")
		}
	}
}

fun removePunctuation() {
	val readFile = getReadFile("kotlin-posts-3.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()))
		val newFile = getOrCreateWriteFile("kotlin-posts-4.csv")
		val regex = Regex("[\\p{P}&&[^\u0027]]")
		lines.forEach {
			val text = it.replace(regex, " ")
			newFile.appendText("$text\n")
		}
	}
}

fun removeStopWordsAndOneLetterWords() {
	val readFile = getReadFile("kotlin-posts-4.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()))
		val newFile = getOrCreateWriteFile("kotlin-posts-5.csv")

		val stopWords = listOf("a", "about", "above", "after", "again",
			"against", "all", "am", "an", "and", "any", "are", "aren't", "aren`t", "arent", "as",
			"at", "be", "because", "been", "before", "being", "below",
			"between", "both", "but", "by", "can't", "can`t", "cant", "cannot", "could",
			"couldn't", "couldn`t", "couldnt", "did", "didn't", "didn`t", "didnt", "do", "does", "doesn't", "doesn`t", "doesnt", "doing",
			"don't", "don`t", "dont", "down", "during", "each", "few", "for", "from", "further",
			"had", "hadn't", "hadn`t", "hadnt", "has", "hasn't", "hasn`t", "hasnt", "have", "haven't", "haven`t", "havent","having", "he",
			"he'd", "he`d", "he'll", "he`ll", "he's", "he`s", "hes", "her", "here", "here's", "here`s", "hers", "herself",
			"him", "himself", "his", "how", "how's", "how`s", "hows","i", "i'd", "i`d", "id", "i'll", "i`ll", "ill", "i'm", "i`m", "im",
			"i've", "i`ve", "ive", "if", "in", "into", "is", "isn't", "isn`t", "isnt", "it", "it's", "it`s", "its",
			"itself", "let's", "let`s", "lets", "me", "more", "most", "mustn't", "mustn`t", "my", "myself",
			"no", "nor", "not", "of", "off", "on", "once", "only", "or",
			"other", "ought", "our", "ours	ourselves", "out", "over", "own",
			"same", "shan't", "shan`t", "she", "she'd", "she`d", "she'll", "she`ll", "she's", "she`s", "shes", "should",
			"shouldn't", "shouldn`t", "shouldnt", "so", "some", "such", "than", "that", "that's", "that`s", "thats", "the",
			"their", "theirs", "them", "themselves", "then", "there", "there's", "there`s", "theres",
			"these", "they", "they'd", "they`d", "they'll", "they`ll", "they're", "they`re", "they've", "they`ve", "this",
			"those", "through", "to", "too", "under", "until", "up", "us",
			"very", "was", "wasn't", "wasn`t", "wasnt", "we", "we'd", "we`d", "we'll", "we`ll", "we're", "we`re", "we've", "we`ve",
			"were", "weren't", "weren`t", "what", "what's", "what`s", "whats", "when", "when's", "when`s", "where",
			"where's", "where`s", "wheres", "which", "while", "who", "who's", "who`s", "whos", "whom", "why", "why's", "why`s",
			"with", "won't", "won`t", "wont", "would", "wouldn't", "wouldn`t", "wouldnt", "you", "you'd", "you`d", "you'll", "you`ll",
			"you're", "you`re", "you've", "you`ve", "your", "yours", "yourself", "yourselves")

		lines.forEach { line ->
			val text = line.toLowerCase()
			val words = text.split(" ").toMutableList()
			val newLine = StringBuffer()

			words.forEach { word ->
				if (word.length > 1 && !stopWords.contains(word)) {
					newLine.append(word)
					newLine.append(" ")
				}
			}

			newFile.appendText("$newLine\n")
		}
	}
}

fun stem() {
	val readFile = getReadFile("kotlin-posts-5.csv")

	if (readFile.exists()) {

		val output = getOrCreateWriteFile("kotlin-posts-6.csv")
		Stemmer.stem(readFile.absolutePath, output.absolutePath)
	}
}

fun nameAndLabel() {
	val readFile = getReadFile("kotlin-posts-6.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()), StandardCharsets.ISO_8859_1)
		val newFile = getOrCreateWriteFile("kotlin-posts-final.txt")

		var i = 0
		lines.forEach {
			val myString = it.prependIndent("q$i Question$i ").toByteArray(Charsets.ISO_8859_1)
			val text = String(myString, Charsets.UTF_8)
			newFile.appendText("$text\n")
			i++
		}
	}
}

fun getQuestionSample() {
	val readFile = getReadFile("kotlin-posts-2.csv")

	if (readFile.exists()) {
		val questionLines = Files.lines(Paths.get(readFile.toURI()))

		val questionsList = questionLines.toList()

		for (i in 0 until 15) {
			val topicFile = getReadFile("t$i.txt")

			val newFile = getOrCreateWriteFile("t$i-final.txt")

			if (topicFile.exists()) {
				val lines = Files.lines(Paths.get(topicFile.toURI()))

				lines.forEach { line ->
					//Lines were saved with 0 index: +1 is needed
					val str = line.substring(0, line.indexOf("\t"))
					val questionIndex = Integer.valueOf(str)

					val newLine = "$line ${questionsList[questionIndex]}"
					newFile.appendText("$newLine\n")

					println("${questionIndex + 1}\t${questionsList[questionIndex]}")
					println("${line+1}\t${questionsList[questionIndex]}")
				}
			}
		}
	}
}