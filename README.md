# Java-Banking-Simulator

### An Application Employing Synchronized/Cooperating Multiple Threads In Java Using Locks

There are five depositor agents (threads) and ten withdrawal agents
(threads), and one auditor agent (thread) simultaneously executing.
FixedThreadPool() and an Executor object are used to control the threads.

Below is a screenshot of the console while the program is running. I simulate Money Laundering Suppression Act
by flagging any depositing transaction with a deposit value greater than $350.00 and any withdrawal amount greater than $75.00.

![Console Screenshot](https://user-images.githubusercontent.com/42612374/229374045-4db5cdc9-b701-47be-b7f0-282d46fc1b5e.PNG)
