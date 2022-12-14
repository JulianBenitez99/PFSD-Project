package co.edu.escuelaing
package kafka.producer

import kafka.config.Config
import protos.trades.TradeProto

import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object TradesProducer extends Producer[TradeProto] {

  val LOGGER: Logger = Logger("TradesProducer")

  val producer = new KafkaProducer[String, Array[Byte]](Config.KAFKA_PRODUCER_PROPS)

  override def send(topic: String, key: String, value: TradeProto): Unit = {
    val record = new ProducerRecord[String, Array[Byte]](topic, key, value.toByteArray)
    producer.send(record)
    LOGGER.info(s"Sent record: $record")
  }
}
