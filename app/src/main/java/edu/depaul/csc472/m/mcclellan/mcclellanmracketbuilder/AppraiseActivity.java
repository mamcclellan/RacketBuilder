package edu.depaul.csc472.m.mcclellan.mcclellanmracketbuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AppraiseActivity extends Activity {

    TextView appraiseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise);

        Intent intent = getIntent();
        if (intent != null) {
            appraiseView = (TextView) findViewById(R.id.appraisal);
            Profile profile = intent.getParcelableExtra("Profile");
            appraiseView.setText(getAppraisal(profile));
        }
    }

    private String getAppraisal(Profile profile)
    {
        StringBuilder s = new StringBuilder();

        int headSizeIndex, weightIndex, balanceIndex;
        if (profile.getHeadSize() < 3) headSizeIndex = 0;
        else if (profile.getHeadSize() < 6) headSizeIndex = 1;
        else headSizeIndex = 2;
        if (profile.getWeight() < 3) weightIndex = 0;
        else if (profile.getWeight() < 6) weightIndex = 1;
        else weightIndex = 2;
        if (profile.getBalance() < 3) balanceIndex = 0;
        else if (profile.getBalance() < 6) balanceIndex = 1;
        else balanceIndex = 2;

        s.append(overallFrameDesc[headSizeIndex][weightIndex][balanceIndex]);
        s.append("\n\n");

        if (profile.getLength() > 1) {
            s.append(lengthFactor[weightIndex][balanceIndex]);
            s.append("\n\n");
        }

        if (profile.getStiffness() == 0) {
            s.append(stiffnessFactor[0]);
            s.append("\n\n");
        }
        else if (profile.getStiffness() == 4) {
            s.append(stiffnessFactor[1]);
            s.append("\n\n");
        }

        s.append("String Commentary:\n\n");

        int stringMainIndex = profile.getTypeMain();
        int stringCrossIndex = profile.getTypeCrosses();
        s.append(stringTypeFactor[stringMainIndex][stringCrossIndex]);
        if (profile.getTension() < 2) s.append(tensionFactor[0]);
        else if (profile.getTension() > 6) s.append(tensionFactor[1]);
        if (profile.getGauge() == 0) s.append(gaugeFactor[0]);
        else if (profile.getGauge() == 4) s.append(gaugeFactor[1]);

        return s.toString();
    }

    // String data -- All my own commentary

    // Overall frame characteristics
    // 3-dimensional array for 3 steps of head size, weight, and balance
    // 27 possibilities
    String[][][] overallFrameDesc = {
            // Small Head Size
            {
                    // Low Weight
                    {
                        // Head Light balance
                            "A racket for players who want the fastest swing - and nothing else. " +
                            "It'll feel great as you take practice swings, but once the ball hits" +
                            " the strings... well, the racket will move more than the ball. ",
                        // Balanced frame
                            "This racket is a little too light on power to be taken seriously, " +
                            "but will probably feel good to swing and offer good control for" +
                            " experienced players who also happen to be hopelessly weak. ",
                        // Head Heavy balance
                            "Inexperienced players who want the feel of an old-school racket but" +
                            " don't have the stroke practice to swing a real one properly will" +
                            " enjoy being the volley hero of the senior's tour. "
                    },
                    // Medium Weight
                    {
                            // Head Light balance
                            "The combination of a small head size and head light balance make " +
                            "this racket a good choice for players with strong hand-eye" +
                            "coordination and perhaps underdeveloped forearms. This racket risks" +
                            " being bullied around by strong-hitting opponents. ",
                            // Balanced frame
                            "A racket for intermediate players less concerned about power and" +
                            " more focused on solid control and predictable behavior. ",
                            // Head Heavy balance
                            "Sharp-eyed tour professionals should enjoy the decent swing-speed," +
                            " strong plow-through and accurate shot-making of this racket, " +
                            "although this racket may lack power against the world's strongest. "
                    },
                    // Heavy Weight
                    {
                            // Head Light balance
                            "The small head size and head light balance keep this racket plenty" +
                            " swingable, serious players may be disappointed by the disparity" +
                            " between the racket's solid practice swings but relative lack of" +
                            " plow-through. Hawk-eyed players with long but accurate strokes" +
                            " will find plenty to like though. ",
                            // Balanced frame
                            "The old-school pro's racket. Great for feel, control and solid" +
                            "volleys, McEnroe would approve. Today's power-focused game may " +
                            " have made this racket uncompetitive for most at the elite level," +
                            " but Roger Federer was still winning slams with this not too long" +
                            " ago. ",
                            // Head Heavy balance
                            "For bodybuilders not suffering from myopia, this is an easy way to" +
                            " ask McEnroe, \"Do you even lift bro?\" If you can somehow prepare" +
                            " your swings years in advance - and somehow hit the tiny sweet spot" +
                            " - glory shall be yours. "
                    }
            },
            // Medium Head Size
            {
                    // Low Weight
                    {
                            // Head Light balance
                            "Beginners with strong eyes but poor upper body strength will" +
                            " appreciate this friendly racket's demeanor, at least until the" +
                            " bigger players come and steal their lunch money. ",
                            // Balanced frame
                            "A good choice for entry-level players who feel confident in" +
                            " their hand-eye coordination. Offers a good balance of " +
                            "everything, but players who develop their game quickly may" +
                            " also outgrow this racket's weight quickly. ",
                            // Head Heavy balance
                            "Casual players will love this frame's sense of stability through" +
                            " their shots. In fact, professional players are moving towards this" +
                            " racket as well, as it keeps volleys sharp and absorbs strong shots" +
                            " without sacrificing much swing speed. "
                    },
                    // Medium Weight
                    {
                            // Head Light balance
                            "Intermediate players who like to take large, fast cuts at the ball " +
                            "and want nothing to slow them down will find a good partner in this" +
                            " racket. Its overall weight should hold up against all but the " +
                            "hardest hitters. Players with compact or slow swings may not like " +
                            "stability of this racket against those strong hitters, but " +
                            "solid pushers should be fine. ",
                            // Balanced frame
                            "Middle of the road racket for middle of the road players - not " +
                            "that there's anything wrong with that. All-around club players " +
                            "will enjoy the modern feel of this racket, which has no obvious" +
                            " weaknesses. ",
                            // Head Heavy balance
                            "Common amongst pros and strong club players alike, this " +
                            "racket offers good performance all around for players who can" +
                            " generate their own power from developed swings. "
                    },
                    // Heavy Weight
                    {
                            // Head Light balance
                            "The choice of the traditional dirtballing pro, the racket offers " +
                            "good weight against hard shots but keeps the swing nice and fast " +
                            "for reliable, heavy ground strokes. Think Rafa, but if you don't " +
                            "have his talent at the net, you'll find your volleys will suffer " +
                            "with this racket. ",
                            // Balanced frame
                            "The typical choice of pros for the dawn of the power era of tennis " +
                            "in the 90's and aughts. You'll need strong forearms, but this " +
                            "racket offers solid protection against flat hard hitters. As spin " +
                            "becomes an increasing factor in the modern game, this racket is " +
                            "falling out of pro favor for its slower swing speed, but is still " +
                            "a good choice for any serious player. ",
                            // Head Heavy balance
                            "A racket for true He-men. It's also my racket of choice... ok not" +
                            " because I'm a He-man, but because of the protection this racket" +
                            " offers against hard hitters. Against strong servers, just stick " +
                            "this racket out and watch it absorb all the pace. Volleys are also " +
                            "easy to stick, and when I have time to set up for a shot and I hit " +
                            "the sweet spot... well, there's no greater feeling. Not an easy " +
                            "racket to come back to when you're out of practice, though. "
                    }
            },
            // Large Head Size
            {
                    // Low Weight
                    {
                            // Head Light balance
                            "Oh hi grandma! Yes, I do see you're playing tennis. How nice! " +
                            "Please watch your hip, though. ",
                            // Balanced frame
                            "The best choice for absolute beginners, this racket should give " +
                            "anyone confidence to hit some balls. This is a surprisingly" +
                            " scalable option too, since as your swing speed improves, you can" +
                            " still reign in this racket's power by adding extra spin. Best " +
                            "option for keeping long (and maybe boring) rallies going. ",
                            // Head Heavy balance
                            "Beginners who want solid strikes on the ball will love the power " +
                            "of this racket. Players who are too physically strong may find " +
                            "themselves hitting too many balls long, though. "
                    },
                    // Medium Weight
                    {
                            // Head Light balance
                            "Perhaps the racket of future pros, if the trends of previous " +
                            "decades are any indication. The large head provides good power and" +
                            " spin, while the balance keeps the swing speed high. This racket's " +
                            "time will come. ",
                            // Balanced frame
                            "The current choice of the professional pusher looking to troll his " +
                            "opponent into submission. With the large head size, it's hard to " +
                            "mis-hit shots, and the medium weight and balance offer protection " +
                            "from being blown off the court. Don't aim too close to the lines " +
                            "and add plenty of spin; let your opponents f*** up before you do. ",
                            // Head Heavy balance
                            "For older, former pushers who want to laugh at their opponents' " +
                            "mistakes but also not do any running of their own, this racket " +
                            "offers huge power for those with compact swings. It will absorb " +
                            "anything you throw at it. It's best not to go for your own kill " +
                            "shots - stick your racket out and let it do the work for you."
                    },
                    // Heavy Weight
                    {
                            // Head Light balance
                            "I honestly don't know who this racket would be good for. If you're " +
                            "a beginner, choose a lighter racket; if you're more advanced, " +
                            "choose a smaller racket. This racket has the weird condition of " +
                            "being powerful while feeling underpowered - the opposite of how " +
                            "most players want their rackets to behave as they mature. ",
                            // Balanced frame
                            "Dude, I know you're swole. You don't need to keep proving it by " +
                            "hitting the ball out of the courts. ",
                            // Head Heavy balance
                            "This is tennis, not baseball."
                    }
            }
    };

    // For higher lengths against weight and balance
    String[][] lengthFactor = {
            // low weight
            {
                    // head light balance
                    "The lower weight and head light balance should make this a very easy to " +
                    "swing racket despite its length. ",
                    // balanced frame
                    "The extra length, along with the low weight, should make this a fast and " +
                    "powerful racket for those comfortable hitting with a longer racket. ",
                    // head heavy frame
                    "Despite this racket's low weight, the extra length and head-heavy balance " +
                    "will slow down the swing speed. Shots should feel exceptionally crisp and " +
                    "powerful though. "
            },
            // medium weight
            {
                    // head light balance
                    "The racket works well with its longer length, which when combined with its " +
                    "head-light balance should produce terrific racket head speed. ",
                    // balanced frame
                    "Although this is a balanced racket, for many players it will feel more like " +
                    "a head-heavy racket with its extra length moving the overall balance " +
                    "outward. ",
                    // head heavy frame
                    "With its extra length, this racket will feel much heavier than its spec " +
                    "suggests.  "
            },
            // heavy weight
            {
                    // head light balance
                    "With its extra length and hefty frame, this racket would be almost " +
                    "unusable without its head light balance. Strong players may find a use for " +
                    "it, but anyone already uncomfortable with extended-length rackets will " +
                    "not approve. ",
                    // balanced frame
                    "Only the hardcore would use this extended length frame; it's a little too " +
                    "long and heavy even for season players. ",
                    // head heavy frame
                    "As if this racket weren't already enough of a club, its extra length " +
                    "ensures that it will be exceptionally unwieldy. "
            }
    };

    String[] stiffnessFactor = {
            // low stiffness
        "This should be a very comfortable racket and easy on the arm, but the crispness of your " +
        "shots might suffer for it. ",
            // high stiffness
        "Many players will find a racket this stiff to be uncomfortable. But if you don't have " +
        "arm problems and like the feel, you may find some performance benefits. "
    };

    String[] tensionFactor = {
            // Low tension
            "Inexperienced players might have trouble if they use a racket that has been " +
            "strung to a tension this much lower than recommended, or if they play with an old" +
            " racket that has lost tension to this degree. Just because Federer can control " +
            "strings this loose doesn't mean you can. Only string this low if you have trouble " +
            "getting the ball over the net, or if comfort is your greatest priority. ",
            // high tension
            "A racket strung this tightly will offer good control - and you'll hit few balls " +
            "long - but it may not be comfortable to play with, especially if combined with a " +
            "stiff frame. "
    };

    String[][] stringTypeFactor = {
        // mains: synthetic gut
            {
                    // crosses: synthetic gut
                    "This racket uses the standard, cheap, and reliable synthetic gut, which is " +
                    "well-behaved and well-balanced. ",
                    // crosses: multifilament
                    "The multifilament string used for the crosses adds some comfort and " +
                    "power while only minimally sacrificing control and spin. Do not be alarmed " +
                    "by their tendency to fray. ",
                    // crosses: monofilament
                    "The monofilament cross strings will add a bit of spin and control while " +
                    "only slightly reducing power."
            },
            // mains: multifilament
            {
                    // crosses: synthetic gut
                    "The synthetic gut in the crosses helps even out these string's performance, " +
                    "but the overall feel will still be old school. Except strong cushy volleys, " +
                    "but also lots of hitting long. ",
                    // crosses: multifilament
                    "A full multifilament string setup will be comfy and remind older players of " +
                    "their old rackets, but with today's modern game full of spin and power," +
                    " these strings will be outmatched in performance. ",
                    // crosses: monofilament
                    "A strange string setup indeed: the monofilament crosses will not do much " +
                    "to enhance spin and control when the multifilaments are dictating the " +
                    "overall feel in the mains. I suppose many players inadvertantly end up " +
                    "playing with this setup when they are trying to use the opposite setup but " +
                    "confuse the two while stringing. "
            },
            // mains: monofilament
            {
                    // crosses: synthetic gut
                    "Monofilaments are harsh, but can be slightly softened with some cheap " +
                    "synthetic gut to retain almost all of the monofilament's extra spin and " +
                    "control. ",
                    // crosses: multifilament
                    "The nerdy choice for advanced club and pro players who want the modern " +
                    "performance benefits of monofilament strings to dominate the racket's " +
                    "overall characterstic, but are looking to soften its harshness with " +
                    "the multifilament crosses. ",
                    // crosses: monofilament
                    "Not for players with arm issues, full monofilament string setups are harsh, " +
                    "unforgiving, ... and perform extremely well. Rafa generates his ridiculous " +
                    "spin with these strings. He also spends a lot of time injured. "
            }
    };

    String[] gaugeFactor = {
            "You will rarely break strings that are this thick. ",
            "You will frequently break strings that are this thin. "
    };

}
