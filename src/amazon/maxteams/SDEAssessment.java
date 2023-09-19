package amazon.maxteams;

import java.util.*;

public class SDEAssessment {

    public static void main(String[] args) {
        System.out.println(countMaxNumTeams(Arrays.asList(3, 4, 3, 1, 6, 5), 3, 2));
        System.out.println(countMaxNumTeams(Arrays.asList(5, 1, 2, 1, 4, 5), 3, 2));
    }

    public static int countMaxNumTeams(List<Integer> skill, int teamSize, int maxDiff) {
        // Write your code here

        if(skill.isEmpty()) return 0;

        int maxTeamCount = 0;
        PriorityQueue<Integer> skillQueue = new PriorityQueue<>();
        skillQueue.addAll(skill);

        int teamMemberCount = 0;
        int minSkill = 0, maxSkill = 0;

        while(!skillQueue.isEmpty()){
            int skillValue = skillQueue.poll();

            if(teamMemberCount<1) {
                teamMemberCount++;
                minSkill = skillValue;
            } else if((teamMemberCount + 1) == teamSize) {
                maxSkill = skillValue;
                if((maxSkill - minSkill) <= maxDiff) {
                    maxTeamCount++;
                }

                teamMemberCount = 0;
            }else {
                teamMemberCount++;
            }
        }

        return maxTeamCount;
    }
}
