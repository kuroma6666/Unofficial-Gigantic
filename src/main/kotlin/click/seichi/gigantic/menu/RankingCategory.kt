package click.seichi.gigantic.menu

import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.extension.getOrPut
import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.ranking.Ranking
import click.seichi.gigantic.will.Will
import org.bukkit.entity.Player
import java.util.*

/**
 * @author tar0ss
 */
enum class RankingCategory(val menuTitle: LocalizedText) {
    TOTAL(LocalizedText(
            Locale.JAPANESE to "累計"
    )) {
        override fun isContain(player: Player, ranking: Ranking): Boolean {
            return true
        }
    },
    DAILY(LocalizedText(
            Locale.JAPANESE to "日間"
    )) {
        override fun isContain(player: Player, ranking: Ranking): Boolean {
            return true
        }
    },
    MONTHLY(LocalizedText(
            Locale.JAPANESE to "月間"
    )) {
        override fun isContain(player: Player, ranking: Ranking): Boolean {
            return true
        }
    },
    ;

    abstract fun isContain(player: Player, ranking: Ranking): Boolean

}