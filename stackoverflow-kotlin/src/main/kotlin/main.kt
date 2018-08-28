import org.jsoup.Jsoup
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
	removeHtml()
	removeURLs()
	removePunctuation()
	removeStopWordsAndOneLetterWords()
	stem()
	nameAndLabel()
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
		val regex = Regex("\\p{P}")
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
			"against", "all", "am", "an", "and", "any", "are", "aren't", "as",
			"at", "be", "because", "been", "before", "being", "below",
			"between", "both", "but", "by", "can't", "cannot", "could",
			"couldn't", "did", "didn't", "do", "does", "doesn't", "doing",
			"don't", "down", "during", "each", "few", "for", "from", "further",
			"had", "hadn't", "has", "hasn't", "have", "haven't", "having", "he",
			"he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself",
			"him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm",
			"i've", "if", "in", "into", "is", "isn't", "it", "it's", "its",
			"itself", "let's", "me", "more", "most", "mustn't", "my", "myself",
			"no", "nor", "not", "of", "off", "on", "once", "only", "or",
			"other", "ought", "our", "ours	ourselves", "out", "over", "own",
			"same", "shan't", "she", "she'd", "she'll", "she's", "should",
			"shouldn't", "so", "some", "such", "than", "that", "that's", "the",
			"their", "theirs", "them", "themselves", "then", "there", "there's",
			"these", "they", "they'd", "they'll", "they're", "they've", "this",
			"those", "through", "to", "too", "under", "until", "up", "very",
			"was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were",
			"weren't", "what", "what's", "when", "when's", "where", "where's",
			"which", "while", "who", "who's", "whom", "why", "why's", "with",
			"won't", "would", "wouldn't", "you", "you'd", "you'll", "you're",
			"you've", "your", "yours", "yourself", "yourselves")

		lines.forEach { line ->
			val text = line.toLowerCase()
			val words = text.split(" ").toMutableList()
			val newLine = StringBuffer()

			words.forEach { word ->
				if (!stopWords.contains(word) && word.length > 1) {
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
	val readFile = getReadFile("kotlin-posts-5.csv")

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