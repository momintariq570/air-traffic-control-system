Air Traffic Control System (ATCS)

This project implements a simulation for enqueing and dequeing aircrafts
in to the ATCS. 

There are 4 types of aircrafts in order of queue priority:

1) EMERGENCY
2) VIP
3) PASSENGER
4) CARGO

There are 2 types of aircraft sizes in order of queue priority:

1) LARGE
2) SMALL

All EMERGENCY, LARGE aircrafts are at the top of the queue, followed by
all EMERGENCY, SMALL aircrafts and likewise for VIP, PASSENGER and CARGO
aircrafts.

To enforce the ordering above, the internal data structure is sorted
everytime an aircraft is added.

The application exposes 5 REST endpoints:

1) '/atcs-status': returns true or false if ATCS is running
2) '/boot': turns ATCS on if it is off and vice versa, and returns the aircraft queue
3) '/enqueue/{aircraftType}/{size}': adds aircraft to the queue and returns the updated queue
4) '/dequeue': removes an aircraft from the top of the queue and returns the updated queue
5) '/queue': returns the aircraft queue

The application can be started by executing the following command:

mvn spring-boot:run
