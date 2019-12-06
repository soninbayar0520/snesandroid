import java.io.EOFException;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class maintest {

	public static void main(String[] args) throws TesseractException {

		String img2 = "src/main/2.jpg";
		String img3 = "src/main/3.jpg";
		String img4 = "src/main/4.jpg";
		String img5 = "src/main/5.jpeg";

		// System.out.println(StringClean(findTotal(img1)));
		// System.out.println(StringClean(findTotal(img2)));
		// System.out.println(StringClean(findTotal(img3)));
		// System.out.println(StringClean(findTotal(img4)));
		// System.out.println(StringClean(findTotal(img5)));
		// findDate(img3);
		// findDate(img2);
		findDate(img3);
		findDate(img4);
		findDate(img5);
		// System.out.println(findTotal(img2));
		// System.out.println(StringClean(findTotal(img3)));
		// System.out.println(findTotal(img4));
		// System.out.println(result);

		// RegexChecker("\\s[Tt][Oo][TtFf][Aa][Ll]\\s\\d{1,9}[,.]\\d{1,9}",result);
		// RegexChecker("\\s[Tt][Oo][TtFf][Aa][Ll]\\s?[:]?\\s?[A-Za-z]{0,4}?",result);
		// RegexChecker("\\s[Tt][Oo][TtFf][Aa][Ll]\\s?[:]?\\s?[$]?\\s?\\d{1,9}[,.]\\d{1,9}",result);
		// perfect

		// FindTotal(result);
		// readSubTotal(result);
		// System.out.print(imageFile.getAbsolutePath());
	}

	public static String findDate(String fileUrl) {
		String resultTotal;
		String resultDate;
		String RawString;
		String regexTotal = "\\s[Tt][Oo][TtFf][Aa][Ll][.]?\\s?[:]?\\s?[$]?\\s?\\d{1,9}[,.]\\d{1,9}";
		// String
		// regexDate="\\s?[0-9]{2,4}[/][0-9]{2,4}[/][0-9]{2,4}\\s?([0-9]{2}[:][0-9]{2})?([:][0-9]{2})?\\s?";
		// String
		// regexDate0="(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}):(\\d{2,4}))|(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}\\s[A-Za-z]{2}))|(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})";
		// String
		//String regexDate1="(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}):(\\d{2,4}))|(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}\\s[A-Za-z]{2}))";
		String regexDate1="(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}):(\\d{2,4}))|(\\d{2,4})[/](\\d{2,4})[/](\\d{2,4})(\\s(\\d{2,4}):(\\d{2,4}\\s[A-Za-z]{2}))";
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("src/main/tessdata");
		tesseract.setLanguage("eng");
		try {
			File imageFile = new File(fileUrl);
			long start = System.currentTimeMillis();
			RawString = tesseract.doOCR(imageFile);
			// System.out.println(RawString);
			resultTotal = RegexTotal(regexTotal, RawString);
			resultDate = RegexTotal(regexDate1, RawString);
			System.out.println(resultTotal);
			System.out.println("DATE: " + resultDate);
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			// System.out.println(RawString);
			System.out.println("Total elapsed sec: " + timeElapsed / 1000);
			return resultTotal;
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static double StringClean(String Total) {

		try {
			Total = Total.replaceAll("[A-Za-z]{5}[:]?[.]?\\s", "").replace("$", "");
			// System.out.println(Total);
			return Double.parseDouble(Total);
		} catch (Exception ex) {
			System.out.println("String to double parse error");
			return 0.0;
		}
	}

	public static String findTotal(String fileUrl) {
		String result = "";
		String RawString;
		String regexTotal = "\\s[Tt][Oo][TtFf][Aa][Ll][.]?\\s?[:]?\\s?[$]?\\s?\\d{1,9}[,.]\\d{1,9}";
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("src/main/tessdata");
		tesseract.setLanguage("eng");
		try {
			File imageFile = new File(fileUrl);
			long start = System.currentTimeMillis();
			RawString = tesseract.doOCR(imageFile);
			result = RegexTotal(regexTotal, RawString);
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			// System.out.println(RawString);
			System.out.println("Total elapsed sec: " + timeElapsed / 1000);
			return result;
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static String RegexTotal(String Regex, String CheckString) {
		String result = "";
		Pattern CheckRegex = Pattern.compile(Regex);
		Matcher RegexMatcher = CheckRegex.matcher(CheckString);
		while (RegexMatcher.find()) {
			if (RegexMatcher.group().length() != 0) {
				// System.out.println(RegexMatcher.group().trim());
				result = RegexMatcher.group().trim();
			}
		}
		return result;
	}

}
