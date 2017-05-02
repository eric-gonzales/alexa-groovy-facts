package com.ericgonzalesevans.groovyfacts

import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.LaunchRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SessionEndedRequest
import com.amazon.speech.speechlet.SessionStartedRequest
import com.amazon.speech.speechlet.Speechlet
import com.amazon.speech.speechlet.SpeechletException
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import com.amazon.speech.ui.SimpleCard

/**
 * Created by ericgonzales on 1/16/17.
 *
 * This simple sample has no external dependencies or session management, and shows the most basic
 * example of how to handle Alexa Skill requests.
 *
 */
class GroovyFactsSpeechlet implements Speechlet {

    private static final GROOVY_FACTS = [
        "Groovy is a dynamic language.",
        "Groovy features both static and dynamic typing.",
        "Groovy has an ecosystem of testing libraries and frameworks.",
        "Groovy has features similar to Python, Ruby, Perl, and Smalltalk",
        "Groovy can be used as a scripting language for the Java Platform",
        "Groovy is dynamically compiled to Java Virtual Machine bytecode",
        "Groovy can be integrated into any JVM environment",
        "Groovy is one of many languages that can run on the JVM",
        "Groovy interoperates with other Java code and libraries",
        "Most Java code is also syntactically valid Groovy",
        "Most valid Java files are also valid Groovy files.",
        "Groovy uses the safe navigation operator to check automatically for nulls.",
        "Groovy provides native support for various markup languages such as XML and HTML.",
        "A Groovy source code file can sometimes be executed as an uncompiled script.",
        "GroovyBeans are Groovy's version of JavaBeans.",
        "Groovy implicitly generates getter and setter methods.",
        "Groovy offers support for prototype extension",
        "Groovy's changes in code through prototyping are not visible in Java.",
        "Groovy's syntax permits omitting parentheses and dots in some situations.",
        "Traits in Groovy can be seen as interfaces carrying both default implementations and state.",
        "In Groovy, omitting a modifier on a field doesn’t result in a package-private field. Instead, it is used to create a property.",
        "A trait in Groovy is defined using the trait keyword.",
        "Automatic Resource Management blocks from Java 7 are not supported in Groovy.",
        "In Groovy, the methods which will be invoked are chosen at runtime. This is called runtime dispatch or multi-methods.",
        "Interfaces in Groovy are implemented by using the implements keyword.",
        "Groovy implements anonymous inner classes and nested classes in a way similar to Java.",
        "In Groovy, it is possible to create a package-private field by using a particular annotation",
        "Although Groovy is mostly an object-oriented language, it also offers functional features such as closures.",
        "Closures in Groovy work similar to a method pointer, enabling code to be written and run later in time.",
        "Strings can be interpolated with variables and expressions by using G Strings.",
        "Groovy uses Objects for almost everything, even using autowraps for primitives",
        "G Strings in Groovy containing variables and expressions must be declared using double quotes.",
        "A complex expression in a Groovy GString must be enclosed in curly brackets.",
        "A Groovy script is fully parsed, compiled, and generated before execution similar to Perl and Ruby",
        "Traits in Groovy allow composition of behaviors, runtime implementation of interfaces, and behavior overriding",
        "Groovy's closures support free variables, which have not been explicitly passed as a parameter to it.",
        "Groovy code can be more compact than Java, because it does not require all the elements that Java requires.",
        "One way Java programmers can learn Groovy gradually is by starting with familiar Java syntax.",
        "Groovy 2.4 was the last major release under Pivotal Software's sponsorship which ended in March 2015.",
        "James Strachan created the Apache Groovy programming language in 2003.",
        "Groovy won the first prize at JAX 2007 innovation award.",
        "Grails, a Groovy web framework, won the second prize at JAX 2008 innovation award.",
        "On July 2, 2012, Groovy 2.0 was released",
        "The company Pivotal sponsored Groovy and Grails until April 2015"
    ]

    void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        // any initialization logic goes here
    }

    SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return getNewFactResponse() //gives a new fact as soon as the app is launched
    }

    SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        def intent = request.getIntent()
        def intentName = (intent != null) ? intent.getName() : null //todo: elvis compare here?

        switch (intentName) {
            case "GetNewFactIntent":
                return getNewFactResponse()
                break
            case "AMAZON.HelpIntent":
                return getNewHelpResponse()
                break
            case "AMAZON.StopIntent":
                PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech()
                outputSpeech.setText("Goodbye")
                return SpeechletResponse.newTellResponse(outputSpeech)
                break
            case "AMAZON.CancelIntent":
                PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech()
                outputSpeech.setText("Goodbye")
                return SpeechletResponse.newTellResponse(outputSpeech)
                break
            default:
                throw new SpeechletException("Invalid Intent")
                break
        }
    }

    void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        // any cleanup logic goes here
    }

    private static getNewFactResponse() {
        def groovyFact = GROOVY_FACTS.sort{new Random()}?.take(1)?.first()

        // Create the Simple card content.
        def card = new SimpleCard()
        card.setTitle("GroovyFacts")
        card.setContent(groovyFact)

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech()
        speech.setText(groovyFact)

        return SpeechletResponse.newTellResponse(speech, card)
    }

    private static getNewHelpResponse() {
        def helpText = "You can ask Groovy Facts to tell me a groovy fact, or, you can say exit. What can I do for you?"
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech()
        speech.setText(helpText)

        // Create reprompt.
        Reprompt reprompt = new Reprompt()
        reprompt.setOutputSpeech(speech)

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }
}
