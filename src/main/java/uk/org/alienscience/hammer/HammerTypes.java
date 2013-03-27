package uk.org.alienscience.hammer;

import uk.org.alienscience.Hammer;

import static uk.org.alienscience.Hammer.*;

/**
 * TODO: Document
 */
public class HammerTypes {

    /******* Characters for use in types *************/

    public static Hammer<String> colon = literal(":");
    public static Hammer<String> semiColon = literal(";");
    public static Hammer<String> atSign = literal("@");
    public static Hammer<String> ampersand = literal("&");
    public static Hammer<String> equalsSign = literal("=");
    public static Hammer<String> slash = literal("/");
    public static Hammer<String> percent = literal("%");
    public static Hammer<String> questionMark = literal("?");

    public static Hammer<String> digit = charRange("0","9");
    public static Hammer<String> alpha = oneOf(charRange("a", "z"), charRange("A", "Z"));

    /******* HTTP URL Type (based on RFC 1738) **********/

    private static Hammer<String> safe = oneOf("$","-","_",".","+");
    private static Hammer<String> extra = oneOf("!","*","'","(",")",",");
    private static Hammer<String> hex = oneOf(digit, charRange("A","F"), charRange("a","f"));
    private static Hammer<String> unreserved = oneOf(alpha, digit, safe, extra);
    private static Hammer<String> escape = sequence(percent, hex, hex);
    private static Hammer<String> uchar = oneOf(unreserved, escape);
    // TODO finish from here
    private static Hammer<String> hostname = oneOf(hostname, hostNumber);
    private static Hammer<String> host = oneOf(hostname, hostNumber);
    private static Hammer<String> hostPort = sequence(host, optional(colon, port));
    private static Hammer<String> search = oneOf(uchar, colon, semiColon, atSign, ampersand, equalsSign);
    private static Hammer<String> hSegment = oneOf(uchar, colon, semiColon, atSign, ampersand, equalsSign);
    private static Hammer<String> hPath = sequence(hSegment, sequence(slash, hSegment).repeat(0, 8));
    public static Hammer<String> httpUrl =
            sequence(literal("http://"), hostPort, optional(slash, hPath, optional(questionMark, search)));
}
