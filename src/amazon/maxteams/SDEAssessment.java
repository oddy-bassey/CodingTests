package amazon.maxteams;

import java.util.*;

public class SDEAssessment {

    public static void main(String[] args) {
//        System.out.println(countMaxNumTeams(Arrays.asList(3, 4, 3, 1, 6, 5), 3, 2));
//        System.out.println(countMaxNumTeams(Arrays.asList(5, 1, 2, 1, 4, 5), 3, 2));
        System.out.println(countMaximumProfitableGroups(Arrays.asList(2, 3, 2)));
        System.out.println(countMaximumProfitableGroups(Arrays.asList(3, 1, 3, 5)));
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

    public static int countMaximumProfitableGroups(List<Integer> stockPrice) {
        return countGroups(stockPrice, new ArrayList<>(), 0, 0, 0, 0,
                0, Integer.MIN_VALUE);
    }

    static int countGroups(List<Integer> stockPrice, List<Integer> group, int firstIndex, int lastIndex, int firstValue, int lastValue, int groupCount, int maxValue) {

        if(stockPrice.size() == 1) return 1;

        if(firstIndex > stockPrice.size()-1) {
            return groupCount;
        }
        if(lastIndex > stockPrice.size()-1) {
            firstIndex++;
            lastIndex = firstIndex;
            maxValue = Integer.MIN_VALUE;
            group.clear();
        }

        if(firstIndex == lastIndex && firstIndex <= stockPrice.size()-1) {
            groupCount++;
            group.add(stockPrice.get(lastIndex));
            return countGroups(stockPrice, group, firstIndex == stockPrice.size() - 1? firstIndex+1 : firstIndex, ++lastIndex, stockPrice.get(firstIndex),
                    stockPrice.get(firstIndex), groupCount, stockPrice.get(firstIndex));
        } else {
            maxValue = stockPrice.get(lastIndex) > maxValue? stockPrice.get(lastIndex) : maxValue;
            boolean isFirstGreaterAndIsMax = stockPrice.get(firstIndex) > stockPrice.get(lastIndex) && stockPrice.get(firstIndex) == maxValue;
            boolean isLastGreaterAndIsMax = stockPrice.get(lastIndex) > stockPrice.get(firstIndex) && stockPrice.get(lastIndex) == maxValue;
            boolean isFirstAndLastEqualAndIsMax = stockPrice.get(lastIndex) == stockPrice.get(firstIndex) && stockPrice.get(lastIndex) == maxValue;

            group.add(stockPrice.get(lastIndex));

            if(isFirstGreaterAndIsMax || isLastGreaterAndIsMax || isFirstAndLastEqualAndIsMax) {
                groupCount++;
            }
            return countGroups(stockPrice, group, firstIndex, ++lastIndex, stockPrice.get(firstIndex), lastValue, groupCount, maxValue);
        }
    }
}
