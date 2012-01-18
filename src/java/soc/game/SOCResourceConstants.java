/**
 * Java Settlers - An online multiplayer version of the game Settlers of Catan
 * Copyright (C) 2003  Robert S. Thomas
 * Portions of this file Copyright (C) 2009-2010,2012 Jeremy D Monin <jeremy@nand.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The author of this program can be reached at thomas@infolab.northwestern.edu
 **/
package soc.game;


/**
 * This is a list of constants for representing
 * types of resources in Settlers of Catan.
 *<P>
 * Warning: Many pieces of code depend on these values and their count.
 *          Clay is first (1), Wood is last (5), Unknown is after wood.
 *          Those are the 5 resource types (count==5 or ==6 (unknown) is also assumed).
 *          Adding a new resource type would require changes in many places.
 *          SOCRobotBrain.estimateResourceRarity is one of many examples.
 * Constants in other places (like {@link soc.message.SOCPlayerElement#CLAY})
 * have the same hardcoded values.
 *<P>
 * Before 1.1.08, this was an interface.  Changing to a class allowed adding
 * methods such as {@link #resName(int)}.
 *
 * @see SOCResourceSet
 */
public class SOCResourceConstants
{
    /**
     * Warning: Don't mess with these constants, other pieces
     *          of code depend on these numbers staying like this.
     *          {@link #CLAY} is first (1), {@link #WOOD} is last (5), {@link #UNKNOWN} is after wood.
     *<P>
     *          Some code also takes advantage that {@link #CLAY} == {@link SOCBoard#CLAY_HEX},
     *          {@link #SHEEP} == {@link SOCBoard#SHEEP_HEX}, etc.
     * @see #MIN
     */
    public static final int CLAY = 1;
    public static final int ORE = 2;
    public static final int SHEEP = 3;
    public static final int WHEAT = 4;
    public static final int WOOD = 5;

    /**
     * Unknown resource type (6).  Occurs after {@link #WOOD} (5).
     * Sometimes also used as a "MAX+1" for array sizing
     * for per-resource-type arrays that contain {@link #CLAY} through {@link #WOOD}
     * but don't contain <tt>UNKNOWN</tt>.
     *<P>
     * Same numeric value as {@link #GOLD_LOCAL}.
     * @see #MAXPLUSONE
     */
    public static final int UNKNOWN = 6;

    /**
     * Some code, internal to the <tt>soc.game</tt> or <tt>soc.robot</tt> packages,
     * uses this value (6) to represent {@link SOCBoardLarge#GOLD_HEX} tiles;
     * same numeric value as {@link #UNKNOWN}.  Occurs after {@link #WOOD} (5).
     *<P>
     * Gold is not a resource, and <tt>GOLD_LOCAL</tt> should not be sent over the
     * network or used externally.
     * @since 2.0.00
     */
    public static final int GOLD_LOCAL = 6;

    /** Minimum value (1 == {@link #CLAY}) */
    public static final int MIN = 1;

    /** One past maximum value (7; max value is 6 == {@link #UNKNOWN}) */
    public static final int MAXPLUSONE = 7;

    /**
     * Get the resource type name for this resource type number,
     * such as "clay" or "ore".
     *
     * @param rtype Resource type, such as {@link #CLAY} or {@link #WOOD}.
     *     {@link #UNKNOWN} / {@link #GOLD_LOCAL} is out of range.
     * @return Lowercase resource name, or null if rtype is out of range.
     * @since 1.1.08
     */
    public static String resName(int rtype)
    {
        String tname;
        switch (rtype)
        {
        case CLAY:
            tname = "clay";  break;
        case ORE:
            tname = "ore";   break;
        case SHEEP:
            tname = "sheep"; break;
        case WHEAT:
            tname = "wheat"; break;
        case WOOD:
            tname = "wood";  break;
        default:
            // Should not happen
            tname = null;            
        }
        return tname;
    }

    /**
     * Get the indefinite article of the resource type name for this number,
     * such as "a clay" or "an ore".
     *
     * @param rtype Resource type, such as {@link #CLAY} or {@link #SHEEP}.
     * @return Lowercase resource name, or "a null" if rtype is out of range
     *              ({@link #CLAY} - {@link #WOOD})
     * @since 1.1.08
     */
    public static String aResName(final int rtype)
    {
        StringBuffer sb = new StringBuffer();
        if (rtype == ORE)
            sb.append("an ");
        else
            sb.append("a ");
        sb.append(resName(rtype));
        return sb.toString();
    }
}
