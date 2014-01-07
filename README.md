JavaTestTask
============

The target is to create User Service, which recieves Events and provides statistics. Each Event contains type(StartGame, Experience, FinishGame), experience and timestamp.

Service should aggregate experience for each user and return statistics by request. Each request contains userId, fromTimestamp and toTimestamp. Statistics should contain all data for specified user and be in provided timerang.

TDD was used
