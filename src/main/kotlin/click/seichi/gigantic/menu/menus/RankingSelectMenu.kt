package click.seichi.gigantic.menu.menus

import click.seichi.gigantic.Gigantic
import click.seichi.gigantic.extension.wrappedLocale
import click.seichi.gigantic.item.items.menu.RankingButtons
import click.seichi.gigantic.menu.Menu
import click.seichi.gigantic.message.messages.RankingMessages
import click.seichi.gigantic.ranking.Score
import org.bukkit.entity.Player

/**
 * @author tar0ss
 */
object RankingSelectMenu : Menu() {
    override val size: Int
        get() = 9 * 5

    override fun getTitle(player: Player): String {
        return RankingMessages.TITLE(Gigantic.RANKING_UPDATE_TIME).asSafety(player.wrappedLocale)
    }

    init {
        registerButton(1, RankingButtons.TOTAL_SCORE_RANKING_SELECT)
        registerButton(2, RankingButtons.DAILY_SCORE_RANKING_SELECT)
        registerButton(3, RankingButtons.MONTHLY_SCORE_RANKING_SELECT)
        
        registerButton(18, RankingButtons.DIAMOND)
        registerButton(27, RankingButtons.GOLD)
        registerButton(36, RankingButtons.SILVER)
        Score.values().forEachIndexed { index, score ->
            registerButton(10 + index, RankingButtons.SCORE(score))
            registerButton(19 + index, RankingButtons.RANKED_PLAYER(score, 1))
            registerButton(28 + index, RankingButtons.RANKED_PLAYER(score, 2))
            registerButton(37 + index, RankingButtons.RANKED_PLAYER(score, 3))
        }
    }

}