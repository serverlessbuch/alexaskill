package de.serverlessbuch.alexa;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.Collections;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
public class MySpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final String APP_ID = "amzn1.ask.skill.81442cf4-3a76-419d-9149-c33e3567d94e";

    public MySpeechletRequestStreamHandler() {
        super(new MySpeechlet(), Collections.singleton(APP_ID));
    }
}
