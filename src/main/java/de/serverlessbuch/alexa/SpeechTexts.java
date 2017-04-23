package de.serverlessbuch.alexa;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
class SpeechTexts {
    private SpeechTexts() {}

    static String welcomeText() {
        return "Das Serverless Computing Buch von Niko Köbler erscheint voraussichtlich im Herbst 2017 im Verlag Entwickler Press. "
                + "Die Website zum Buch findest Du unter http://serverlessbuch.de ";
    }

    static String endText() {
        return "Auf Wiedersehen!";
    }

    static String chapterText() {
        return "Das Serverless Computing Buch besteht aus folgenden Kapiteln: "
                + "1. Serverless Computing, 2. AWS Lambda, 3. Amazon API Gateway, 4. Amazon DynamoDB, 5. Beispiel Apps";
    }

    static String authorText() {
        return "Niko Köbler macht irgendwas mit Computern, oft in der Cloud, meist auf der Java Virtual Machine. "
                + "Er ist Co-Lead der Java User Group Darmstadt, Autor und Sprecher auf internationalen Konferenzen. "
                + "Niko tweetet unter @dasniko";
    }

    static String helpText() {
        return "Frage mich nach den Kapiteln oder dem Autor.";
    }

    static String sorryText() {
        return "Entschuldigung, ich habe Dich nicht vertanden.";
    }
}
