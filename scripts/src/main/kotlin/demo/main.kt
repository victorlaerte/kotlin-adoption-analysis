package demo

import org.jsoup.Jsoup
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
//  removeHtml()
//  removeURLs()
//	removePunctuation()
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
