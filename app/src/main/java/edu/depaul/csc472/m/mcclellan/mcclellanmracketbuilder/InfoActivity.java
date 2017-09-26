package edu.depaul.csc472.m.mcclellan.mcclellanmracketbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class InfoActivity extends Activity {

    TextView infoHeaderView, infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        if (intent != null) {
            String racketInfo = "Racket Frame Info";
            String stringInfo = "String Info";
            String performanceInfo = "Performance Info";
            infoHeaderView = (TextView) findViewById(R.id.info_header);
            infoView = (TextView) findViewById(R.id.info);
            infoView.setMovementMethod(new ScrollingMovementMethod());
            int info = intent.getIntExtra("Info", 3);

            if (info == 1) infoHeaderView.setText(racketInfo);
            else if (info == 2) infoHeaderView.setText(stringInfo);
            else infoHeaderView.setText(performanceInfo);
            infoView.setText(getInfo(info));
        }
    }

    private String getInfo(int info) {
        switch (info) {
            case 1: return racketInfo;
            case 2:  return stringInfo;
            case 0: return performanceInfo;
            default: return "error";
        }
    }

    // Data -- all text is mine

    String racketInfo =
            "HEAD SIZE:\n\n" +
            "How large the area of the racket head is in square inches. " +
            "Larger head sizes offer much greater power and some more spin, but sacrifice " +
            "control. As tennis has evolved from a serve-and-volley game to a more baseline-" +
            "oriented slugfest, racket sizes have generally gotten larger. Most players play " +
            "with a racket between 95 and 110 square inches; for now, rackets larger than 110 " +
            "square inches are considered over-sized, which an official upper limit of 135 " +
            "square inches. Although there is no official lower limit, it is hard to find new " +
            "rackets smaller than 90 square inches. Over-sized rackets are common for beginners " +
            "who want to hit the ball cleanly and strongly, but as players' swings develop, " +
            "they may want to consider using a smaller, less-powerful racket to better control " +
            "their shots.\n\n" +
            "WEIGHT:\n\n" +
            "Lighter rackets are easier to swing fast, and in general the trend has been for " +
            "rackets to become lighter to increase racket head speed (and therefore spin and " +
            "power. However, lighter rackets absorb less of the incoming ball's impact, which " +
            "neutralizes the extra power advantage of a faster swing and makes returning hard, " +
            "flat shots difficult. Hits will feel more solid with heavier rackets, but beginners " +
            "should start out with lighter rackets and consider adding weight to them as they " +
            "require it. Rackets are usually weighed in ounces.\n\n" +
            "BALANCE:\n\n" +
            "The balance of a racket is about as important as the overall weight, and greatly " +
            "affects the racket's feel. The balance is usually reported in units of \"points\" " +
            "either head light or head heavy: A head light racket has more of the weight " +
            "concentrated towards the handle, while a head heavy racket has more of the mass " +
            "towards the head end. Head light rackets offer greater swing speed, making it " +
            "to time shots, but head heavier rackets offer a more solid feeling hit and stronger " +
            "volleys. Balance is usually a matter of preference, though.\n\n" +
            "LENGTH:\n\n" +
            "Rackets vary in length between the standard 27 inches and the max allowed limit of " +
            "29 inches. Longer rackets offer more power with their greater leverage, but " +
            "hamper swing speed unless they are lightened or balanced head-light.\n\n" +
            "STIFFNESS:\n\n" +
            "The stiffness of a frame is measured on a relative RAting (RA); basically, " +
            "the higher the number, the stiffer the racket. Stiffer rackets offer more power, " +
            "control, and a crisper hit, but many players find stiff rackets uncomfortable. " +
            "Additionally, stiff rackets can aggravate arm issues.";

    String stringInfo =
            "MAINS VS CROSSES:\n\n" +
            "The MAINS are the strings that run parallel to the handle, or up and down. The " +
            "CROSSES are the strings that run perpendicular to the handle. Since the mains " +
            "are longer than the crosses, and since ground strokes are made with the racket " +
            "held sideways to allow the mains to impart spin on the ball, the overall feel of " +
            "the racket is dominated by the type of string used for the mains, with the crosses " +
            "offering secondary characteristics. Most players use the same type of string " +
            "in both the mains and the crosses, but hybrid stringing has become popular for " +
            "serious players looking to fine tune the string feel.\n\n" +
            "STRING TYPES:\n\n" +
            "- Synthetic Gut:\n" +
            "The cheapest and most common of the string types, synthetic gut is usually made " +
            "from Nylon, and offers balanced performance with durable properties. Casual " +
            "players should stick with these cheaper strings, since they last the longest " +
            "and offer neutral characteristics.\n\n" +
            "- Multifilament:\n" +
            "These strings are usually made by wrapping many thinner Nylon threads together into " +
            "a bundle. They are more comfortable and more powerful than regular synthetic gut, " +
            "and more expensive. They are great for players with arm injuries, but some players " +
            "get worried by how quickly the fibers become frayed; this is generally not an " +
            "issue, although multifilaments indeed won't last as long as solid core strings.\n\n" +
            "- Monofilament:\n" +
            "Monofilament strings are usually made with a single polyester core (sometimes " +
            "Kevlar) and are the highest performing string, reigning in power in favor of " +
            "fantastic spin and control. Most players on tour use these strings as their mains, " +
            "but they have downsides: They are harsh, dead-feeling strings that aggravate arm " +
            "problems. They are undergoing extensive R&D to become softer feeling, but until " +
            "then many players combine these strings with multifilaments in the crosses to " +
            "soften the feel of the racket.\n\n" +
            "NUMBER OF MAINS/CROSSES:\n\n" +
            "Not taking head size into account, a greater number of strings overall improves " +
            "shot control and crispness, but at the expense of generated spin. More strings " +
            "are great for negating spin, though, so flat hitters may want to consider a denser " +
            "pattern. Larger head sizes without more strings will make the strings further " +
            "apart, increasing spin even more, although most over-sized rackets will add add " +
            "more strings to keep the feel consistent.\n\n" +
            "TENSION:\n" +
            "Measured in pounds (lbs), most rackets come with a recommended string tension, " +
            "which should be used as a guide for making changes. Decreasing the tension relative " +
            "to the recommended tension will provide more power and a softer feel, while " +
            "tightening the strings relative to the recommended tension will offer more control " +
            "and crisper hits. Tension is often a very subjective measure; pros use vastly " +
            "different tension between each other. Beginner's should stick with the recommended " +
            "tension, and note that the tension of a racket decreases with play time.\n\n" +
            "GAUGE:\n\n" +
            "The thickness of the racket, counterintuitively from thickest (15) to thinnest " +
            "(19) Thinner strings always perform better, offering more spin and control, but " +
            "also break more easily. Most casual players play with 16 gauge for durability, " +
            "while most club players stick with 17 gauge. Higher than 17 gauge tends to break " +
            "frequently, so plan your budget accordingly.";

    String performanceInfo =
            "POWER:\n\n" +
            "How far the ball goes when you hit it is the most obvious objective effect you " +
            "notice when trying a new racket, but more power isn't always desirable. Players " +
            "with big, strong swings may end up hitting too many balls long if the racket is " +
            "too powerful, and a bouncy racket makes touch and precision shots more difficult. " +
            "But as spin becomes an ever increasing part of the game even among casual players, " +
            "rackets are becoming more powerful. If you ever want to know how the old folks at" +
            "the local club can hit such hard shots with such weak swings, check out their " +
            "usually over-sized rackets.\n\n" +
            "SPIN:\n\n" +
            "Spin refers to how fast you can get the ball to rotate as it travels along the shot " +
            "path. Heavy top spin shots will quickly dip down into the court, before seeming " +
            "to explode forward and upward. Heavy slice (bottom spin) shots will maintain a flat " +
            "trajectory, often seeming to skid off the court, and not bounce high. Modern tennis " +
            "is dominated by spin, but that still leaves room for a rogue flat hitter to shake " +
            "things up. But generally, more spin is better if you plan on playing a baseline " +
            "game.\n\n" +
            "SWING SPEED:\n\n" +
            "How easy it is to swing the racket, with a higher swing speed meaning more racket " +
            "head speed and therefore usually more power and spin. The higher the better, since " +
            "it allows you to more quickly prepare for and time your shots, but attaining high " +
            "swing speeds can come at the cost of solid hitting feel and performance. Generally, " +
            "rackets are becoming lighter and more designed to increase these speeds.\n\n" +
            "CONTROL:\n\n" +
            "This can be a subjective metric, since beginning tennis players will often have a " +
            "different definition of control than seasoned players. Usually, though, less " +
            "powerful rackets offer greater control and precision precisely because the ball" +
            "won't explode off the racket as violently, allowing a player to more effectively " +
            "adjust their swing and put the ball where they really want to. With huge spin, " +
            "though, just about anyone can take huge cuts with powerful rackets and produce " +
            "enough top spin to keep the ball in the court.\n\n" +
            "VOLLEY:\n\n" +
            "Volleying has fallen out of favor in the singles game, but is still a huge " +
            "component of doubles. Sadly, many of the characteristics that make a good volley " +
            "racket, such as a sturdy weight and slight head-heavy balance, adversely affect " +
            "the baseline game, and vice-versa. If you plan on playing lots of doubles, or if " +
            "you happen to be a rare serve-volleyer, consider forgoing the modern racket trends " +
            "and pick one that will stick a mean volley.";
}