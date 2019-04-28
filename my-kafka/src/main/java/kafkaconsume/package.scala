import kafka.admin.ConsumerGroupCommand._
import kafka.common.KafkaException

object kafkaconsume {

  def main(args: Array[String]): Unit = {
    val opts = new ConsumerGroupCommandOptions(args)
    val consumerGroupService = new KafkaConsumerGroupService(opts)

    //consumerGroupService.listGroups().foreach(println(_))

    /*consumerGroupService.listGroups().foreach(println(_))
    val (state, assighments) = consumerGroupService.describeGroup()
    println(state)
    println(assighments)

    opts.describeOpt()*/

    consumerGroupService.listGroups().foreach(x => {
      //println(args)
      val argsBuf = args.toBuffer
      argsBuf += ("-group", x)
      val opts = new ConsumerGroupCommandOptions(argsBuf.toArray)
      val consumerGroupService = new KafkaConsumerGroupService(opts)
      val (state, assighments) = consumerGroupService.describeGroup()

      assighments match {
        case None =>
        case Some(assignments) =>

          assignments.foreach { consumerAssignment =>

            if (consumerAssignment.topic.getOrElse(MISSING_COLUMN_VALUE) == "json01") {

              state match {
                case Some("Empty") =>
                  System.err.println(s"Consumer group '$x' has no active members.")
                  printAssignment(assignments, true)
                case Some("PreparingRebalance") | Some("AwaitingSync") =>
                  System.err.println(s"Warning: Consumer group '$x' is rebalancing.")
                  printAssignment(assignments, true)
                case Some("Stable") =>
                  printAssignment(assignments, true)
                case other =>
                  // the control should never reach here
                  throw new KafkaException(s"Expected a valid consumer group state, but found '${other.getOrElse("NONE")}'.")

                /*print("%-30s %-10s %-15s %-15s %-10s %-50s".format(
                consumerAssignment.topic.getOrElse(MISSING_COLUMN_VALUE), consumerAssignment.partition.getOrElse(MISSING_COLUMN_VALUE),
                consumerAssignment.offset.getOrElse(MISSING_COLUMN_VALUE), consumerAssignment.logEndOffset.getOrElse(MISSING_COLUMN_VALUE),
                consumerAssignment.lag.getOrElse(MISSING_COLUMN_VALUE), consumerAssignment.consumerId.getOrElse(MISSING_COLUMN_VALUE)))

              print("%-30s %s".format(consumerAssignment.host.getOrElse(MISSING_COLUMN_VALUE), consumerAssignment.clientId.getOrElse(MISSING_COLUMN_VALUE)))

              println()*/
              }
            }
          }


          /*state match {
            case Some("Empty") =>
              System.err.println(s"Consumer group '$x' has no active members.")
              printAssignment(assignments, true)
            case Some("PreparingRebalance") | Some("AwaitingSync") =>
              System.err.println(s"Warning: Consumer group '$x' is rebalancing.")
              printAssignment(assignments, true)
            case Some("Stable") =>
              printAssignment(assignments, true)
            case other =>
              // the control should never reach here
              throw new KafkaException(s"Expected a valid consumer group state, but found '${other.getOrElse("NONE")}'.")
          }*/
      }
    })

  }
}
