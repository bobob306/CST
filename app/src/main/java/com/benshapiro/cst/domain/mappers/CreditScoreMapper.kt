package com.benshapiro.cst.domain.mappers

import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.network.response.GetCreditScoreResponse

object CreditScoreMapper {

    fun buildFrom(
        response: GetCreditScoreResponse
    ): CreditScore {
        return CreditScore(
            accountIDVStatus = response.accountIDVStatus,
            augmentedCreditScore = response.augmentedCreditScore,
            dashboardStatus = response.dashboardStatus,
            personaType = response.personaType,
            creditReportInfo = CreditScore.CreditReportInfo(
                changeInLongTermDebt = response.creditReportInfo.changeInLongTermDebt,
                changeInShortTermDebt = response.creditReportInfo.changeInShortTermDebt ,
                changedScore = response.creditReportInfo.changedScore ,
                clientRef = response.creditReportInfo.clientRef ,
                currentLongTermCreditLimit = response.creditReportInfo.currentLongTermCreditLimit ,
                currentLongTermCreditUtilisation = response.creditReportInfo.currentLongTermCreditUtilisation ,
                currentLongTermDebt = response.creditReportInfo.currentLongTermDebt ,
                currentLongTermNonPromotionalDebt = response.creditReportInfo.currentLongTermNonPromotionalDebt ,
                currentShortTermCreditLimit = response.creditReportInfo.currentShortTermCreditLimit ,
                currentShortTermCreditUtilisation = response.creditReportInfo.currentShortTermCreditUtilisation ,
                currentShortTermDebt = response.creditReportInfo.currentShortTermDebt ,
                currentShortTermNonPromotionalDebt = response.creditReportInfo.currentShortTermNonPromotionalDebt ,
                daysUntilNextReport = response.creditReportInfo.daysUntilNextReport ,
                equifaxScoreBand = response.creditReportInfo.equifaxScoreBand ,
                equifaxScoreBandDescription = response.creditReportInfo.equifaxScoreBandDescription ,
                hasEverBeenDelinquent = response.creditReportInfo.hasEverBeenDelinquent ,
                hasEverDefaulted = response.creditReportInfo.hasEverDefaulted ,
                maxScoreValue = response.creditReportInfo.maxScoreValue ,
                minScoreValue = response.creditReportInfo.minScoreValue ,
                monthsSinceLastDefaulted = response.creditReportInfo.monthsSinceLastDefaulted ,
                monthsSinceLastDelinquent = response.creditReportInfo.monthsSinceLastDelinquent ,
                numNegativeScoreFactors = response.creditReportInfo.numNegativeScoreFactors ,
                numPositiveScoreFactors = response.creditReportInfo.numPositiveScoreFactors ,
                percentageCreditUsed = response.creditReportInfo.percentageCreditUsed,
                percentageCreditUsedDirectionFlag = response.creditReportInfo.percentageCreditUsedDirectionFlag ,
                score = response.creditReportInfo.score ,
                scoreBand = response.creditReportInfo.equifaxScoreBand ,
                status = response.creditReportInfo.status ,
            ),
            coachingSummary = CreditScore.CoachingSummary(
                activeChat = response.coachingSummary.activeChat,
                activeTodo = response.coachingSummary.activeTodo,
                numberOfCompletedTodoItems = response.coachingSummary.numberOfCompletedTodoItems,
                numberOfTodoItems = response.coachingSummary.numberOfTodoItems,
                selected = response.coachingSummary.selected
            )
        )
    }

}