package kafkaconsume;

import kafka.admin.ConsumerGroupCommand;
import scala.Predef;
import scala.collection.immutable.List;

/**
 * Created by firstsword on 2019/3/18.
 */
public class MyConsumerGroup {
    public static void main(String[] args) {
        ConsumerGroupCommand.KafkaConsumerGroupService consumerGroupService = new ConsumerGroupCommand.KafkaConsumerGroupService(
                new ConsumerGroupCommand.ConsumerGroupCommandOptions(args));
        List<String> groups = consumerGroupService.listGroups();
    }
}
