package programming;

import java.util.Scanner;

public class Programming {

    static class Team {

        String name;
        int matchesPlayed = 0;
        int wins = 0;
        int draws = 0;
        int losses = 0;
        int points = 0;
        int goalsFor = 0;
        int goalsAgainst = 0;

        public Team(String name) {
            this.name = name;
        }

        public int getGoalDifference() {
            return goalsFor - goalsAgainst;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");
        Team teamC = new Team("Team C");
        Team teamD = new Team("Team D");

        Team[] teams = {teamA, teamB, teamC, teamD};

        Team[] homeTeams = {teamA, teamC, teamA, teamB, teamA, teamB};
        Team[] awayTeams = {teamB, teamD, teamC, teamD, teamD, teamC};

        System.out.println("--- FIKSTÜR ---");
        for (int i = 0; i < 6; i++) {
            System.out.println("Match " + (i + 1) + ": " + homeTeams[i].name + " vs " + awayTeams[i].name);
        }
        System.out.println("----------------\n");

        for (int i = 0; i < 6; i++) {
            Team home = homeTeams[i];
            Team away = awayTeams[i];

            System.out.println("Skor girin -> Match " + (i + 1) + ": " + home.name + " vs " + away.name);
            System.out.print(home.name + " goals: ");
            int homeGoals = scanner.nextInt();
            System.out.print(away.name + " goals: ");
            int awayGoals = scanner.nextInt();

            home.goalsFor += homeGoals;
            home.goalsAgainst += awayGoals;
            away.goalsFor += awayGoals;
            away.goalsAgainst += homeGoals;

            home.matchesPlayed++;
            away.matchesPlayed++;

            if (homeGoals > awayGoals) {
                home.points += 3;
                home.wins++;
                away.losses++;
            } else if (homeGoals < awayGoals) {
                away.points += 3;
                away.wins++;
                home.losses++;
            } else {
                home.points += 1;
                away.points += 1;
                home.draws++;
                away.draws++;
            }
            System.out.println();
        }

        System.out.println("\n--- PUAN DURUMU TABLOSU ---");
        System.out.printf("%-10s %-5s %-5s %-5s %-5s %-10s %-5s\n",
                "Team", "MP", "W", "D", "L", "Diff", "Pts");
        System.out.println("-----------------------------------------------------");

        for (Team team : teams) {
            System.out.printf("%-10s %-5d %-5d %-5d %-5d %-10d %-5d\n",
                    team.name,
                    team.matchesPlayed,
                    team.wins,
                    team.draws,
                    team.losses,
                    team.getGoalDifference(),
                    team.points);
        }

        Team champion = teams[0];

        for (int i = 1; i < teams.length; i++) {

            if (teams[i].points > champion.points) {
                champion = teams[i];
            } else if (teams[i].points == champion.points) {
                if (teams[i].getGoalDifference() > champion.getGoalDifference()) {
                    champion = teams[i];
                }
            }
        }

        System.out.println("\n=====================================================");
        System.out.println("🏆 TURNUVA ŞAMPİYONU: " + champion.name.toUpperCase() + " 🏆");
        System.out.println("=====================================================");

        scanner.close();
    }
}
