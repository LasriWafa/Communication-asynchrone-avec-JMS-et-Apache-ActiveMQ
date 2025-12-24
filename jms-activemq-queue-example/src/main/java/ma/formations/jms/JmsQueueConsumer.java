package ma.formations.jms;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsQueueConsumer {
    private final String destinationName;
    private final String brockerUrl;

    public JmsQueueConsumer(String destinationName, String brockerUrl) {
        this.destinationName = destinationName;
        this.brockerUrl = brockerUrl;
    }

    public void receive() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brockerUrl);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Créer une session et une queue
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(destinationName);

        // Créer un consommateur pour recevoir le message
        MessageConsumer consumer = session.createConsumer(destination);

        // Attendre un message et le traiter
        Message message = consumer.receive(1000); // Attente de 1 seconde (1000ms) - TP says 1, assuming 1ms is too fast, but code says 1. Usually receive takes millis. TP comment says "Attente de 1 seconde". 1 = 1ms in receive(long timeout). receive(1000) is 1 sec. I will use 1000 to match the comment "1 seconde".
        
        if (message instanceof TextMessage textMessage) {
            System.out.println("Message reçu : " + textMessage.getText());
        } else {
            System.out.println("Aucun message reçu.");
        }

        // Fermer la connexion
        consumer.close();
        session.close();
        connection.close();
    }
}
