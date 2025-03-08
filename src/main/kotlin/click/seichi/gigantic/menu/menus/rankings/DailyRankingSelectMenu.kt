package click.seichi.gigantic.menu.menus.rankings

import click.seichi.gigantic.Gigantic

import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.extension.wrappedLocale
import click.seichi.gigantic.item.items.menu.RankingButtons
import click.seichi.gigantic.menu.Menu
import click.seichi.gigantic.message.messages.RankingMessages
import click.seichi.gigantic.ranking.DailyScore
import org.bukkit.entity.Player

/**
 * @author kuroma6666
 */
object DailyRankingSelectMenu : Menu() {
    override val size: Int
        get() = 9 * 5

    override fun getTitle(player: Player): String {
        // val duration = player.get(Keys.MENU_RANKING_CATEGORY).menuTitle.asSafety(player.wrappedLocale)
        
        return RankingMessages.TITLE(Gigantic.RANKING_UPDATE_TIME, duration).asSafety(player.wrappedLocale)
    }

    init {
        registerButton(2, RankingButtons.TOTAL_SCORE_RANKING_SELECT)
        registerButton(4, RankingButtons.DAILY_SCORE_RANKING_SELECT)
        registerButton(6, RankingButtons.MONTHLY_SCORE_RANKING_SELECT)
        
        registerButton(18, RankingButtons.DIAMOND)
        registerButton(27, RankingButtons.GOLD)
        registerButton(36, RankingButtons.SILVER)
        DailyScore.values().forEachIndexed { index, score ->
            registerButton(10 + index, RankingButtons.DAILY_SCORE(score))
            registerButton(19 + index, RankingButtons.DAILY_RANKED_PLAYER(score, 1))
            registerButton(28 + index, RankingButtons.DAILY_RANKED_PLAYER(score, 2))
            registerButton(37 + index, RankingButtons.DAILY_RANKED_PLAYER(score, 3))
        }
    }

}