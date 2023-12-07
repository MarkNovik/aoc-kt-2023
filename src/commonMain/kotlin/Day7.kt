data object Day7 : AOC(7) {
    override fun part1(input: String): String = solve(input, false)
    override fun part2(input: String): String = solve(input, true)

    private fun solve(input: String, jIsJoker: Boolean) = parseInput(input, jIsJoker)
        .sortedBy(Play::hand)
        .foldIndexed(0L) { index, acc, play ->
            acc + (index + 1) * play.bid
        }.toString()

    fun parseInput(input: String, jIsJoker: Boolean) = input.lines().map { Play.parse(it, jIsJoker) }

    data class Play(val hand: Hand, val bid: Long) {
        companion object {
            fun parse(line: String, jIsJoker: Boolean) =
                line.split(" ").let { (h, b) -> Play(Hand.parse(h, jIsJoker), b.toLong()) }
        }
    }

    data class Hand(val cards: List<Suit>) : Comparable<Hand> {
        val combo by lazy {
            val dist = cards.toSet().associateWith { cards.count(it::equals) }
            val vals = dist.values.sortedDescending()
            when (dist[Suit.JOKER]) {
                5, 4 -> Combo.FiveOfKind
                3 -> when (vals) {
                    listOf(3, 2) -> Combo.FiveOfKind
                    listOf(3, 1, 1) -> Combo.FourOfKind
                    else -> error("UNREACHABLE")
                }

                2 -> when (vals) {
                    listOf(3, 2) -> Combo.FiveOfKind
                    listOf(2, 2, 1) -> Combo.FourOfKind
                    listOf(2, 1, 1, 1) -> Combo.ThreeOfKind
                    else -> error("UNREACHABLE")
                }

                1 -> when (vals) {
                    listOf(4, 1) -> Combo.FiveOfKind
                    listOf(3, 1, 1) -> Combo.FourOfKind
                    listOf(2, 2, 1) -> Combo.FullHouse
                    listOf(2, 1, 1, 1) -> Combo.ThreeOfKind
                    listOf(1, 1, 1, 1, 1) -> Combo.OnePair
                    else -> error("UNREACHABLE")
                }

                null -> when (vals) {
                    listOf(5) -> Combo.FiveOfKind
                    listOf(4, 1) -> Combo.FourOfKind
                    listOf(3, 2) -> Combo.FullHouse
                    listOf(3, 1, 1) -> Combo.ThreeOfKind
                    listOf(2, 2, 1) -> Combo.TwoPair
                    listOf(2, 1, 1, 1) -> Combo.OnePair
                    listOf(1, 1, 1, 1, 1) -> Combo.HighCard
                    else -> error("UNREACHABLE")
                }

                else -> error("UNREACHABLE")
            }

        }

        override fun compareTo(other: Hand): Int {
            val comboCmp = this.combo compareTo other.combo
            return if (comboCmp != 0) comboCmp
            else this.cards.zip(other.cards) { a, b ->
                a compareTo b
            }.firstOrNull {
                it != 0
            } ?: 0
        }

        companion object {
            fun parse(str: String, jIsJoker: Boolean): Hand = Hand(str.map { Suit.parse(it, jIsJoker) })
        }
    }

    enum class Suit {
        JOKER,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;

        companion object {
            fun parse(c: Char, jIsJoker: Boolean): Suit = when (c.uppercaseChar()) {
                '2' -> TWO
                '3' -> THREE
                '4' -> FOUR
                '5' -> FIVE
                '6' -> SIX
                '7' -> SEVEN
                '8' -> EIGHT
                '9' -> NINE
                'T' -> TEN
                'J' -> if (jIsJoker) JOKER else JACK
                'Q' -> QUEEN
                'K' -> KING
                'A' -> ACE
                else -> error("Unknown suit `$c`")
            }
        }
    }

    enum class Combo {
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfKind,
        FullHouse,
        FourOfKind,
        FiveOfKind
    }
}