package client;

import edu.goit.analyzer.AvgAnalyzer;
import edu.goit.entity.PairResult;
import edu.goit.finalyzer.AvgFinalyzer;

import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) {
        CryptoServiceClient client = CryptoServiceClient.of();

        PairResult result = client.applyFor(new AvgAnalyzer(LocalDateTime.MIN, LocalDateTime.MAX),
                new AvgFinalyzer());

        System.out.println(result);

    }


}
