package click.seichi.gigantic.ranking

import click.seichi.gigantic.cache.cache.PlayerCache
import click.seichi.gigantic.cache.cache.RankingPlayerCache
import click.seichi.gigantic.cache.key.Keys
import click.seichi.gigantic.database.RankingEntity
import click.seichi.gigantic.database.table.ranking.DailyRankingScoreTable
import click.seichi.gigantic.extension.potionOf
import click.seichi.gigantic.message.LocalizedText
import click.seichi.gigantic.player.ExpReason
import click.seichi.gigantic.relic.Relic
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SortOrder
import java.math.BigDecimal
import java.util.*

/**
 * @author kuroma6666
 */
enum class DailyScore(
        val column: Column<Long>,
        val sortOrder: SortOrder,
        private val icon: ItemStack,
        private val localizedName: LocalizedText,
        private val localizedUnit: LocalizedText
) {
    DAILY_EXP(
            DailyRankingScoreTable.exp,
            SortOrder.DESC,
            ItemStack(Material.EXPERIENCE_BOTTLE),
            LocalizedText(
                    Locale.JAPANESE to "日間獲得経験値ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "exp"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            var exp = 0.toBigDecimal()
            Keys.EXP_MAP.values.forEach { exp += cache.getOrDefault(it) }
            entity.score.exp = exp.toLong()
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_EXP.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.exp
        }

    },
    DAILY_BREAK_BLOCK(
            DailyRankingScoreTable.breakBlock,
            SortOrder.DESC,
            potionOf(Color.GREEN),
            LocalizedText(
                    Locale.JAPANESE to "日間通常破壊量ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "block"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.MINE_BLOCK)).toLong()
            entity.dailyScore.breakBlock = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_BREAK_BLOCK.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.breakBlock
        }
    },
    DAILY_MULTI_BREAK_BLOCK(
            DailyRankingScoreTable.multiBreakBlock,
            SortOrder.DESC,
            potionOf(Color.BLUE),
            LocalizedText(
                    Locale.JAPANESE to "日間範囲破壊量ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "block"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.SPELL_MULTI_BREAK)).toLong()
            entity.dailyScore.multiBreakBlock = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.RANK_MULTI_BREAK_BLOCK.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.multiBreakBlock
        }
    },
    DAILY_RELIC_BONUS(
            DailyRankingScoreTable.relicBonus,
            SortOrder.DESC,
            potionOf(Color.PURPLE),
            LocalizedText(
                    Locale.JAPANESE to "日間レリックボーナスランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "exp"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.EXP_MAP.getValue(ExpReason.RELIC_BONUS)).toLong()
            entity.dailyScore.relicBonus = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_RELIC_BONUS.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.relicBonus
        }
    },
    DAILY_MAX_COMBO(
            DailyRankingScoreTable.maxCombo,
            SortOrder.DESC,
            potionOf(Color.RED),
            LocalizedText(
                    Locale.JAPANESE to "日間最大コンボ数ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "combo"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.MAX_COMBO).toLong()
            entity.dailyScore.maxCombo = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_MAX_COMBO.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.maxCombo
        }
    },
    DAILY_RELIC(
            DailyRankingScoreTable.relic,
            SortOrder.DESC,
            potionOf(Color.LIME),
            LocalizedText(
                    Locale.JAPANESE to "日間レリック数ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "個"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            var value = 0L
            Relic.values().forEach { value += cache.getOrDefault(Keys.RELIC_MAP.getValue(it)) }
            entity.dailyScore.relic = value
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_RELIC.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.relic
        }
    },
    DAILY_STRIP_MINE(
            DailyRankingScoreTable.stripMine,
            SortOrder.DESC,
            potionOf(Color.BLACK),
            LocalizedText(
                    Locale.JAPANESE to "日間露天掘り面積ランキング"
            ),
            LocalizedText(
                    Locale.JAPANESE to "chunk"
            )
    ) {
        override fun write(entity: RankingEntity, cache: PlayerCache) {
            val value = cache.getOrDefault(Keys.STRIP_MINE)
            val chunk = value.div(16.0).div(16.0).toLong()
            entity.dailyScore.stripMine = chunk
        }

        override fun read(entity: RankingEntity, cache: RankingPlayerCache) {
            Keys.DAILY_RANK_STRIP_MINE.let {
                cache.force(it, it.read(entity))
            }
        }

        override fun getValue(rankingPlayer: RankingPlayer): Long {
            return rankingPlayer.stripMine
        }
    },
    ;

    fun getIcon() = icon.clone()
    fun getName(locale: Locale) = localizedName.asSafety(locale)
    fun getUnit(locale: Locale) = localizedUnit.asSafety(locale)

    abstract fun write(entity: RankingEntity, cache: PlayerCache)

    abstract fun read(entity: RankingEntity, cache: RankingPlayerCache)

    abstract fun getValue(rankingPlayer: RankingPlayer): Long
}