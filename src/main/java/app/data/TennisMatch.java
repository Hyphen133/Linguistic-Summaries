package app.data;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
public class TennisMatch {
    @Id @GeneratedValue
    Long id;

    String playerName;
    String opponentName;
    String courtSurface;
    String tournamentName;
    String round;
    String location;

    boolean winner;
    int gamesDifference;
    int aces;
    int doubleFaults;
    int averageSetDurationInMinutes;
    double successfulBreakPointsPercentage;
    double firstServeWonPointsPercentage;
    double secondServeWonPointsPercentage;
    double returnWonPointsPercentage;
    double firstServicePercentage;
    int tieBreaksWon;
    boolean isFirstSetWon;

}
