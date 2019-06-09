package app.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Getter
public class TennisMatch {
    @Id
    @GeneratedValue
    private Long id;

    private String playerName;
    private String opponentName;
    private String courtSurface;
    private String tournamentName;
    private String round;
    private String location;

    private boolean winner;
    private int gamesDifference;
    private int aces;
    private int doubleFaults;
    private int averageSetDurationInMinutes;
    private double successfulBreakPointsPercentage;
    private double firstServeWonPointsPercentage;
    private double secondServeWonPointsPercentage;
    private double returnWonPointsPercentage;
    private double firstServicePercentage;
    private int tieBreaksWon;
    private boolean isFirstSetWon;
}
