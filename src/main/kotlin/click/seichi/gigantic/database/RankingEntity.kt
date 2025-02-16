package click.seichi.gigantic.database

import click.seichi.gigantic.database.dao.ranking.RankingScore
import click.seichi.gigantic.database.dao.ranking.RankingUser
import click.seichi.gigantic.database.dao.ranking.DailyRankingScore
import java.util.*

/**
 * @author tar0ss
 */
class RankingEntity(uniqueId: UUID) {

    val user = RankingUser.findById(uniqueId) ?: RankingUser.new(uniqueId) {}

    val score = RankingScore.findById(uniqueId) ?: RankingScore.new(uniqueId) {}

    val dailyScore = DailyRankingScore.findById(uniqueId) ?: DailyRankingScore.new(uniqueId) {}

}