package app.loading;


import app.Utils;
import app.data.TennisMatch;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TennisCsvLoader {
    private static String csvFilepath = "src/main/resources/all_matches.csv";

    public static List<TennisMatch> load(int maxRecordCount) {
        List<TennisMatch> tennisMatches = new ArrayList<>();

        int i = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilepath))) {
            String line;

            boolean isFirstLine = true;

            while ((line = br.readLine()) != null && i < maxRecordCount) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    String[] values = line.split(",");

                    //Filter bad values
                    if (values.length > 54) {
                        continue;
                    }

                    try {
                        boolean isDouble = processBoolean(values[50]);

                        //Filter missing values
                        if (processInt(values[23]) == 0) {
                            continue;
                        }

                        //Is doubles have long name or difficult name then values[13] will turn into string -> parsing int expection
                        if (processInt(values[13]) != 0 && !isDouble) {       //check if sets are recorded

                            TennisMatch tennisMatch = TennisMatch.builder()
                                    .playerName(processName(values[7], "-"))
                                    .opponentName(processName(values[9], "-"))
                                    .courtSurface(values[3])
                                    .tournamentName(values[11])
                                    .round(values[12])
                                    .location(values[2])
                                    //fuzzy
                                    .winner(processBoolean(values[46]))
                                    .gamesDifference(Math.abs(processInt(values[15]) - processInt(values[16])))
                                    .aces(processInt(values[20]))
                                    .doubleFaults(processInt(values[21]))
                                    .averageSetDurationInMinutes(processTimeToMinutes(values[45]) / processInt(values[13]))
                                    .successfulBreakPointsPercentage(calculatePercentage(values[28], String.valueOf(processInt(values[28]) + processInt(values[29]))))
                                    .firstServeWonPointsPercentage(calculatePercentage(values[24], values[25]))
                                    .secondServeWonPointsPercentage(calculatePercentage(values[26], values[27]))
                                    .returnWonPointsPercentage(calculatePercentage(values[41], values[42]))
                                    .firstServicePercentage(calculatePercentage(values[22], values[23]))
                                    .tieBreaksWon(processInt(values[17]))
                                    .isFirstSetWon(processBoolean(values[49]))
                                    .build();

                            tennisMatches.add(tennisMatch);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Exception: " + i);
                    }
                }
                i++;
            }
        }
//        catch (NumberFormatException e){
//            System.out.println(i);
//            e.printStackTrace();
//        }
        catch (IOException e) {
            System.out.println(i);
            e.printStackTrace();
        }


        return tennisMatches;

    }

    private static double calculatePercentage(String value1, String value2) {
        int v1 = processInt(value1);
        int v2 = processInt(value2);

        if (v2 == 0) {
            return 0.0;
        } else {
            return Utils.round(((double) v1) / v2, 2);
        }
    }

    private static String processName(String name, String separator) {
        String[] nameParts = name.split(separator);
        List<String> parts = new ArrayList<>();
        for (String namePart : nameParts) {
            if (!namePart.equals("")) {
                parts.add(Character.toUpperCase(namePart.charAt(0)) + namePart.substring(1));
            }
        }
        return StringUtils.join(parts.toArray(new String[0]), " ");
    }

    private static boolean processBoolean(String truthString) throws IllegalArgumentException {
        if (truthString.length() != 1) {
            throw new IllegalArgumentException("Bad truth string!!");
        }
        return truthString.equals("t");
    }

    private static int processInt(String intString) {
        if (intString.equals("")) {
            return 0;
        }
        return Integer.parseInt(intString);
    }

    private static int processTimeToMinutes(String time) {
        if (time.length() != 8) {
            return 0;
        }
        String[] timeParts = time.split(":");
        return 60 * processInt(timeParts[0]) + processInt(timeParts[1]);

    }
}
