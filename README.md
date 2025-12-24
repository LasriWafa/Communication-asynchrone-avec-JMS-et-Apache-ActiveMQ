# JEE TP9 - Asynchronous Communication with JMS & Apache ActiveMQ

This project implements asynchronous communication patterns using **Jakarta JMS** and **Apache ActiveMQ** as the Message-Oriented Middleware (MOM). It demonstrates two core messaging models: **Point-to-Point (Queue)** and **Publish/Subscribe (Topic)**.

## 📂 Project Structure

The project is divided into three separate Maven modules:

1. **jms-activemq-queue-example**
    * Demonstrates the **Queue** model (Point-to-Point).
    * Contains both a Producer and a Consumer in a single execution flow.
2. **jms-activemq-topic-producer-example**
    * Demonstrates the **Topic** model (Publish/Subscribe).
    * Acts as the Publisher, sending `Article` objects to the topic.
3. **jms-activemq-topic-consumer-example**
    * Acts as the Subscriber.
    * Creates durable subscribers to receive messages from the topic.

## 🛠️ Prerequisites

* **Java**: JDK 17 or higher.
* **Apache ActiveMQ**: Version 6.1.4 (or compatible).
* **Maven**: For dependency management.

## 🚀 Getting Started

### 1. Start Apache ActiveMQ

Before running the applications, you must start the ActiveMQ broker.

1. Navigate to your ActiveMQ `bin` directory via terminal/cmd:

    ```bash
    cd /path/to/apache-activemq/bin
    ```

2. Start the broker:

    ```bash
    ./activemq start   # macOS/Linux
    .\activemq start   # Windows (PowerShell)
    ```

3. Verify it's running at [http://localhost:8161/admin](http://localhost:8161/admin) (Default credentials: `admin`/`admin`).

### 2. Run Point-to-Point (Queue) Example

This example establishes a 1-to-1 communication channel. Even if the consumer is offline, the message holds in the queue.

* **Class**: `ma.formations.jms.Main` in `jms-activemq-queue-example`.
* **Action**: Simply run the `main` method.
* **Result**: You will see messages being sent and immediately acknowledged/consumed.

### 3. Run Publish/Subscribe (Topic) Example

This example broadcasts messages to multiple subscribers.

**Step A: Start the Consumer**

* **Class**: `ma.formations.jms.Main` in `jms-activemq-topic-consumer-example`.
* **Action**: Run the `main` method.
* **Status**: It will start and wait for incoming messages.

**Step B: Start the Producer**

* **Class**: `ma.formations.jms.Main` in `jms-activemq-topic-producer-example`.
* **Action**: Run the `main` method.
* **Result**: The producer sends `Article` objects. Check the **Consumer's console** to see that both subscribers received the data.

## 📚 Technical Concepts Implemented

* **JMS ConnectionFactory**: To connect to the provider (ActiveMQ).
* **JMS Session**: Context for producing/consuming messages.
* **Queues**: Messages are delivered to **one** consumer (Load Balancing).
* **Topics**: Messages are delivered to **all** active subscribers (Broadcasting).
* **Durable Subscribers**: Subscribers that receive messages sent while they were offline (if configured).
