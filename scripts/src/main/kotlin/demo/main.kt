package demo

import org.jsoup.Jsoup
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
//  removeHtml()
//  removeURLs()
//	removePunctuation()
	removeStopWordsAndOneLetterWords()
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
			var text = Jsoup.parse(it).text()

			text = text.replace("\n", "")
			newFile.appendText("$text\n")
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
			var text = it.replace(regex, "")
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
			var text = it.replace(regex, " ")
			newFile.appendText("$text\n")
		}
	}
}

fun removeStopWordsAndOneLetterWords() {
	val readFile = getReadFile("kotlin-posts-4.csv")

	if (readFile.exists()) {
		val lines = Files.lines(Paths.get(readFile.toURI()))
		val newFile = getOrCreateWriteFile("kotlin-posts-5.csv")
		val stopWords = listOf("a","about","above","after","again","against","all","am","an","and","any","are","aren't","as","at","be","because","been","before","being","below","between","both","but","by","can't","cannot","could","couldn't","did","didn't","do","does","doesn't","doing","don't","down","during","each","few","for","from","further","had","hadn't","has","hasn't","have","haven't","having","he","he'd","he'll","he's","her","here","here's","hers","herself","him","himself","his","how","how's","i","i'd","i'll","i'm","i've","if","in","into","is","isn't","it","it's","its","itself","let's","me","more","most","mustn't","my","myself","no","nor","not","of","off","on","once","only","or","other","ought","our","ours	ourselves","out","over","own","same","shan't","she","she'd","she'll","she's","should","shouldn't","so","some","such","than","that","that's","the","their","theirs","them","themselves","then","there","there's","these","they","they'd","they'll","they're","they've","this","those","through","to","too","under","until","up","very","was","wasn't","we","we'd","we'll","we're","we've","were","weren't","what","what's","when","when's","where","where's","which","while","who","who's","whom","why","why's","with","won't","would","wouldn't","you","you'd","you'll","you're","you've","your","yours","yourself","yourselves")

		lines.forEach {
			var text = it.toLowerCase()
			var words = text.split(" ").toMutableList()
			var newLine = StringBuffer()

			words.forEach {
				if (!stopWords.contains(it) && it.length > 1) {
					newLine.append(it)
					newLine.append(" ")
				}
			}

			newFile.appendText("$newLine\n")
		}
	}
}