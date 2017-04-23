package de.serverlessbuch.alexa;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
public class MySpeechlet implements Speechlet {

    private static final Logger log = LoggerFactory.getLogger(MySpeechlet.class);

    private static final String INTENT_AUTHOR = "Author";
    private static final String INTENT_CHAPTER = "Chapter";
    private static final String INTENT_HELP = "AMAZON.HelpIntent";
    private static final String INTENT_STOP = "AMAZON.StopIntent";

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
        log.info("User {} - {}", session.getUser().getUserId(), session.getUser().getAccessToken());
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
        return speechletAskResponse(SpeechTexts.welcomeText());
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

            Intent intent = request.getIntent();
            if (null != intent) {
                log.info("intent: {}", intent.getName());
                intent.getSlots().forEach((k, v) -> log.info("slot {}: {} = {}", k, v.getName(), v.getValue()));
            } else {
                log.warn("no intent");
            }
            String intentName = intent.getName();

            switch (intentName) {
                case INTENT_AUTHOR:
                    return speechletAskResponse(SpeechTexts.authorText());
                case INTENT_CHAPTER:
                    return speechletAskResponse(SpeechTexts.chapterText());
                case INTENT_HELP:
                    return speechletAskResponse(SpeechTexts.helpText());
                case INTENT_STOP:
                    return speechletTellResponse(SpeechTexts.endText());
                default:
                    throw new SpeechletException("Invalid Intent " + intentName);
            }
    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
    }

    private SpeechletResponse speechletTellResponse(String text) {
        SimpleCard card = createCard(text);
        PlainTextOutputSpeech speech = createSpeech(text);
        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse speechletAskResponse(String text) {
        SimpleCard card = createCard(text);
        PlainTextOutputSpeech speech = createSpeech(text);
        return SpeechletResponse.newAskResponse(speech, createReprompt(), card);
    }

    private SimpleCard createCard(String text) {
        SimpleCard card = new SimpleCard();
        card.setTitle("Serverless Buch");
        card.setContent(text);
        return card;
    }

    private PlainTextOutputSpeech createSpeech(String text) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);
        return speech;
    }

    private Reprompt createReprompt() {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(SpeechTexts.sorryText());
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);
        return reprompt;
    }
}
