package click.seichi.gigantic.database.dao.ranking

import click.seichi.gigantic.database.table.ranking.DailyRankingScoreTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import java.util.*

/**
 * @author kuroma6666
 */
class DailyRankingScore(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, DailyRankingScore>(DailyRankingScoreTable)

    var exp by DailyRankingScoreTable.exp

    var breakBlock by DailyRankingScoreTable.breakBlock

    var multiBreakBlock by DailyRankingScoreTable.multiBreakBlock

    var relicBonus by DailyRankingScoreTable.relicBonus

    var maxCombo by DailyRankingScoreTable.maxCombo

    var relic by DailyRankingScoreTable.relic

    var stripMine by DailyRankingScoreTable.stripMine

}