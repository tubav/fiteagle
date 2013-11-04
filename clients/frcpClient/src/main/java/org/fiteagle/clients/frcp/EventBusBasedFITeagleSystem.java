package org.fiteagle.clients.frcp;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class EventBusBasedFITeagleSystem {
	private static final String QUEUE = "FITeagle";

	private static Connection createConnection() throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"vm://localhost");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		return connection;
	}

	public static void main(String[] args) throws Exception {
		Connection connection = createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(QUEUE);

		FITeagleCore fiteagleCore = new FITeagleCore(session, destination);
		FITeagleDeliverySFA fiteagleDeliverySFA = new FITeagleDeliverySFA(
				session, destination);

		new Thread(fiteagleCore).start();
		new Thread(fiteagleDeliverySFA).start();
		Thread.sleep(1000);
	}

	public static class FITeagleDeliverySFA implements Runnable {
		private Session session;
		private Destination destination;

		public FITeagleDeliverySFA(Session session, Destination destination) {
			this.destination = destination;
			this.session = session;
		}

		public void run() {
			try {
				MessageConsumer consumer = session.createConsumer(destination);
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				TextMessage message = session
						.createTextMessage("This could be a resource adapter or a DM component");
				producer.send(message);
			} catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}
	}

	
	public static class FITeagleCore implements Runnable, ExceptionListener {
		private Session session;
		private Destination destination;

		public FITeagleCore(Session session, Destination destination) {
			this.destination = destination;
			this.session = session;
		}

		public void run() {
			try {
				MessageConsumer consumer = session.createConsumer(destination);
				MessageProducer producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				// Message message = consumer.receive();
				consumer.setMessageListener(new MyListener());

			} catch (Exception e) {
				System.out.println("Caught: " + e);
				e.printStackTrace();
			}
		}

		public synchronized void onException(JMSException ex) {
			System.out.println("JMS Exception occured.  Shutting down client.");
		}

		private class MyListener implements MessageListener {

			@Override
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						String text = textMessage.getText();
						System.out.println("Received: " + text);
					} else {
						System.out.println("Received: " + message);
					}
				} catch (Exception e) {
					System.out.println("Caught: " + e);
					e.printStackTrace();
				}
			}

		}

	}
}
