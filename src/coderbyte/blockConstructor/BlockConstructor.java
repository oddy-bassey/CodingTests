package coderbyte.blockConstructor;

import java.io.*;
import java.util.*;

public class BlockConstructor {
    public static void main (String[] args) {
        /* create varriables to hold the following data
         *
         * mempool.csv file path
         * maxWeight
         * read map of transactions
         * list of sorted txIds
         * list of selected txIds
         *
         */
        String filePath = "mempool.csv";
        final int maxWeight = 4000000;

        try{
            Map<String, Transaction> transactions = parseFile(filePath);
            List<String> sortedTxIds = sortTransactions(transactions);
            List<String> selectedTxIds = selectTransactions(transactions, sortedTxIds, maxWeight);

            for(String txid : selectedTxIds) {
                System.out.println(txid);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // Transaction class object to hold data
    static class Transaction {
        private String txid;
        private Integer fee;
        private Integer weight;
        private List<String> parentTxids;

        public Transaction(String txid, Integer fee, Integer weight, List<String> parentTxids) {
            this.txid = txid;
            this.fee = fee;
            this.weight = weight;
            this.parentTxids = parentTxids;
        }
    }

    // method to parse mempool.csv file
    public static Map<String, Transaction> parseFile(String filePath) throws IOException {
        Map<String, Transaction> transactions = new HashMap<>();

        // read the file into a BufferedStream object
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // read each line in the stream until its null
        while((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String txId = data[0];
            Integer fee = Integer.parseInt(data[1]);
            Integer weight = Integer.parseInt((data[2]));

            // check if the transacton has parents
            List<String> parents = new ArrayList<>();
            if(data.length > 3){
                parents = Arrays.asList(data[4].split(";"));
            }
            // add the read transaction to the map data
            transactions.put(txId, new Transaction(txId, fee, weight, parents));
        }
        reader.close();
        return transactions;
    }

    // create method to sort the transactions
    public static List<String> sortTransactions(Map<String, Transaction> transactions) {
        Map<String, Integer> txDegree = new HashMap<>();
        Map<String, List<String>> txGraph = new HashMap<>();

        // Initialize the transaction occurrence/degree and graph map
        for (Transaction transaction : transactions.values()) {
            txDegree.put(transaction.txid, 0);
            txGraph.put(transaction.txid, new ArrayList<>());
        }

        // Construct the transaction graph and calculate the number of occurrence/degrees
        for (Transaction transaction : transactions.values()) {
            for (String parent : transaction.parentTxids) {
                txGraph.get(parent).add(transaction.txid);
                txDegree.put(transaction.txid, txDegree.get(transaction.txid) + 1);
            }
        }

        // sort the transactions
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : txDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);
            for (String child : txGraph.get(current)) {
                txDegree.put(child, txDegree.get(child) - 1);
                if (txDegree.get(child) == 0) {
                    queue.offer(child);
                }
            }
        }
        return result;
    }

    // create method to select the transactions
    public static List<String> selectTransactions(Map<String, Transaction> transactions, List<String> sortedTxids, int maxWeight) {
        List<String> selectedTxids = new ArrayList<>();
        int totalWeight = 0;
        for(String txid : sortedTxids) {
            if (totalWeight + transactions.get(txid).weight <= maxWeight) {
                selectedTxids.add(txid);
                totalWeight += transactions.get(txid).weight;
            }
        }

        return selectedTxids;
    }
}


